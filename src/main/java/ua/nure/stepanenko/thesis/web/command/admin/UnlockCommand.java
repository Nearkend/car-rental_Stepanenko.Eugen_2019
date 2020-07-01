package ua.nure.stepanenko.thesis.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.stepanenko.thesis.db.Facade;
import ua.nure.stepanenko.thesis.db.TransactionManagerFacade;
import ua.nure.stepanenko.thesis.db.connector.postgres.PostgresTransactionManagerFactory;
import ua.nure.stepanenko.thesis.exception.AppException;
import ua.nure.stepanenko.thesis.web.Paths;
import ua.nure.stepanenko.thesis.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnlockCommand extends Command {

    private static final Logger LOG = Logger.getLogger(UnlockCommand.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresTransactionManagerFactory.getInstance());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        LOG.debug("Command starts");

        String login = req.getParameter("login");
        LOG.trace(String.format("Request parameter: login --> %s", login));

        FACADE.setUserFieldBlockedByUserLogin(false, login);

        LOG.debug("Command finished");
        return Paths.PERMISSION_SERVLET;
    }
}
