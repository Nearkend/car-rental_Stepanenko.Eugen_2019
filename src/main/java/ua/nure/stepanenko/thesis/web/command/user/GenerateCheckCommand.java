package ua.nure.stepanenko.thesis.web.command.user;

import com.itextpdf.text.DocumentException;
import org.apache.log4j.Logger;
import ua.nure.stepanenko.thesis.exception.AppException;
import ua.nure.stepanenko.thesis.exception.ExceptionMessages;
import ua.nure.stepanenko.thesis.model.entyty.Car;
import ua.nure.stepanenko.thesis.model.entyty.User;
import ua.nure.stepanenko.thesis.report.TicketGenerator;
import ua.nure.stepanenko.thesis.web.Paths;
import ua.nure.stepanenko.thesis.web.command.Command;
import ua.nure.stepanenko.thesis.web.command.util.CommandUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GenerateCheckCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GenerateCheckCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        LOG.debug("Command starts");

        Car car = CommandUtils.getCarFromRequest(req);
        LOG.trace(String.format("Request parameter: car --> %s", car.toString()));
        User user = (User) req.getSession().getAttribute("user");
        LOG.trace(String.format("Session attribute: user --> %s", user.toString()));
        String realPath = req.getServletContext().getRealPath(Paths.PDF_REPORTS_PATH);
        LOG.trace(String.format("Context path --> %s", realPath));

        try {
            TicketGenerator.createTicket(realPath, user, car);
        } catch (IOException | DocumentException e) {
            throw new AppException(ExceptionMessages.COULD_NOT_CREATE_CHECK_MESSAGE);
        }

        LOG.debug("Command finished");
        return Paths.TICKET_SERVLET;
    }
}
