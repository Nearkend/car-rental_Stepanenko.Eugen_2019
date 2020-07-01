package ua.nure.stepanenko.thesis.exception.validate.user;

import ua.nure.stepanenko.thesis.exception.validate.ValidateException;

public class IncorrectPasswordValidateException extends ValidateException {

    public IncorrectPasswordValidateException(String message) {
        super(message);
    }
}
