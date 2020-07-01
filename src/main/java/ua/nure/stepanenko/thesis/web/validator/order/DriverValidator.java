package ua.nure.stepanenko.thesis.web.validator.order;

import ua.nure.stepanenko.thesis.exception.validate.order.IncorrectDriverValidateException;
import ua.nure.stepanenko.thesis.exception.validate.ValidateException;
import ua.nure.stepanenko.thesis.web.validator.Validator;
import ua.nure.stepanenko.thesis.web.validator.util.ValidatorUtils;

import java.util.regex.Pattern;

public class DriverValidator implements Validator<String> {

    private static final String REGEX_FOR_DRIVER = "true||false";

    private static final Pattern PATTERN_FOR_DRIVER = Pattern.compile(REGEX_FOR_DRIVER);

    private static DriverValidator driverValidator = new DriverValidator();

    private DriverValidator() {
    }

    public static DriverValidator getInstance() {
        return driverValidator;
    }

    @Override
    public void validate(String driver) throws ValidateException {
        if (driver == null || !ValidatorUtils.suitPatter(PATTERN_FOR_DRIVER, driver)) {
            throw new IncorrectDriverValidateException(
                    "Incorrect driver, " + System.lineSeparator() +
                            "driver must be true (have) or false (have no)");
        }
    }
}
