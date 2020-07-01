-- Drop all tables --
DROP TABLE orders;
DROP TABLE cars;
DROP TABLE users;
DROP TABLE states;
DROP TABLE penalty;
DROP TABLE marks;
DROP TABLE classes;
DROP TABLE roles;

CREATE TABLE roles (
                       id SERIAL NOT NULL,
                       name CHARACTER VARYING(8) NOT NULL ,
                       CONSTRAINT role_id PRIMARY KEY (id)
);

-- Create table users --
CREATE TABLE users (
                       login CHARACTER VARYING(16) NOT NULL,
                       password CHARACTER VARYING(32) NOT NULL,
                       full_name CHARACTER VARYING(32) NOT NULL,
                       passport CHARACTER VARYING(8) NULL,
                       blocked BOOLEAN NOT NULL DEFAULT FALSE,
                       role_id INTEGER NOT NULL DEFAULT 1,
                       CONSTRAINT user_login PRIMARY KEY (login),
                       CONSTRAINT role_id FOREIGN KEY (role_id)
                           REFERENCES roles (id) MATCH SIMPLE
                           ON UPDATE NO ACTION ON DELETE CASCADE
);

-- Create table marks --
CREATE TABLE marks (
                       id SERIAL NOT NULL,
                       name CHARACTER VARYING(12) NOT NULL ,
                       CONSTRAINT mark_id PRIMARY KEY (id)
);

-- Create table classes --
CREATE TABLE classes (
                         id SERIAL NOT NULL,
                         name CHARACTER VARYING(10) NOT NULL,
                         CONSTRAINT class_id PRIMARY KEY (id)
);

-- Create table cars --
CREATE TABLE cars (
                      id SERIAL NOT NULL,
                      mark_id INTEGER NOT NULL,
                      class_id INTEGER NOT NULL,
                      name CHARACTER VARYING(16) NOT NULL,
                      cost INTEGER NOT NULL,
                      there_is BOOLEAN NOT NULL DEFAULT TRUE,
                      CONSTRAINT car_id PRIMARY KEY (id),
                      CONSTRAINT mark_id FOREIGN KEY (mark_id)
                          REFERENCES marks (id) MATCH SIMPLE
                          ON UPDATE NO ACTION ON DELETE CASCADE,
                      CONSTRAINT class_id FOREIGN KEY (class_id)
                          REFERENCES classes (id) MATCH SIMPLE
                          ON UPDATE NO ACTION ON DELETE CASCADE
);

-- Create table states --
CREATE TABLE states (
                        id SERIAL NOT NULL,
                        name CHARACTER VARYING(12) NOT NULL,
                        CONSTRAINT state_id PRIMARY KEY (id)
);

-- Create table penalty --
CREATE TABLE penalty (
                         id SERIAL NOT NULL,
                         cost INTEGER NULL,
                         cause CHARACTER VARYING(96) NOT NULL,
                         CONSTRAINT penalty_id PRIMARY KEY (id)
);

-- Create table orders --
CREATE TABLE orders (
                        number SERIAL NOT NULL,
                        user_login CHARACTER VARYING(16) NOT NULL,
                        car_id INTEGER NOT NULL,
                        state_id INTEGER NOT NULL DEFAULT 1,
                        penalty_id INTEGER NULL,
                        driver BOOLEAN NOT NULL DEFAULT FALSE,
                        term DATE NOT NULL,
                        CONSTRAINT order_number PRIMARY KEY (number),
                        CONSTRAINT user_login FOREIGN KEY (user_login)
                            REFERENCES users (login) MATCH SIMPLE
                            ON UPDATE NO ACTION ON DELETE CASCADE,
                        CONSTRAINT car_id FOREIGN KEY (car_id)
                            REFERENCES cars (id) MATCH SIMPLE
                            ON UPDATE NO ACTION ON DELETE CASCADE,
                        CONSTRAINT state_id FOREIGN KEY (state_id)
                            REFERENCES states (id) MATCH SIMPLE
                            ON UPDATE NO ACTION  ON DELETE CASCADE,
                        CONSTRAINT penalty_id FOREIGN KEY (penalty_id)
                            REFERENCES penalty (id) MATCH SIMPLE
                            ON UPDATE NO ACTION  ON DELETE CASCADE
)