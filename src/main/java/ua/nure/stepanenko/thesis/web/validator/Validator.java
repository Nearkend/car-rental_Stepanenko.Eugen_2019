package ua.nure.stepanenko.thesis.web.validator;

import ua.nure.stepanenko.thesis.exception.validate.ValidateException;

public interface Validator<T> {

    void  validate(T field) throws ValidateException;
}
