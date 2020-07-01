package ua.nure.stepanenko.thesis.db.connector.postgres.dao;

import ua.nure.stepanenko.thesis.db.connector.postgres.utli.DAOUtils;
import ua.nure.stepanenko.thesis.db.dao.PenaltyDAO;
import ua.nure.stepanenko.thesis.model.been.Penalty;

import java.sql.*;

public class PostgresPenaltyDAO extends PenaltyDAO {

    private static final String INSERT_INTO_PENALTY_SQL = "INSERT INTO penalty (cause, cost) VALUES (?, ?)";

    private static final String SELECT_FROM_PENALTY_SQL = "SELECT * FROM penalty WHERE id = ?";

    private static final String DELETE_FROM_PENALTY_SQL = "DELETE FROM penalty WHERE id = ?";

    private static PostgresPenaltyDAO postgresPenaltyDAO;

    private PostgresPenaltyDAO() {
    }

    public static PostgresPenaltyDAO getInstance() {
        if (postgresPenaltyDAO == null) {
            synchronized (PostgresPenaltyDAO.class) {
                if (postgresPenaltyDAO == null) {
                    postgresPenaltyDAO = new PostgresPenaltyDAO();
                }
            }
        }
        return postgresPenaltyDAO;
    }

    @Override
    public boolean createPenalty(Connection connection, Penalty penalty) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                INSERT_INTO_PENALTY_SQL, Statement.RETURN_GENERATED_KEYS)) {

            DAOUtils.fillPreparedStatement(preparedStatement, penalty.getCause(), penalty.getCost());
            boolean result = preparedStatement.executeUpdate() > 0;
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if(generatedKeys.next()) {
                penalty.setId(generatedKeys.getInt("id"));
            }

            return result;
        }
    }

    @Override
    public Penalty getPenaltyById(Connection connection, int id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_PENALTY_SQL)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return getPenalty(resultSet);
                }
            }
        }
        return new Penalty();
    }

    @Override
    public boolean deletePenaltyById(Connection connection, int id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_PENALTY_SQL)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        }
    }
}
