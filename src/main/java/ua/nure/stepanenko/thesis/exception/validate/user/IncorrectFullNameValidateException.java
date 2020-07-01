package ua.nure.stepanenko.thesis.exception.validate.user;

import ua.nure.stepanenko.thesis.exception.validate.ValidateException;

public class IncorrectFullNameValidateException extends ValidateException {

    public IncorrectFullNameValidateException(String message) {
        super(message);
    }
}
