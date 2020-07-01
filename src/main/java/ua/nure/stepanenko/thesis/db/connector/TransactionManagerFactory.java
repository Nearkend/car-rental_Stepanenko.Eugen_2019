package ua.nure.stepanenko.thesis.db.connector;

import ua.nure.stepanenko.thesis.db.transaction.*;

public interface TransactionManagerFactory {

    UserTransactionManager getUserTransactionManager();

    PenaltyTransactionManager getPenaltyTransactionManager();

    OrderTransactionManager getOrderTransactionManager();

    CarTransactionManager getCarTransactionManager();

    CarOrderTransactionManager getCarOrderTransactionManager();

    OrderPenaltyTransactionManager getOrderPenaltyTransactionManager();
}
