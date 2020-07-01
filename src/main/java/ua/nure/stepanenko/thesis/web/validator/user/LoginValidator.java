package ua.nure.stepanenko.thesis.web.validator.user;

import ua.nure.stepanenko.thesis.exception.validate.user.IncorrectLoginValidateException;
import ua.nure.stepanenko.thesis.exception.validate.ValidateException;
import ua.nure.stepanenko.thesis.web.validator.Validator;
import ua.nure.stepanenko.thesis.web.validator.util.ValidatorUtils;

import java.util.regex.Pattern;

public class LoginValidator implements Validator<String> {

    private static final String REGEX_FOR_LOGIN = "\\pL{5,16}";

    private static final Pattern PATTERN_FOR_LOGIN = Pattern.compile(REGEX_FOR_LOGIN);

    private static LoginValidator loginValidator = new LoginValidator();

    private LoginValidator() {
    }

    public static LoginValidator getInstance(){
        return loginValidator;
    }

    @Override
    public void validate(String login) throws ValidateException {
        if (login == null || !ValidatorUtils.suitPatter(PATTERN_FOR_LOGIN, login)) {
            throw new IncorrectLoginValidateException("Incorrect user login, " + System.lineSeparator() +
                    "user login consist only latter, and " + System.lineSeparator() +
                    "max length of login - 16, min length - 5");
        }
    }
}
