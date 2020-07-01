package ua.nure.stepanenko.thesis.db.transaction;

import ua.nure.stepanenko.thesis.exception.db.DBException;
import ua.nure.stepanenko.thesis.model.been.Penalty;

public interface PenaltyTransactionManager {

    boolean createPenalty(Penalty penalty) throws DBException;

    Penalty getPenaltyById(int id) throws DBException;

    boolean deletePenaltyById(int id) throws DBException;
}
