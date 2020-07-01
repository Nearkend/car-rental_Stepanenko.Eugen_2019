package ua.nure.stepanenko.thesis.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.stepanenko.thesis.exception.AppException;
import ua.nure.stepanenko.thesis.web.Paths;
import ua.nure.stepanenko.thesis.web.command.Command;
import ua.nure.stepanenko.thesis.web.validator.Validator;
import ua.nure.stepanenko.thesis.web.validator.common.LanguageValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLanguageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ChangeLanguageCommand.class);

    private static final Validator<String> LANGUAGE_VALIDATOR = LanguageValidator.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        LOG.debug("Command starts");

        String language = req.getParameter("language");
        LOG.trace(String.format("Request parameter: language --> %s", language));
        LANGUAGE_VALIDATOR.validate(language);
        req.getSession().setAttribute("language", language);

        LOG.debug("Command finished");
        return Paths.MAIN_PAGE;
    }
}
