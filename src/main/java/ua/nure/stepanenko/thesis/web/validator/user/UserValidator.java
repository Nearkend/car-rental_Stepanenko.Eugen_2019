package ua.nure.stepanenko.thesis.web.validator.user;

import ua.nure.stepanenko.thesis.exception.validate.ValidateException;
import ua.nure.stepanenko.thesis.model.entyty.User;
import ua.nure.stepanenko.thesis.web.validator.Validator;

public class UserValidator implements Validator<User> {

    private static final Validator<String> FULL_NAME_VALIDATOR = FullNameValidator.getInstance();
    private static final Validator<String> LOGIN_VALIDATOR = LoginValidator.getInstance();
    private static final Validator<String> PASSWORD_VALIDATOR = PasswordValidator.getInstance();

    private static UserValidator userValidator = new UserValidator();

    private UserValidator() {
    }

    public static UserValidator getInstance(){
        return userValidator;
    }

    @Override
    public void validate(User user) throws ValidateException {
        FULL_NAME_VALIDATOR.validate(user.getFullName());
        LOGIN_VALIDATOR.validate(user.getLogin());
        PASSWORD_VALIDATOR.validate(user.getPassword());
    }
}
