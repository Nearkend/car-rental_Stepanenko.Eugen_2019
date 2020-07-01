package ua.nure.stepanenko.thesis.exception.validate.user;

import ua.nure.stepanenko.thesis.exception.validate.ValidateException;

public class IncorrectLoginValidateException extends ValidateException {

    public IncorrectLoginValidateException(String message) {
        super(message);
    }
}
