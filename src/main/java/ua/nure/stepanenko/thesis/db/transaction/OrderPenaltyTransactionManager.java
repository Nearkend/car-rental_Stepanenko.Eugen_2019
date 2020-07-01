package ua.nure.stepanenko.thesis.db.transaction;

import ua.nure.stepanenko.thesis.exception.db.DBException;
import ua.nure.stepanenko.thesis.model.been.Penalty;
import ua.nure.stepanenko.thesis.model.constant.State;

public interface OrderPenaltyTransactionManager {

    boolean createAndSetOrderFieldPenaltyIdByOrderNumber(
            Penalty penalty, int orderNumber) throws DBException;

    boolean createAndSetOrderFieldPenaltyIdByOrderNumberAndChangeState(
            Penalty penalty, int orderNumber, State state) throws DBException;
}
