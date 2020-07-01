package ua.nure.stepanenko.thesis.db.connector.postgres.dao;

import org.apache.commons.codec.digest.DigestUtils;
import ua.nure.stepanenko.thesis.db.connector.postgres.utli.DAOUtils;
import ua.nure.stepanenko.thesis.db.dao.UserDAO;
import ua.nure.stepanenko.thesis.model.constant.Role;
import ua.nure.stepanenko.thesis.model.entyty.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgresUserDAO extends UserDAO {

    private static final String INSERT_INTO_USERS_SQL = "INSERT INTO users VALUES (?, ?, ?)";

    private static final String SELECT_FROM_USERS_BY_LOGIN_SQL = "SELECT * FROM users WHERE login = ?";

    private static final String SELECT_FROM_USERS_BY_LOGIN_AND_PASSWORD_SQL =
            "SELECT * FROM users WHERE login = ? AND password = ?";

    private static final String UPDATE_USERS_SQL =
            "UPDATE users SET login = ?, password = ?, full_name = ?, passport = ? WHERE login = ?";

    private static final String UPDATE_USERS_ROLE_SQL = "UPDATE users SET role_id = ? WHERE login = ?";

    private static final String UPDATE_USERS_PASSPORT_SQL = "UPDATE users SET passport = ? WHERE login = ?";

    private static final String UPDATE_USERS_BLOCKED_SQL = "UPDATE users SET blocked = ? WHERE login = ?";

    private static PostgresUserDAO postgresUserDAO;

    private PostgresUserDAO() {
    }

    public static PostgresUserDAO getInstance() {
        if (postgresUserDAO == null) {
            synchronized (PostgresUserDAO.class) {
                if (postgresUserDAO == null) {
                    postgresUserDAO = new PostgresUserDAO();
                }
            }
        }
        return postgresUserDAO;
    }

    @Override
    public boolean createUser(Connection connection, User user) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_USERS_SQL)) {
            DAOUtils.fillPreparedStatement(
                    preparedStatement,
                    user.getLogin(),
                    DigestUtils.md5Hex(user.getPassword()),
                    user.getFullName());

            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public User getUserByLogin(Connection connection, String login) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USERS_BY_LOGIN_SQL)) {
            preparedStatement.setString(1, login);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return getUser(resultSet);
                }
            }
        }
        return new User();
    }

    @Override
    public User getUserByLoginAndPassword(
            Connection connection, String login, String password) throws SQLException {

            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USERS_BY_LOGIN_AND_PASSWORD_SQL)) {
            DAOUtils.fillPreparedStatement(preparedStatement, login, DigestUtils.md5Hex(password));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return getUser(resultSet);
                }
            }
        }
        return new User();
    }

    @Override
    public boolean updateUserByLogin(Connection connection, User newUser, String oldLogin) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)) {
            DAOUtils.fillPreparedStatement(
                    preparedStatement,
                    newUser.getLogin(),
                    DigestUtils.md5Hex(newUser.getPassword()),
                    newUser.getFullName(),
                    newUser.getPassport(),
                    oldLogin);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean setUserFieldRoleByUserLogin(
            Connection connection, Role role, String userLogin) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_ROLE_SQL)) {
            DAOUtils.fillPreparedStatement(
                    preparedStatement,
                    role.getId(),
                    userLogin);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean setUserFieldPassportByUserLogin(
            Connection connection, String passport, String userLogin) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_PASSPORT_SQL)) {
            DAOUtils.fillPreparedStatement(
                    preparedStatement,
                    passport,
                    userLogin);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean setUserFieldBlockedByUserLogin(
            Connection connection, boolean blocked, String userLogin) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_BLOCKED_SQL)) {
            DAOUtils.fillPreparedStatement(
                    preparedStatement,
                    blocked,
                    userLogin);

            return preparedStatement.executeUpdate() > 0;
        }
    }
}
