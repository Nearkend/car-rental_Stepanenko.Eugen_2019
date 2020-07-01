package ua.nure.stepanenko.thesis.web.validator.common;

import ua.nure.stepanenko.thesis.exception.validate.IncorrectLanguageValidateException;
import ua.nure.stepanenko.thesis.exception.validate.ValidateException;
import ua.nure.stepanenko.thesis.web.validator.Validator;
import ua.nure.stepanenko.thesis.web.validator.util.ValidatorUtils;

import java.util.regex.Pattern;

public class LanguageValidator implements Validator<String> {

    private static final String REGEX_FOR_LANGUAGE = "\\w{2}";

    private static final Pattern PATTERN_FOR_LANGUAGE = Pattern.compile(REGEX_FOR_LANGUAGE);

    private static LanguageValidator dateValidator = new LanguageValidator();

    private LanguageValidator() {
    }

    public static LanguageValidator getInstance() {
        return dateValidator;
    }

    @Override
    public void validate(String language) throws ValidateException {
        if (language == null || !ValidatorUtils.suitPatter(PATTERN_FOR_LANGUAGE, language)) {
            throw new IncorrectLanguageValidateException("Incorrect language");
        }
    }
}
