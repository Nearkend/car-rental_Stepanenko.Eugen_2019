package ua.nure.stepanenko.thesis.web.command.manager;

import org.apache.log4j.Logger;
import ua.nure.stepanenko.thesis.db.Facade;
import ua.nure.stepanenko.thesis.db.TransactionManagerFacade;
import ua.nure.stepanenko.thesis.db.connector.postgres.PostgresTransactionManagerFactory;
import ua.nure.stepanenko.thesis.exception.AppException;
import ua.nure.stepanenko.thesis.model.entyty.Order;
import ua.nure.stepanenko.thesis.web.Paths;
import ua.nure.stepanenko.thesis.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ToManagerOrdersCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ToManagerOrdersCommand.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresTransactionManagerFactory.getInstance());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        LOG.debug("Command starts");

        long currentTimeMillis = System.currentTimeMillis();
        req.setAttribute("currentTimeMillis", currentTimeMillis);
        List<Order> orders = FACADE.getOrders();
        LOG.trace(String.format("Orders --> %s", orders.toString()));
        req.setAttribute("orders", orders);

        LOG.debug("Command finished");
        return Paths.ORDERS_MANAGER_PAGE;
    }
}
