package ua.nure.stepanenko.thesis.db.dao;

import ua.nure.stepanenko.thesis.model.been.Penalty;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class PenaltyDAO {

    public abstract boolean createPenalty(Connection connection, Penalty penalty) throws SQLException;

    public abstract Penalty getPenaltyById(Connection connection, int id) throws SQLException;

    public abstract boolean deletePenaltyById(Connection connection, int id) throws SQLException;

    protected Penalty getPenalty(ResultSet resultSet) throws SQLException {
        Penalty penalty = new Penalty();
        penalty.setId(resultSet.getInt("id"));
        penalty.setCause(resultSet.getString("cause"));
        penalty.setCost(resultSet.getInt("cost"));
        return penalty;
    }
}
