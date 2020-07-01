package ua.nure.stepanenko.thesis.web.validator.user;

import ua.nure.stepanenko.thesis.exception.validate.user.IncorrectPasswordValidateException;
import ua.nure.stepanenko.thesis.exception.validate.ValidateException;
import ua.nure.stepanenko.thesis.web.validator.Validator;
import ua.nure.stepanenko.thesis.web.validator.util.ValidatorUtils;

import java.util.regex.Pattern;

public class PasswordValidator implements Validator<String> {

    private static final String REGEX_FOR_PASSWORD = "\\pL{5,16}";

    private static final Pattern PATTERN_FOR_PASSWORD = Pattern.compile(REGEX_FOR_PASSWORD);

    private static PasswordValidator passwordValidator = new PasswordValidator();

    private PasswordValidator() {
    }

    public static PasswordValidator getInstance(){
        return passwordValidator;
    }

    @Override
    public void validate(String password) throws ValidateException {
        if (password == null || !ValidatorUtils.suitPatter(PATTERN_FOR_PASSWORD, password)) {
            throw new IncorrectPasswordValidateException("Incorrect user password, " + System.lineSeparator() +
                    "user password consist only latter, and " + System.lineSeparator() +
                    "max length of password - 16, min length - 5");
        }
    }
}
