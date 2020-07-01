package ua.nure.stepanenko.thesis.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.stepanenko.thesis.exception.AppException;
import ua.nure.stepanenko.thesis.web.Paths;
import ua.nure.stepanenko.thesis.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToEditCarCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ToEditCarCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        LOG.debug("Command starts");

        String carId = req.getParameter("carId");
        LOG.trace(String.format("Request parameter: carId --> %s", carId));
        req.setAttribute("carId", carId);

        LOG.debug("Command finished");
        return Paths.EDIT_CAR_PAGE;
    }
}
