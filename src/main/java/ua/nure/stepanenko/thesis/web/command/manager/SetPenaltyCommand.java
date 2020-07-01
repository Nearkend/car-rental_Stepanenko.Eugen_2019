package ua.nure.stepanenko.thesis.web.command.manager;

import org.apache.log4j.Logger;
import ua.nure.stepanenko.thesis.db.Facade;
import ua.nure.stepanenko.thesis.db.TransactionManagerFacade;
import ua.nure.stepanenko.thesis.db.connector.postgres.PostgresTransactionManagerFactory;
import ua.nure.stepanenko.thesis.exception.AppException;
import ua.nure.stepanenko.thesis.model.been.Penalty;
import ua.nure.stepanenko.thesis.web.Paths;
import ua.nure.stepanenko.thesis.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetPenaltyCommand extends Command {

    private static final Logger LOG = Logger.getLogger(SetPenaltyCommand.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresTransactionManagerFactory.getInstance());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        LOG.debug("Command starts");

        int orderNumber = Integer.parseInt(req.getParameter("orderNumber"));
        LOG.trace(String.format("Request parameter: orderNumber --> %d", orderNumber));
        Penalty penalty = getPenaltyFromRequest(req);

        FACADE.createAndSetOrderFieldPenaltyIdByOrderNumber(penalty, orderNumber);

        LOG.debug("Command finished");
        return Paths.MANAGER_ORDERS_SERVLET;
    }

    private Penalty getPenaltyFromRequest(HttpServletRequest req) {
        Penalty penalty = new Penalty();
        String cause = req.getParameter("cause");
        LOG.trace(String.format("Request parameter: cause --> %s", cause));
        int toPay= Integer.parseInt(req.getParameter("toPay"));
        LOG.trace(String.format("Request parameter: toPay --> %d", toPay));
        penalty.setCause(cause);
        penalty.setCost(toPay);
        return penalty;
    }
}
