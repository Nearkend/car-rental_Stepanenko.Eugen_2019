package ua.nure.stepanenko.thesis.exception.db;

import ua.nure.stepanenko.thesis.exception.AppException;

public class DBException extends AppException {

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }
}
