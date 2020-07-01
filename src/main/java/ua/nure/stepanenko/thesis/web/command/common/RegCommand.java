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
import ua.nure.stepanenko.thesis.web.command.util.CommandUtils;
import ua.nure.stepanenko.thesis.web.validator.Validator;
import ua.nure.stepanenko.thesis.web.validator.user.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegCommand extends Command {

    private static final Logger LOG = Logger.getLogger(RegCommand.class);

    private static final Validator<User> USER_VALIDATOR = UserValidator.getInstance();

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresTransactionManagerFactory.getInstance());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        LOG.debug("Command starts");

        checkCaptcha(req);

        User user = CommandUtils.getUserFromRequest(req);
        LOG.trace(String.format("Request parameter: user --> %s", user.toString()));

        USER_VALIDATOR.validate(user);

        LOG.trace(String.format("User created (true, false) --> %b", FACADE.createUser(user)));

        LOG.debug("Command finished");
        return Paths.AUTHENTICATE_SERVLET;
    }

    private void checkCaptcha(HttpServletRequest req) throws AppException {
        String gRecaptchaResponse = req
                .getParameter("g-recaptcha-response");

        if (gRecaptchaResponse.isEmpty()) {
            throw new AppException(ExceptionMessages.MAYBE_YOUR_ROBOT_MESSAGE);
        }
    }
}
