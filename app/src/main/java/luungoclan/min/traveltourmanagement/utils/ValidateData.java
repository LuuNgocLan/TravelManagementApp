package luungoclan.min.traveltourmanagement.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateData {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean checkValidateEmail(String email) {
        if (email == null) {
            return false;
        } else {
            validate(email);
        }
        return true;
    }

    public static boolean checkValidatePassword(String password) {
        if (password == null) {
            return false;
        } else {
            if (password.length() < Common.LENGTH_PASSWORD_MIN) {
                return false;
            }
        }
        return true;
    }
}
