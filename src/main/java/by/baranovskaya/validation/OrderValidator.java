package by.baranovskaya.validation;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderValidator implements Validator{
    private final static String NUMBER_REGEX = "[0-9]";
    private final static String NUMBERS_REGEX = "[0-9]+";
    private final static String TYPE_ROOM_REGEX = "[\\w\\+\\s]+";
    private final static String BREAKFAST_REGEX = "да|нет";
    private final static double PARAM_DIVISION = 3.114E+10;


    private static boolean isDataMatch(String data, String regex){
        System.out.println(data);
        if (data == null || data.isEmpty()){
            return false;
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);

        System.out.println(matcher.matches());
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


    public static boolean validateOrder(Date arrivalDate, Date departureDate, int noAdults, int noChildren,
                                        String typeApartment, String breakfast) {
        return isDataMatch(String.valueOf(noAdults), NUMBER_REGEX)
                && isDataMatch(String.valueOf(noChildren), NUMBER_REGEX)
                && isDataMatch(typeApartment, TYPE_ROOM_REGEX)
                && isDataMatch(breakfast, BREAKFAST_REGEX);
               // date
                //&& isDateMatch(user.getDateBirth())

    }

    public static boolean validateNumber(int roomNumber) {
        return isDataMatch(String.valueOf(roomNumber), NUMBERS_REGEX);
    }
}
