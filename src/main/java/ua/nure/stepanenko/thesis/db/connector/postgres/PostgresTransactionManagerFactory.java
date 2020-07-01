package ua.nure.stepanenko.thesis.db.connector.postgres;

import ua.nure.stepanenko.thesis.db.connector.DAOFactory;
import ua.nure.stepanenko.thesis.db.connector.TransactionManagerFactory;
import ua.nure.stepanenko.thesis.db.connector.postgres.transaction.*;
import ua.nure.stepanenko.thesis.db.transaction.*;

public class PostgresTransactionManagerFactory implements TransactionManagerFactory {

    private static PostgresTransactionManagerFactory transactionManagerFactory;

    private DAOFactory daoFactory;

    private PostgresTransactionManagerFactory(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public static PostgresTransactionManagerFactory getInstance() {
        if (transactionManagerFactory == null) {
            synchronized (PostgresTransactionManagerFactory.class) {
                if (transactionManagerFactory == null) {
                    transactionManagerFactory =
                            new PostgresTransactionManagerFactory(PostgresDAOFactory.getInstance());
                }
            }
        }
        return transactionManagerFactory;
    }

    @Override
    public UserTransactionManager getUserTransactionManager() {
        return PostgresUserTransactionManager.getInstance(daoFactory);
    }

    @Override
    public PenaltyTransactionManager getPenaltyTransactionManager() {
        return PostgresPenaltyTransactionManager.getInstance(daoFactory);
    }

    @Override
    public OrderTransactionManager getOrderTransactionManager() {
        return PostgresOrderTransactionManager.getInstance(daoFactory);
    }

    @Override
    public CarTransactionManager getCarTransactionManager() {
        return PostgresCarTransactionManager.getInstance(daoFactory);
    }

    @Override
    public CarOrderTransactionManager getCarOrderTransactionManager() {
        return PostgresCarOrderTransactionManager.getInstance(daoFactory);
    }

    @Override
    public OrderPenaltyTransactionManager getOrderPenaltyTransactionManager() {
        return PostgresOrderPenaltyTransactionManager.getInstance(daoFactory);
    }
}
