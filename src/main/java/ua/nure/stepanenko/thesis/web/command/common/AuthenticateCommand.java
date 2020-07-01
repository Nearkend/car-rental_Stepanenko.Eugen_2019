package ua.nure.stepanenko.thesis.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.stepanenko.thesis.db.Facade;
import ua.nure.stepanenko.thesis.db.TransactionManagerFacade;
import ua.nure.stepanenko.thesis.db.connector.postgres.PostgresTransactionManagerFactory;
import ua.nure.stepanenko.thesis.exception.AppException;
import ua.nure.stepanenko.thesis.exception.ExceptionMessages;
import ua.nure.stepanenko.thesis.model.entyty.User;
import ua.nure.stepanenko.thesis.web.Paths;
import ua.nure.stepanenko.thesis.web.command.Command;
import ua.nure.stepanenko.thesis.web.validator.Validator;
import ua.nure.stepanenko.thesis.web.validator.user.LoginValidator;
import ua.nure.stepanenko.thesis.web.validator.user.PasswordValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticateCommand extends Command {

    private static final Logger LOG = Logger.getLogger(AuthenticateCommand.class);

    private static final Validator<String> LOGIN_VALIDATOR = LoginValidator.getInstance();

    private static final Validator<String> PASSWORD_VALIDATOR = PasswordValidator.getInstance();

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresTransactionManagerFactory.getInstance());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        LOG.debug("Command starts");

        String login = req.getParameter("login");
        LOG.trace(String.format("Request parameter: login --> %s", login));
        String password = req.getParameter("password");
        LOG.trace(String.format("Request parameter: password --> %s", password));

        LOGIN_VALIDATOR.validate(login);
        PASSWORD_VALIDATOR.validate(password);

        User user = FACADE.getUserByLoginAndPassword(login, password);
        checkUser(user);

        HttpSession session = req.getSession();
        session.setAttribute("user", user);

        LOG.debug("Command finished");
        return Paths.MAIN_SERVLET;
    }

    private void checkUser(User user) throws AppException {
        if (!user.isFilled()) {
            throw new AppException(ExceptionMessages.USER_NOT_EXIST_MESSAGE);
        }
    }
}
