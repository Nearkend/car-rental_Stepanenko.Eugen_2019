package ua.nure.stepanenko.thesis.db.transaction;

import ua.nure.stepanenko.thesis.exception.db.DBException;
import ua.nure.stepanenko.thesis.model.constant.State;

public interface CarOrderTransactionManager {

    boolean changeCarFieldThereIsAndOrderFieldStateByIDs(
            boolean thereIs, int carId, State state, int orderNumber) throws DBException;
}
