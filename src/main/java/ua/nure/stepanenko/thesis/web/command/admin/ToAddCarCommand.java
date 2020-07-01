package ua.nure.stepanenko.thesis.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.stepanenko.thesis.exception.AppException;
import ua.nure.stepanenko.thesis.web.Paths;
import ua.nure.stepanenko.thesis.web.command.Command;
import ua.nure.stepanenko.thesis.web.command.common.AuthenticateCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToAddCarCommand extends Command {

    private static final Logger LOG = Logger.getLogger(AuthenticateCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, AppException {
        LOG.debug("Command starts");
        // no op
        LOG.debug("Command finished");
        return Paths.ADD_CAR_PAGE;
    }
}
