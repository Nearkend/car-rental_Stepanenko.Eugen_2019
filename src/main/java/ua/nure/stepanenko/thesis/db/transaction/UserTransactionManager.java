package ua.nure.stepanenko.thesis.db.transaction;

import ua.nure.stepanenko.thesis.exception.db.DBException;
import ua.nure.stepanenko.thesis.model.constant.Role;
import ua.nure.stepanenko.thesis.model.entyty.User;

public interface UserTransactionManager {

    boolean createUser(User user) throws DBException;

    User getUserByLogin(String login) throws DBException;

    User getUserByLoginAndPassword(String login, String password) throws DBException;

    boolean updateUserByLogin(User user, String oldLogin) throws DBException;

    boolean setUserFieldRoleByUserLogin(Role role, String userLogin) throws DBException;

    boolean setUserFieldPassportByUserLogin(String passport, String userLogin) throws DBException;

    boolean setUserFieldBlockedByUserLogin(boolean blocked, String userLogin) throws DBException;
}
