package ua.nure.stepanenko.thesis.web.command.user;

import org.apache.log4j.Logger;
import ua.nure.stepanenko.thesis.db.Facade;
import ua.nure.stepanenko.thesis.db.TransactionManagerFacade;
import ua.nure.stepanenko.thesis.db.connector.postgres.PostgresTransactionManagerFactory;
import ua.nure.stepanenko.thesis.exception.AppException;
import ua.nure.stepanenko.thesis.exception.validate.ValidateException;
import ua.nure.stepanenko.thesis.model.constant.State;
import ua.nure.stepanenko.thesis.model.entyty.Car;
import ua.nure.stepanenko.thesis.model.entyty.Order;
import ua.nure.stepanenko.thesis.model.entyty.User;
import ua.nure.stepanenko.thesis.web.Paths;
import ua.nure.stepanenko.thesis.web.command.Command;
import ua.nure.stepanenko.thesis.web.command.util.CommandUtils;
import ua.nure.stepanenko.thesis.web.validator.Validator;
import ua.nure.stepanenko.thesis.web.validator.common.DateValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class OrderCommand extends Command {

    private static final Logger LOG = Logger.getLogger(OrderCommand.class);

    private static final Validator<String> DATE_VALIDATOR = DateValidator.getInstance();

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresTransactionManagerFactory.getInstance());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        LOG.debug("Command starts");

        Order order = getOrder(req);

        FACADE.createOrder(order);

        LOG.debug("Command finished");
        return Paths.MAIN_SERVLET;
    }

    private Order getOrder(HttpServletRequest req) throws ValidateException {
        Car car = CommandUtils.getCarFromRequest(req);
        LOG.trace(String.format("Request parameter: car --> %s", car.toString()));
        User user = (User) req.getSession().getAttribute("user");
        LOG.trace(String.format("Session attribute: user --> %s", user.toString()));
        boolean driver = Boolean.parseBoolean(req.getParameter("driver"));
        LOG.trace(String.format("Request parameter: driver --> %b", driver));
        String strTerm = req.getParameter("term");
        DATE_VALIDATOR.validate(strTerm);
        Date term = Date.valueOf(strTerm);
        LOG.trace(String.format("Request parameter: term --> %s", term.toString()));

        Order order = new Order();
        order.setTerm(term);
        order.setDriver(driver);
        order.setUser(user);
        order.setCar(car);
        order.setState(State.EXPECTATION);
        return order;
    }
}
