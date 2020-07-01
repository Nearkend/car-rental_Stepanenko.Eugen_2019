package ua.nure.stepanenko.thesis.web.command.util;

import ua.nure.stepanenko.thesis.exception.AppException;
import ua.nure.stepanenko.thesis.exception.ExceptionMessages;
import ua.nure.stepanenko.thesis.model.been.LookingCounter;
import ua.nure.stepanenko.thesis.model.constant.Class;
import ua.nure.stepanenko.thesis.model.constant.Mark;
import ua.nure.stepanenko.thesis.model.entyty.Car;
import ua.nure.stepanenko.thesis.model.entyty.User;
import ua.nure.stepanenko.thesis.web.LookingCounterWatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class CommandUtils {

    private static final int ALLOWABLE_QUANTITY_OF_SAME_SEARCHING = 5;

    private static ScheduledExecutorService service = Executors.newScheduledThreadPool(2);

    private CommandUtils() {
    }

    public static User getUserFromRequest(HttpServletRequest req) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String fullName = req.getParameter("fullName");
        String passport = req.getParameter("passport");

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setPassport(passport);
        return user;
    }

    public static Car getCarFromRequest(HttpServletRequest req) {
        Car car = new Car();
        int carId = Integer.parseInt(req.getParameter("carId"));
        Mark carMark = Mark.valueOf(req.getParameter("carMark"));
        Class carClass = Class.valueOf(req.getParameter("carClass"));
        String carName = req.getParameter("carName");
        int carCost = Integer.parseInt(req.getParameter("carCost"));
        boolean carThereIs = Boolean.parseBoolean(req.getParameter("carThereIs"));
        car.setId(carId);
        car.setMark(carMark);
        car.setClazz(carClass);
        car.setName(carName);
        car.setCost(carCost);
        car.setThereIs(carThereIs);
        return car;
    }

    public static void checkLookingCounter(HttpSession session, String looking) throws AppException {
        LookingCounter lookingCounter = (LookingCounter) session.getAttribute("lookingCounter");

        if (lookingCounter == null) {
            lookingCounter = new LookingCounter();
            lookingCounter.setLooking(looking);
            session.setAttribute("lookingCounter", lookingCounter);
        }

        if (lookingCounter.getLooking().equals(looking)) {

            if (lookingCounter.getCount().incrementAndGet() > ALLOWABLE_QUANTITY_OF_SAME_SEARCHING) {
                service.schedule(new LookingCounterWatcher(lookingCounter), 1, TimeUnit.MINUTES);
                throw new AppException(ExceptionMessages.YOU_TRY_TO_ENTER_THE_SAME_SEARCH_MESSAGE);
            }
        } else {
            lookingCounter.setCount(1);
            lookingCounter.setLooking(looking);
        }
    }

    public static ScheduledExecutorService getService() {
        return service;
    }
}
