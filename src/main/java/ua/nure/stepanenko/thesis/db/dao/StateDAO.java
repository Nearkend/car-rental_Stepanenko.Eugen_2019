package ua.nure.stepanenko.thesis.db.dao;

import ua.nure.stepanenko.thesis.model.entyty.StateCounter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class StateDAO {

    public abstract List<StateCounter> getStateCounters(Connection connection) throws SQLException;
}
