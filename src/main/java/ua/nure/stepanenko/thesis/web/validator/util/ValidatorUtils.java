package ua.nure.stepanenko.thesis.web.validator.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ValidatorUtils {

    private ValidatorUtils() {

    }

    public static boolean suitPatter(Pattern pattern, String text) {
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
}
