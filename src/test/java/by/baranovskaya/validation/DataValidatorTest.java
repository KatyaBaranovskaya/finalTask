package by.baranovskaya.validation;

import org.junit.Assert;
import org.junit.Test;

public class DataValidatorTest {
    @Test
    public void validateLoginPassTest() {
        String login = "baranovskaya";
        String password = "Katya18";
        Assert.assertTrue(DataValidator.validateLoginPassword(login, password));
    }

    @Test
    public void validateLoginEmailTest() {
        String login = "b";
        String email = "katya@";
        Assert.assertFalse(DataValidator.validateLoginEmail(login, email));
    }

    @Test
    public void validateAvatarTest() {
        String avatar = "";
        Assert.assertFalse(DataValidator.validateAvatar(avatar));
    }

    @Test
    public void validateServiceTest() {
        String typeService = "Аренда сейфа";
        String description = "В каждом номере расположен сейф.";
        String image = "service.jpg";
        Assert.assertTrue(DataValidator.validateService(typeService, description, image));
    }

    @Test
    public void validateSearchParamTest() {
        int minPrice = 50;
        int maxPrice = 30;
        Assert.assertFalse(DataValidator.validateSearchParam(minPrice, maxPrice));
    }

    @Test
    public void validateRoomNumberTest() {
        int roomNumber = 122;
        Assert.assertTrue(DataValidator.validateRoomNumber(roomNumber));
    }
}
