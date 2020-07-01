package ua.nure.stepanenko.thesis.web.command.user;

import org.apache.log4j.Logger;
import ua.nure.stepanenko.thesis.db.Facade;
import ua.nure.stepanenko.thesis.db.TransactionManagerFacade;
import ua.nure.stepanenko.thesis.db.connector.postgres.PostgresTransactionManagerFactory;
import ua.nure.stepanenko.thesis.exception.AppException;
import ua.nure.stepanenko.thesis.model.entyty.User;
import ua.nure.stepanenko.thesis.web.Paths;
import ua.nure.stepanenko.thesis.web.command.Command;
import ua.nure.stepanenko.thesis.web.validator.Validator;
import ua.nure.stepanenko.thesis.web.validator.user.PassportValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SetPassportCommand extends Command {

    private static final Logger LOG = Logger.getLogger(SetPassportCommand.class);

    private static final Validator<String> PASSPORT_VALIDATOR = PassportValidator.getInstance();

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresTransactionManagerFactory.getInstance());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        LOG.debug("Command starts");

        HttpSession session = req.getSession();

        String passport = req.getParameter("passport");
        LOG.trace(String.format("Request parameter: passport --> %s", passport));
        User user = (User) session.getAttribute("user");
        LOG.trace(String.format("Session attribute: user --> %s", user));

        PASSPORT_VALIDATOR.validate(passport);

        FACADE.setUserFieldPassportByUserLogin(passport, user.getLogin());

        user.setPassport(passport);
        session.setAttribute("user", user);

        LOG.debug("Command finished");
        return Paths.PERSONAL_SERVLET;
    }
}
