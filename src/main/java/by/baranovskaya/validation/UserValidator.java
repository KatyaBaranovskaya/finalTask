package by.baranovskaya.validation;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private final static String NAMING_REGEX = "^[A-ZА-Я][a-zа-я]+";
    private final static String EMAIL_REGEX = "^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,3}";
    private final static String TELEPHONE_REGEX = "([^-][^A-Za-z.]+)";
    private final static String LOGIN_REGEX = "^[A-Za-z][A-Za-z0-9_]+";
    private final static String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z]).{4,}";
    private final static double PARAM_DIVISION = 3.114E+10;

    private static boolean isDataMatch(String data, String regex){
        System.out.println(data);
        if (data == null || data.isEmpty()){
            return false;
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);

        return matcher.matches();
    }

    private static boolean isDateMatch(Date dataBirth){
        if (dataBirth == null) {
            return false;
        }
        Date date = new Date();
        int diff = (int) ((date.getTime() - dataBirth.getTime()) / PARAM_DIVISION);
        return diff > 7;
    }

    public static boolean validateLoginPassword(String loginValue, String passValue) {
        return isDataMatch(loginValue, LOGIN_REGEX)
                && isDataMatch(passValue, PASSWORD_REGEX);
    }

    public static boolean validateLoginEmail(String login, String email) {
          return isDataMatch(login, LOGIN_REGEX)
                  && isDataMatch(email, EMAIL_REGEX);
    }

    public static boolean validatePassword(String lastPassword, String newPassword) {
        return isDataMatch(lastPassword, PASSWORD_REGEX)
                && isDataMatch(newPassword, PASSWORD_REGEX);
    }

    public static boolean validateAvatar(String avatar) {
        return (avatar != null && !avatar.isEmpty());
    }

    public static boolean validateRegistration(String email, String login, String password, String surname, String name,
                                               String middleName, Date dateBirth, String telephone) {
        return isDataMatch(surname, NAMING_REGEX)
                && isDataMatch(name, NAMING_REGEX)
                && isDataMatch(middleName, NAMING_REGEX)
                && isDataMatch(email, EMAIL_REGEX)
                && isDataMatch(telephone, TELEPHONE_REGEX)
                //&& isDateMatch(user.getDateBirth())
                && isDataMatch(login, LOGIN_REGEX)
                && isDataMatch(password, PASSWORD_REGEX);

    }

    public static boolean validateUserInfo(String surname, String name, String middleName, Date dateBirth, String telephone) {
        return isDataMatch(surname, NAMING_REGEX)
                && isDataMatch(name, NAMING_REGEX)
                && isDataMatch(middleName, NAMING_REGEX)
                //&& isDateMatch(user.getDateBirth())
                && isDataMatch(telephone, TELEPHONE_REGEX);

    }
}
