package by.baranovskaya.validation;

import by.baranovskaya.entity.TypeRoom;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {
    private final static String NAMING_REGEX = "^[A-ZА-Я][a-zа-я]+";
    private final static String EMAIL_REGEX = "^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,3}";
    private final static String TELEPHONE_REGEX = "([^-][^A-Za-z.]+)";
    private final static String LOGIN_REGEX = "^[A-Za-z][A-Za-z0-9_]+";
    private final static String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z]).{4,}";
    private final static String DATE_REGEX = "(\\d{4})\\-(0\\d|1[012])\\-([0-2]\\d|3[01])";
    private final static String TYPE_SERVICE_REGEX = "[A-ZА-Я0-9][a-zа-я0-9\\-\\\"\\(\\)\\s]+";
    private final static String DESCRIPTION_REGEX = "[A-ZА-Я0-9][a-zа-я0-9\\-\\,\\.\\\"\\:\\;\\(\\)\\s]+";
    private final static String TYPE_ROOM_REGEX = "[\\w\\+\\s]+";
    private final static String NUMBER_REGEX = "[0-9]";
    private final static String NUMBERS_REGEX = "[0-9]+";
    private final static String PRICE_REGEX = "\\d+(\\.\\d{0,})?";
    private final static String STATUS_REGEX = "Бронирован|Свободен";
    private final static String BREAKFAST_REGEX = "да|нет";

    private static boolean isDataMatch(String data, String regex) {
        if (data == null || data.isEmpty()) {
            return false;
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);

        return matcher.matches();
    }

    public static boolean validatePassword(String lastPassword, String newPassword) {
        return isDataMatch(lastPassword, PASSWORD_REGEX)
                && isDataMatch(newPassword, PASSWORD_REGEX);
    }

    public static boolean validateLoginPassword(String loginValue, String passValue) {
        return isDataMatch(loginValue, LOGIN_REGEX)
                && isDataMatch(passValue, PASSWORD_REGEX);
    }

    public static boolean validateLoginEmail(String login, String email) {
        return isDataMatch(login, LOGIN_REGEX)
                && isDataMatch(email, EMAIL_REGEX);
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
                && isDataMatch(String.valueOf(dateBirth), DATE_REGEX)
                && isDataMatch(login, LOGIN_REGEX)
                && isDataMatch(password, PASSWORD_REGEX);

    }

    public static boolean validateUserInfo(String surname, String name, String middleName, Date dateBirth, String telephone) {
        return isDataMatch(surname, NAMING_REGEX)
                && isDataMatch(name, NAMING_REGEX)
                && isDataMatch(middleName, NAMING_REGEX)
                && isDataMatch(String.valueOf(dateBirth), DATE_REGEX)
                && isDataMatch(telephone, TELEPHONE_REGEX);

    }

    public static boolean validateService(String typeService, String description, String image) {
        return isDataMatch(typeService, TYPE_SERVICE_REGEX)
                && isDataMatch(description, DESCRIPTION_REGEX)
                && !image.isEmpty();

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

    public static boolean validateRoom(int roomNumber, TypeRoom typeRoom, String status) {
        return isDataMatch(String.valueOf(roomNumber), NUMBERS_REGEX)
                && isDataMatch(status, STATUS_REGEX)
                && validateTypeRoom(typeRoom.getTypeRoom(), typeRoom.getCapacity(),
                typeRoom.getPrice(), typeRoom.getDescription(), typeRoom.getImage());
    }

    public static boolean validateOrder(Date arrivalDate, Date departureDate, int noAdults, int noChildren,
                                        String typeApartment, String breakfast) {
        return isDataMatch(String.valueOf(noAdults), NUMBER_REGEX)
                && isDataMatch(String.valueOf(noChildren), NUMBER_REGEX)
                && isDataMatch(typeApartment, TYPE_ROOM_REGEX)
                && isDataMatch(breakfast, BREAKFAST_REGEX)
                && isDataMatch(String.valueOf(arrivalDate), DATE_REGEX)
                && isDataMatch(String.valueOf(departureDate), DATE_REGEX);

    }

    public static boolean validateRoomNumber(int roomNumber) {
        return isDataMatch(String.valueOf(roomNumber), NUMBERS_REGEX);
    }
}
