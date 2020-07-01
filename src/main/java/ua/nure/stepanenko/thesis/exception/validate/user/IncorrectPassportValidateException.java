package ua.nure.stepanenko.thesis.exception.validate.user;

import ua.nure.stepanenko.thesis.exception.validate.ValidateException;

public class IncorrectPassportValidateException extends ValidateException {

    public IncorrectPassportValidateException(String message) {
        super(message);
    }
}
