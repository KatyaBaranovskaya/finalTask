package by.baranovskaya.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceValidator {
    private final static String TYPE_SERVICE_REGEX = "[A-ZА-Я0-9][a-zа-я0-9\\-\\\"\\(\\)\\s]+";
    private final static String DESCRIPTION_REGEX = "[A-ZА-Я0-9][a-zа-я0-9\\-\\,\\.\\\"\\:\\;\\(\\)\\s]+";

    private static boolean isDataMatch(String data, String regex){
        if (data == null || data.isEmpty()){
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);

        return matcher.matches();
    }

    public static boolean validateService(String typeService, String description, String image) {
        return isDataMatch(typeService, TYPE_SERVICE_REGEX)
                && isDataMatch(description, DESCRIPTION_REGEX)
                && !image.isEmpty();

    }
}
