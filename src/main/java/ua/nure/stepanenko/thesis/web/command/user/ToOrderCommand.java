package ua.nure.stepanenko.thesis.web.command.user;

import org.apache.log4j.Logger;
import ua.nure.stepanenko.thesis.exception.AppException;
import ua.nure.stepanenko.thesis.model.entyty.Car;
import ua.nure.stepanenko.thesis.web.Paths;
import ua.nure.stepanenko.thesis.web.command.Command;
import ua.nure.stepanenko.thesis.web.command.util.CommandUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToOrderCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ToOrderCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        LOG.debug("Command starts");

        Car car = CommandUtils.getCarFromRequest(req);
        LOG.trace(String.format("Request parameter: car --> %s", car.toString()));
        req.setAttribute("car", car);
        LOG.debug("Command finished");
        return Paths.ORDER_PAGE;
    }
}
