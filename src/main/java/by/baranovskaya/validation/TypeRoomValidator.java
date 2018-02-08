package by.baranovskaya.validation;

import by.baranovskaya.entity.TypeRoom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TypeRoomValidator {
    private final static String TYPE_ROOM_REGEX = "[\\w\\+\\s]+";
    private final static String NUMBER_REGEX = "[0-9]";
    private final static String PRICE_REGEX = "\\d+(\\.\\d{0,})?";
    private final static String DESCRIPTION_REGEX = "[\\W|\\w|\\s]+";

    private static boolean isDataMatch(String data, String regex){
        if (data == null || data.isEmpty()){
            return false;
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);

        return matcher.matches();
    }

    public static boolean validateTypeRoom(String typeApartment, int capacity, double price, String description, String image) {
        return isDataMatch(typeApartment, TYPE_ROOM_REGEX)
                && isDataMatch(String.valueOf(capacity), NUMBER_REGEX)
                && isDataMatch(String.valueOf(price), PRICE_REGEX)
                && isDataMatch(description, DESCRIPTION_REGEX)
                && !image.isEmpty();

    }

    public static boolean validateSearchParam(int minPrice, int maxPrice){
        return (minPrice >= 0 && maxPrice > 0 && maxPrice > minPrice);
    }
}
