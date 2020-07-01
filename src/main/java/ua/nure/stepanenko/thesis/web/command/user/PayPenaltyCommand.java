package ua.nure.stepanenko.thesis.web.command.user;

import org.apache.log4j.Logger;
import ua.nure.stepanenko.thesis.db.Facade;
import ua.nure.stepanenko.thesis.db.TransactionManagerFacade;
import ua.nure.stepanenko.thesis.db.connector.postgres.PostgresTransactionManagerFactory;
import ua.nure.stepanenko.thesis.exception.AppException;
import ua.nure.stepanenko.thesis.web.Paths;
import ua.nure.stepanenko.thesis.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PayPenaltyCommand extends Command {

    private static final Logger LOG = Logger.getLogger(PayPenaltyCommand.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresTransactionManagerFactory.getInstance());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        LOG.debug("Command starts");

        int penaltyId = Integer.parseInt(req.getParameter("penaltyId"));
        LOG.trace(String.format("Request parameter: penaltyId --> %d", penaltyId));
        int orderNumber = Integer.parseInt(req.getParameter("orderNumber"));
        LOG.trace(String.format("Request parameter: orderNumber --> %d", orderNumber));

        FACADE.setNullToOrderFieldPenaltyIdByOrderNumber(orderNumber);

        LOG.debug("Command finished");
        return Paths.PERSONAL_SERVLET;
    }
}
