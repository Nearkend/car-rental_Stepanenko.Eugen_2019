package ua.nure.stepanenko.thesis.web.validator.common;

import ua.nure.stepanenko.thesis.exception.validate.common.*;
import ua.nure.stepanenko.thesis.exception.validate.ValidateException;
import ua.nure.stepanenko.thesis.web.validator.Validator;
import ua.nure.stepanenko.thesis.web.validator.util.ValidatorUtils;

import java.util.regex.Pattern;

public class CostValidator implements Validator<String> {

    private static final String REGEX_FOR_COST = "\\d{1,9}";

    private static final Pattern PATTERN_FOR_COST = Pattern.compile(REGEX_FOR_COST);

    private static CostValidator costValidator = new CostValidator();

    private CostValidator() {
    }

    public static CostValidator getInstance() {
        return costValidator;
    }

    @Override
    public void validate(String cost) throws ValidateException {
        if (cost == null || !ValidatorUtils.suitPatter(PATTERN_FOR_COST, cost)) {
            throw new IncorrectCostValidateException("Incorrect cost, " + System.lineSeparator() +
                    "cost consist only numbers and max length of cost - 9 and min length - 1");
        }
    }
}
