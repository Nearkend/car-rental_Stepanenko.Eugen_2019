package ua.nure.stepanenko.thesis.db.connector.postgres;

import org.apache.log4j.Logger;
import ua.nure.stepanenko.thesis.db.connector.DAOFactory;
import ua.nure.stepanenko.thesis.db.connector.postgres.dao.PostgresCarDAO;
import ua.nure.stepanenko.thesis.db.connector.postgres.dao.PostgresOrderDAO;
import ua.nure.stepanenko.thesis.db.connector.postgres.dao.PostgresPenaltyDAO;
import ua.nure.stepanenko.thesis.db.connector.postgres.dao.PostgresUserDAO;
import ua.nure.stepanenko.thesis.db.dao.CarDAO;
import ua.nure.stepanenko.thesis.db.dao.OrderDAO;
import ua.nure.stepanenko.thesis.db.dao.PenaltyDAO;
import ua.nure.stepanenko.thesis.db.dao.UserDAO;
import ua.nure.stepanenko.thesis.exception.ExceptionMessages;
import ua.nure.stepanenko.thesis.exception.db.DBException;
import ua.nure.stepanenko.thesis.exception.db.StartupDBException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PostgresDAOFactory implements DAOFactory {

    private static final Logger LOG = Logger.getLogger(PostgresDAOFactory.class);

    private static PostgresDAOFactory postgresDAOFactory;

    private DataSource dataSource;

    public PostgresDAOFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static PostgresDAOFactory getInstance() {
        if (postgresDAOFactory == null) {
            synchronized (PostgresDAOFactory.class) {
                if (postgresDAOFactory == null) {
                    try {
                        Context initContext = new InitialContext();
                        Context envContext = (Context) initContext.lookup("java:/comp/env");
                        DataSource dataSource = (DataSource) envContext.lookup("jdbc/ST4DB");
                        postgresDAOFactory = new PostgresDAOFactory(dataSource);
                        LOG.trace("Data source --> " + dataSource);
                    } catch (NamingException e) {
                        LOG.error(ExceptionMessages.ERRORS_WHEN_INITIALIZING_DS_MESSAGE, e);
                        throw new StartupDBException(ExceptionMessages.ERRORS_WHEN_INITIALIZING_DS_MESSAGE, e);
                    }
                }
            }
        }
        return postgresDAOFactory;
    }

    @Override
    public Connection getConnection() throws DBException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.FAILED_TO_GET_CONNECTION_MESSAGE, e);
            throw new DBException(ExceptionMessages.FAILED_TO_GET_CONNECTION_MESSAGE, e);
        }
    }

    @Override
    public UserDAO getUserDAO() {
        return PostgresUserDAO.getInstance();
    }

    @Override
    public PenaltyDAO getPenaltyDAO() {
        return PostgresPenaltyDAO.getInstance();
    }

    @Override
    public OrderDAO getOrderDAO() {
        return PostgresOrderDAO.getInstance();
    }

    @Override
    public CarDAO getCarDAO() {
        return PostgresCarDAO.getInstance();
    }

    @Override
    public void close(Connection connection) throws DBException {
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_CLOSE_CONNECTION_MESSAGE, e);
            throw new DBException(ExceptionMessages.COULD_NOT_CLOSE_CONNECTION_MESSAGE, e);
        }
    }

    @Override
    public void commit(Connection connection) throws DBException {
        try {
            connection.commit();
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.FAILED_TO_MAKE_CHANGES_MESSAGE, e);
            throw new DBException(ExceptionMessages.FAILED_TO_MAKE_CHANGES_MESSAGE, e);
        }
    }

    @Override
    public void rollBack(Connection connection) throws DBException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_ROLLBACK_TRANSACTION_MESSAGE, e);
            throw new DBException(ExceptionMessages.COULD_NOT_ROLLBACK_TRANSACTION_MESSAGE, e);
        }
    }
}
