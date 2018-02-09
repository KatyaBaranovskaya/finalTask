package by.baranovskaya.dao;

import by.baranovskaya.dao.factory.DAOFactory;
import by.baranovskaya.entity.Order;
import by.baranovskaya.exception.DAOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class OrderDAOImplTest {
    private static OrderDAO orderDAO;

    @BeforeClass
    public static void init() {
        orderDAO = DAOFactory.getInstance().getOrderDAO();
    }

    @Test
    public void testGetAllOrders() throws DAOException {
        List<Order> orderList = orderDAO.getNewOrders();
        Assert.assertFalse(orderList.isEmpty());
    }
}
