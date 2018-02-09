package by.baranovskaya.dao;

import by.baranovskaya.dao.factory.DAOFactory;
import by.baranovskaya.entity.Service;
import by.baranovskaya.exception.DAOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class ServiceDAOImplTest {
    private static ServiceDAO serviceDAO;
    private static Service service;

    @BeforeClass
    public static void init() {
        serviceDAO = DAOFactory.getInstance().getServiceDAO();
        service = new Service();
        service.setTypeService("Аренда сейфа");
        service.setDescription("Хороший сейф");
        service.setImage("serv2.jpg");
    }

    @Test
    public void getAllServicesTest() throws DAOException {
        List<Service> serviceList = serviceDAO.getAllServices();
        Assert.assertFalse(serviceList.isEmpty());
    }

    @Test
    public void addServiceTest() throws DAOException {
        serviceDAO.addService(service);
    }
}
