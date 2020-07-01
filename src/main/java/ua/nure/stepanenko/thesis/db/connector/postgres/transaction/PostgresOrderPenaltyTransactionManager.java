package ua.nure.stepanenko.thesis.db.connector.postgres.transaction;

import org.apache.log4j.Logger;
import ua.nure.stepanenko.thesis.db.connector.DAOFactory;
import ua.nure.stepanenko.thesis.db.transaction.OrderPenaltyTransactionManager;
import ua.nure.stepanenko.thesis.exception.ExceptionMessages;
import ua.nure.stepanenko.thesis.exception.db.DBException;
import ua.nure.stepanenko.thesis.model.been.Penalty;
import ua.nure.stepanenko.thesis.model.constant.State;

import java.sql.Connection;
import java.sql.SQLException;

public class PostgresOrderPenaltyTransactionManager implements OrderPenaltyTransactionManager {

    private static final Logger LOG = Logger.getLogger(PostgresOrderPenaltyTransactionManager.class);

    private static PostgresOrderPenaltyTransactionManager postgresOrderPenaltyTransactionManager;

    private DAOFactory daoFactory;

    private PostgresOrderPenaltyTransactionManager(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public static PostgresOrderPenaltyTransactionManager getInstance(DAOFactory daoFactory) {
        if (postgresOrderPenaltyTransactionManager == null) {
            synchronized (PostgresOrderPenaltyTransactionManager.class) {
                if (postgresOrderPenaltyTransactionManager == null) {
                    postgresOrderPenaltyTransactionManager = new PostgresOrderPenaltyTransactionManager(daoFactory);
                }
            }
        }
        return postgresOrderPenaltyTransactionManager;
    }

    @Override
    public boolean createAndSetOrderFieldPenaltyIdByOrderNumber(
            Penalty penalty, int orderNumber) throws DBException {

        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            result = daoFactory.getPenaltyDAO().createPenalty(connection, penalty);
            result &= daoFactory.getOrderDAO().setOrderFieldPenaltyIdByOrderNumber(
                    connection, penalty.getId(), orderNumber);

        } catch (SQLException e) {
            LOG.error(ExceptionMessages.TRANSACTION_ERROR_MESSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.TRANSACTION_ERROR_MESSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public boolean createAndSetOrderFieldPenaltyIdByOrderNumberAndChangeState(
            Penalty penalty, int orderNumber, State state) throws DBException {
        //TODO Think about trigger for order state here

        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            result = daoFactory.getPenaltyDAO().createPenalty(connection, penalty);
            result &= daoFactory.getOrderDAO().setOrderFieldPenaltyIdByOrderNumber(
                    connection, penalty.getId(), orderNumber);

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
