package ua.nure.stepanenko.thesis.db.connector.postgres.transaction;

import org.apache.log4j.Logger;
import ua.nure.stepanenko.thesis.db.connector.DAOFactory;
import ua.nure.stepanenko.thesis.db.transaction.CarOrderTransactionManager;
import ua.nure.stepanenko.thesis.exception.ExceptionMessages;
import ua.nure.stepanenko.thesis.exception.db.DBException;
import ua.nure.stepanenko.thesis.model.constant.State;

import java.sql.Connection;
import java.sql.SQLException;

public class PostgresCarOrderTransactionManager implements CarOrderTransactionManager {

    private static final Logger LOG = Logger.getLogger(PostgresCarTransactionManager.class);

    private static PostgresCarOrderTransactionManager postgresCarTransactionManager;

    private DAOFactory daoFactory;

    private PostgresCarOrderTransactionManager(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public static PostgresCarOrderTransactionManager getInstance(DAOFactory daoFactory) {
        if (postgresCarTransactionManager == null) {
            synchronized (PostgresCarOrderTransactionManager.class) {
                if (postgresCarTransactionManager == null) {
                    postgresCarTransactionManager = new PostgresCarOrderTransactionManager(daoFactory);
                }
            }
        }
        return postgresCarTransactionManager;
    }

    @Override
    public boolean changeCarFieldThereIsAndOrderFieldStateByIDs(
            boolean thereIs, int carId, State state, int orderNumber) throws DBException {

        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            result = daoFactory.getCarDAO().setCarFieldThereIsByCarId(connection, thereIs, carId);
            result &= daoFactory.getOrderDAO().setOrderFieldStateByOrderNumber(connection, state, orderNumber);
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.TRANSACTION_ERROR_MESSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.TRANSACTION_ERROR_MESSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }
}
