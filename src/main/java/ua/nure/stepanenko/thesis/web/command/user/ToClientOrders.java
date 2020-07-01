package ua.nure.stepanenko.thesis.web.command.user;

import org.apache.log4j.Logger;
import ua.nure.stepanenko.thesis.db.Facade;
import ua.nure.stepanenko.thesis.db.TransactionManagerFacade;
import ua.nure.stepanenko.thesis.db.connector.postgres.PostgresTransactionManagerFactory;
import ua.nure.stepanenko.thesis.exception.AppException;
import ua.nure.stepanenko.thesis.model.entyty.Order;
import ua.nure.stepanenko.thesis.model.entyty.User;
import ua.nure.stepanenko.thesis.web.Paths;
import ua.nure.stepanenko.thesis.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ToClientOrders extends Command {

    private static final Logger LOG = Logger.getLogger(ToClientOrders.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresTransactionManagerFactory.getInstance());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        LOG.debug("Command starts");

        long currentTimeMillis = System.currentTimeMillis();
        req.setAttribute("currentTimeMillis", currentTimeMillis);
        User user = (User) req.getSession().getAttribute("user");
        LOG.trace(String.format("Session parameter: user --> %s", user.toString()));

        List<Order> orders = FACADE.getOrdersByUserLogin(user.getLogin());
        LOG.trace(String.format("Orders --> %s", orders));
        req.setAttribute("orders", orders);

        LOG.debug("Command finished");
        return Paths.ORDERS_CLIENT_PAGE;
    }
}
