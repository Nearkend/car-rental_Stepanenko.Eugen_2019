package ua.nure.stepanenko.thesis.web.validator.car;

import ua.nure.stepanenko.thesis.exception.validate.car.IncorrectNameValidateException;
import ua.nure.stepanenko.thesis.exception.validate.ValidateException;
import ua.nure.stepanenko.thesis.web.validator.Validator;
import ua.nure.stepanenko.thesis.web.validator.util.ValidatorUtils;

import java.util.regex.Pattern;

public class NameValidator implements Validator<String> {

    private static final String REGEX_FOR_NAME = "(\\pL||\\s||\\d){2,16}";

    private static final Pattern PATTERN_FOR_NAME = Pattern.compile(REGEX_FOR_NAME);

    private static NameValidator nameValidator = new NameValidator();

    private NameValidator() {
    }

    public static NameValidator getInstance(){
        return nameValidator;
    }

    @Override
    public void validate(String name) throws ValidateException {
        if (name == null || !ValidatorUtils.suitPatter(PATTERN_FOR_NAME, name)) {
            throw new IncorrectNameValidateException("Incorrect car name, " + System.lineSeparator() +
                    "car name consist only latter, spaces, numbers, and " + System.lineSeparator() +
                    "max length of name - 16, min length - 2");
        }
    }
}
