package ua.nure.stepanenko.thesis.web.command;

import ua.nure.stepanenko.thesis.exception.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Command {

    public abstract String execute(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, AppException;

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }
}
