package ua.nure.stepanenko.thesis.model.entyty;

import ua.nure.stepanenko.thesis.model.been.Penalty;
import ua.nure.stepanenko.thesis.model.constant.State;

import java.io.Serializable;
import java.sql.Date;

public class Order implements Serializable {

    private int number;
    private User user;
    private Car car;
    private boolean driver;
    private State state;
    private Date term;
    private Penalty penalty;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public boolean isDriver() {
        return driver;
    }

    public void setDriver(boolean driver) {
        this.driver = driver;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Date getTerm() {
        return term;
    }

    public void setTerm(Date term) {
        this.term = term;
    }

    public Penalty getPenalty() {
        return penalty;
    }

    public void setPenalty(Penalty penalty) {
        this.penalty = penalty;
    }

    @Override
    public String toString() {
        return "number - " + number +
                ", user - " + user +
                ", car - " + car +
                ", driver - " + driver +
                ", state - " + state +
                ", term - " + term +
                ", penalty - " + penalty;
    }
}

