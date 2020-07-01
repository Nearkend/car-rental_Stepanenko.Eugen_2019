package ua.nure.stepanenko.thesis.web.command.manager;

import org.apache.log4j.Logger;
import ua.nure.stepanenko.thesis.db.Facade;
import ua.nure.stepanenko.thesis.db.TransactionManagerFacade;
import ua.nure.stepanenko.thesis.db.connector.postgres.PostgresTransactionManagerFactory;
import ua.nure.stepanenko.thesis.exception.AppException;
import ua.nure.stepanenko.thesis.model.entyty.StateCounter;
import ua.nure.stepanenko.thesis.web.Paths;
import ua.nure.stepanenko.thesis.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ToStateCountCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ToStateCountCommand.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresTransactionManagerFactory.getInstance());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        LOG.debug("Command starts");

        List<StateCounter> stateCounters = FACADE.getStateCountOnOrders();
        LOG.trace(String.format("stateCounters --> %s", stateCounters.toString()));
        req.setAttribute("stateCounters", stateCounters);

        LOG.debug("Command finished");
        return Paths.STATE_STATS_PAGE;
    }
}
