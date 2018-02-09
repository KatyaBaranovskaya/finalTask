package by.baranovskaya;

import by.baranovskaya.entity.Order;
import by.baranovskaya.exception.DAOException;
import by.baranovskaya.exception.ParseDataException;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.TypeRoomService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;

public class TypeRoomServiceTest {
    private final static Logger LOGGER = LogManager.getLogger(TypeRoomServiceTest.class);

    private static TypeRoomService typeRoomService;
    private static Order order;

    @BeforeClass
    public static void init() {
        typeRoomService = new TypeRoomService();
        order = new Order();
        order.setArrivalDate(Date.valueOf("2018-02-14"));
        order.setDepartureDate(Date.valueOf("2018-02-16"));
        order.setTypeApartment("SNGL");
        order.setBreakfast("да");
    }

    @Test
    public void calculatePriceTest() throws DAOException {
        try {
            Assert.assertTrue(136.0 == typeRoomService.calculatePrice(order));
        } catch (ServiceException | ParseDataException e) {
            LOGGER.error(e);
        }
    }
}
