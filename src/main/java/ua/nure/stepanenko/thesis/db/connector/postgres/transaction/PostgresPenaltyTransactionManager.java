package ua.nure.stepanenko.thesis.db.connector.postgres.transaction;

import org.apache.log4j.Logger;
import ua.nure.stepanenko.thesis.db.connector.DAOFactory;
import ua.nure.stepanenko.thesis.db.transaction.PenaltyTransactionManager;
import ua.nure.stepanenko.thesis.exception.ExceptionMessages;
import ua.nure.stepanenko.thesis.exception.db.DBException;
import ua.nure.stepanenko.thesis.model.been.Penalty;

import java.sql.Connection;
import java.sql.SQLException;

public class PostgresPenaltyTransactionManager implements PenaltyTransactionManager {

    private static final Logger LOG = Logger.getLogger(PostgresPenaltyTransactionManager.class);

    private static PostgresPenaltyTransactionManager postgresPenaltyTransactionManager;

    private DAOFactory daoFactory;

    private PostgresPenaltyTransactionManager(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public static PostgresPenaltyTransactionManager getInstance(DAOFactory daoFactory) {
        if (postgresPenaltyTransactionManager == null) {
            synchronized (PostgresPenaltyTransactionManager.class) {
                if (postgresPenaltyTransactionManager == null) {
                    postgresPenaltyTransactionManager = new PostgresPenaltyTransactionManager(daoFactory);
                }
            }
        }
        return postgresPenaltyTransactionManager;
    }

    @Override
    public boolean createPenalty(Penalty penalty) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            result = daoFactory.getPenaltyDAO().createPenalty(connection, penalty);
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_CREATE_PENALTY_MESSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_CREATE_PENALTY_MESSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public Penalty getPenaltyById(int id) throws DBException {
        Connection connection = daoFactory.getConnection();
        Penalty result;
        try {
            result = daoFactory.getPenaltyDAO().getPenaltyById(connection, id);
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_GET_PENALTY_BY_ID_MESSAGE, e);
            throw new DBException(ExceptionMessages.COULD_NOT_GET_PENALTY_BY_ID_MESSAGE, e);
        }
        daoFactory.close(connection);
        return result;
    }

    @Override
    public boolean deletePenaltyById(int id) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            result = daoFactory.getPenaltyDAO().deletePenaltyById(connection, id);
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_DELETE_PENALTY_BY_ID_MESSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_DELETE_PENALTY_BY_ID_MESSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }
}
