package ua.nure.stepanenko.thesis.db.connector.postgres.transaction;

import org.apache.log4j.Logger;
import ua.nure.stepanenko.thesis.db.connector.DAOFactory;
import ua.nure.stepanenko.thesis.db.transaction.CarTransactionManager;
import ua.nure.stepanenko.thesis.exception.ExceptionMessages;
import ua.nure.stepanenko.thesis.exception.db.DBException;
import ua.nure.stepanenko.thesis.model.entyty.Car;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PostgresCarTransactionManager implements CarTransactionManager {

    private static final Logger LOG = Logger.getLogger(PostgresCarTransactionManager.class);

    private static PostgresCarTransactionManager postgresCarTransactionManager;

    private DAOFactory daoFactory;

    private PostgresCarTransactionManager(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public static PostgresCarTransactionManager getInstance(DAOFactory daoFactory) {
        if (postgresCarTransactionManager == null) {
            synchronized (PostgresCarTransactionManager.class) {
                if (postgresCarTransactionManager == null) {
                    postgresCarTransactionManager = new PostgresCarTransactionManager(daoFactory);
                }
            }
        }
        return postgresCarTransactionManager;
    }

    @Override
    public boolean createCar(Car car) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            result = daoFactory.getCarDAO().createCar(connection, car);
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_CREATE_CAR_MESSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_CREATE_CAR_MESSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public List<Car> getCars() throws DBException {
        Connection connection = daoFactory.getConnection();
        List<Car> result;
        try {
            result = daoFactory.getCarDAO().getCars(connection);
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.FAILED_TO_GET_CARS_MESSAGE, e);
            throw new DBException(ExceptionMessages.FAILED_TO_GET_CARS_MESSAGE, e);
        }
        daoFactory.close(connection);
        return result;
    }

    @Override
    public List<Car> getCarsByClass(String clazz, String sort) throws DBException {
        Connection connection = daoFactory.getConnection();
        List<Car> result;
        try {
            result = daoFactory.getCarDAO().getCarsByClass(connection, clazz, sort);
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.FAILED_TO_GET_CARS_BY_CLASS_MESSAGE, e);
            throw new DBException(ExceptionMessages.FAILED_TO_GET_CARS_BY_CLASS_MESSAGE, e);
        }
        daoFactory.close(connection);
        return result;
    }

    @Override
    public List<Car> getCarsByMark(String mark, String sort) throws DBException {
        Connection connection = daoFactory.getConnection();
        List<Car> result;
        try {
            result = daoFactory.getCarDAO().getCarsByMark(connection, mark, sort);
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.FAILED_TO_GET_CARS_BY_MARK_MESSAGE, e);
            throw new DBException(ExceptionMessages.FAILED_TO_GET_CARS_BY_MARK_MESSAGE, e);
        }
        daoFactory.close(connection);
        return result;
    }

    @Override
    public boolean updateCar(Car car) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            result = daoFactory.getCarDAO().updateCar(connection, car);
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_EDIT_CAR_MESSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_EDIT_CAR_MESSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public boolean setCarFieldThereIsByCarId(boolean thereIs, int carId) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            result = daoFactory.getCarDAO().setCarFieldThereIsByCarId(connection, thereIs, carId);
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_SET_CAR_FIELD_THERE_IS_MESSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_SET_CAR_FIELD_THERE_IS_MESSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public boolean deleteCarById(int id) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            result = daoFactory.getCarDAO().deleteCarById(connection, id);
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_DELETE_CAR_BY_ID_MESSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_DELETE_CAR_BY_ID_MESSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }
}
