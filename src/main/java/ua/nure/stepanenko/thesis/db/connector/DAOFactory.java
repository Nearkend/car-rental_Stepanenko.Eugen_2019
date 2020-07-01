package ua.nure.stepanenko.thesis.db.connector;

import ua.nure.stepanenko.thesis.db.dao.CarDAO;
import ua.nure.stepanenko.thesis.db.dao.OrderDAO;
import ua.nure.stepanenko.thesis.db.dao.PenaltyDAO;
import ua.nure.stepanenko.thesis.db.dao.UserDAO;
import ua.nure.stepanenko.thesis.exception.db.DBException;

import java.sql.Connection;

public interface DAOFactory {

    Connection getConnection() throws DBException;

    UserDAO getUserDAO();

    PenaltyDAO getPenaltyDAO();

    OrderDAO getOrderDAO();

    CarDAO getCarDAO();

    void close(Connection connection) throws DBException;

    void commit(Connection connection) throws DBException;

    void rollBack(Connection connection) throws DBException;
}
