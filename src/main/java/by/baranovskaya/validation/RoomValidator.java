package by.baranovskaya.validation;

import by.baranovskaya.entity.TypeRoom;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoomValidator {
    private final static String NUMBER_REGEX = "[0-9]+";
    private final static String STATUS_REGEX = "Бронирован|Свободен";

    private static boolean isDataMatch(String data, String regex) {
        if (data == null || data.isEmpty()) {
            return false;
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);

        return matcher.matches();
    }

    public static boolean validateRoom(int roomNumber, TypeRoom typeRoom, String status) {
        return isDataMatch(String.valueOf(roomNumber), NUMBER_REGEX)
                && isDataMatch(status, STATUS_REGEX)
                && TypeRoomValidator.validateTypeRoom(typeRoom.getTypeRoom(), typeRoom.getCapacity(),
                typeRoom.getPrice(), typeRoom.getDescription(), typeRoom.getImage());
    }
}
