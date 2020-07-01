package ua.nure.stepanenko.thesis.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.stepanenko.thesis.db.Facade;
import ua.nure.stepanenko.thesis.db.TransactionManagerFacade;
import ua.nure.stepanenko.thesis.db.connector.postgres.PostgresTransactionManagerFactory;
import ua.nure.stepanenko.thesis.exception.AppException;
import ua.nure.stepanenko.thesis.web.Paths;
import ua.nure.stepanenko.thesis.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCarCommand extends Command {

    private static final Logger LOG = Logger.getLogger(DeleteCarCommand.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresTransactionManagerFactory.getInstance());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        LOG.debug("Command starts");

        int carId = Integer.parseInt(req.getParameter("carId"));
        LOG.trace(String.format("Request parameter: carId --> %d", carId));

        FACADE.deleteCarById(carId);

        LOG.debug("Command finished");
        return Paths.CARS_SERVLET;
    }
}
