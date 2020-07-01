package ua.nure.stepanenko.thesis.web.validator.common;

import ua.nure.stepanenko.thesis.exception.validate.common.IncorrectDateValidateException;
import ua.nure.stepanenko.thesis.exception.validate.ValidateException;
import ua.nure.stepanenko.thesis.web.validator.Validator;
import ua.nure.stepanenko.thesis.web.validator.util.ValidatorUtils;

import java.util.regex.Pattern;

public class DateValidator implements Validator<String> {

    private static final String REGEX_FOR_DATE = "\\d{4}-\\d{2}-\\d{2}";

    private static final Pattern PATTERN_FOR_DATE = Pattern.compile(REGEX_FOR_DATE);

    private static DateValidator dateValidator = new DateValidator();

    private DateValidator() {
    }

    public static DateValidator getInstance() {
        return dateValidator;
    }

    @Override
    public void validate(String date) throws ValidateException {
        if (date == null || !ValidatorUtils.suitPatter(PATTERN_FOR_DATE, date)) {
            throw new IncorrectDateValidateException("Incorrect date");
        }
    }
}
