package ua.nure.stepanenko.thesis.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.stepanenko.thesis.db.Facade;
import ua.nure.stepanenko.thesis.db.TransactionManagerFacade;
import ua.nure.stepanenko.thesis.db.connector.postgres.PostgresTransactionManagerFactory;
import ua.nure.stepanenko.thesis.exception.AppException;
import ua.nure.stepanenko.thesis.exception.ExceptionMessages;
import ua.nure.stepanenko.thesis.model.entyty.Car;
import ua.nure.stepanenko.thesis.web.Paths;
import ua.nure.stepanenko.thesis.web.command.Command;
import ua.nure.stepanenko.thesis.web.command.util.CommandUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SearchCarsCommand extends Command {

    private static final Logger LOG = Logger.getLogger(SearchCarsCommand.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresTransactionManagerFactory.getInstance());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        LOG.debug("Command starts");

        String by = req.getParameter("by");
        LOG.trace(String.format("Request parameter: by --> %s", by));
        String sort = req.getParameter("sort");
        LOG.trace(String.format("Request parameter: sort --> %s", sort));
        String looking = req.getParameter("looking");
        LOG.trace(String.format("Request parameter: looking --> %s", looking));

        CommandUtils.checkLookingCounter(req.getSession(), looking);

        List<Car> cars = getCars(by, sort, looking);

        LOG.trace(String.format("List of cars --> %s", cars));

        req.setAttribute("cars", cars);

        LOG.debug("Command finished");
        return Paths.MAIN_PAGE;
    }

    private List<Car> getCars(String by, String sort, String looking) throws AppException {
        List<Car> cars;
        if ("class".equals(by)) {
            cars = FACADE.getCarsByClass(looking, sort);
        } else if ("mark".equals(by)) {
            cars = FACADE.getCarsByMark(looking, sort);
        } else {
            throw new AppException(ExceptionMessages.INCORRECT_SEARCH_PARAMETER_MESSAGE);
        }
        return cars;
    }
}
