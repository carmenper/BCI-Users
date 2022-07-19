package com.cemp.bci.users.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

    private ValidationUtil() {}
    private static final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private static final String P_REGEX = "^(?=(?:.*[A-Z].*){1})(?!(?:.*[A-Z].*){2,})(?=(?:.*\\d.*){2})(?!(?:.*\\d.*){3,})(?=(?:.*[a-z]{5,}.*))(?!(?:.*[a-z]{10,}.*)).*$";

    public static boolean isValidEmail(String string) {
        return isValid(string, EMAIL_REGEX);
    }

    public static boolean isValidPassword(String string) {
        return isValid(string, P_REGEX);
    }

    private static boolean isValid(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

}
