package ua.nure.stepanenko.thesis.web.validator.prnalty;

import ua.nure.stepanenko.thesis.exception.validate.user.IncorrectLoginValidateException;
import ua.nure.stepanenko.thesis.exception.validate.ValidateException;
import ua.nure.stepanenko.thesis.web.validator.Validator;
import ua.nure.stepanenko.thesis.web.validator.util.ValidatorUtils;

import java.util.regex.Pattern;

public class CauseValidator implements Validator<String> {

    private static final String REGEX_FOR_CAUSE = "\\pL{16,96}";

    private static final Pattern PATTERN_FOR_CAUSE = Pattern.compile(REGEX_FOR_CAUSE);

    private static CauseValidator causeValidator = new CauseValidator();

    private CauseValidator() {
    }

    public static CauseValidator getInstance(){
        return causeValidator;
    }
    @Override
    public void validate(String cause) throws ValidateException {
        if (cause == null || !ValidatorUtils.suitPatter(PATTERN_FOR_CAUSE, cause)) {
            throw new IncorrectLoginValidateException("Incorrect penalty cause, " + System.lineSeparator() +
                    "max length of cause - 96, min length - 16");
        }
    }
}
