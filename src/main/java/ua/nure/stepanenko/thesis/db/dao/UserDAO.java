package ua.nure.stepanenko.thesis.db.dao;

import ua.nure.stepanenko.thesis.model.constant.Role;
import ua.nure.stepanenko.thesis.model.entyty.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserDAO {

    public abstract boolean createUser(Connection connection, User user) throws SQLException;

    public abstract User getUserByLogin(Connection connection, String login) throws SQLException;

    public abstract User getUserByLoginAndPassword(
            Connection connection, String login, String password) throws SQLException;

    public abstract boolean updateUserByLogin(Connection connection, User user, String oldLogin) throws SQLException;

    public abstract boolean setUserFieldRoleByUserLogin(
            Connection connection, Role role, String userLogin) throws SQLException;

    public abstract boolean setUserFieldPassportByUserLogin(
            Connection connection, String passport, String userLogin) throws SQLException;

    public abstract boolean setUserFieldBlockedByUserLogin(
            Connection connection, boolean blocked, String userLogin) throws SQLException;

    protected User getUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setFullName(resultSet.getString("full_name"));
        user.setPassport(resultSet.getString("passport"));
        user.setBlocked(resultSet.getBoolean("blocked"));
        user.setRole(Role.getRoleById(resultSet.getInt("role_id")));
        return user;
    }
}
