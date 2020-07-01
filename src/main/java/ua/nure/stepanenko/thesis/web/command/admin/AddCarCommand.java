package ua.nure.stepanenko.thesis.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.stepanenko.thesis.db.Facade;
import ua.nure.stepanenko.thesis.db.TransactionManagerFacade;
import ua.nure.stepanenko.thesis.db.connector.postgres.PostgresTransactionManagerFactory;
import ua.nure.stepanenko.thesis.exception.AppException;
import ua.nure.stepanenko.thesis.exception.validate.ValidateException;
import ua.nure.stepanenko.thesis.model.constant.Class;
import ua.nure.stepanenko.thesis.model.constant.Mark;
import ua.nure.stepanenko.thesis.model.entyty.Car;
import ua.nure.stepanenko.thesis.web.Paths;
import ua.nure.stepanenko.thesis.web.command.Command;
import ua.nure.stepanenko.thesis.web.validator.Validator;
import ua.nure.stepanenko.thesis.web.validator.car.*;
import ua.nure.stepanenko.thesis.web.validator.common.CostValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCarCommand extends Command {

    private static final Logger LOG = Logger.getLogger(AddCarCommand.class);

    private static final Validator<String> NAME_VALIDATOR = NameValidator.getInstance();

    private static final Validator<String> COST_VALIDATOR = CostValidator.getInstance();

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresTransactionManagerFactory.getInstance());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, AppException {
        LOG.debug("Command starts");

        Car car = getCarFromRequest(req);

        FACADE.createCar(car);

        LOG.debug("Command finished");
        return Paths.CARS_SERVLET;
    }

    private Car getCarFromRequest(HttpServletRequest req) throws ValidateException {
        Car car = new Car();
        Class clazz = Class.valueOf(req.getParameter("class"));
        LOG.trace(String.format("Request parameter: class --> %s", clazz));
        Mark mark = Mark.valueOf(req.getParameter("mark"));
        LOG.trace(String.format("Request parameter: mark --> %s", mark));
        String name = req.getParameter("name");
        NAME_VALIDATOR.validate(name);
        LOG.trace(String.format("Request parameter: name --> %s", name));
        String costStr = req.getParameter("cost");
        COST_VALIDATOR.validate(costStr);
        int cost = Integer.parseInt(costStr);
        LOG.trace(String.format("Request parameter: cost --> %s", cost));

        car.setClazz(clazz);
        car.setMark(mark);
        car.setName(name);
        car.setCost(cost);

        return car;
    }
}
