package by.baranovskaya.service;

import by.baranovskaya.dao.DAOFactory;
import by.baranovskaya.dao.OrderDAO;
import by.baranovskaya.entity.Order;
import by.baranovskaya.exception.DAOException;
import by.baranovskaya.exception.ServiceException;

import java.sql.Date;

public class OrderService {
    private OrderDAO orderDAO = DAOFactory.getOrderDAO();

    public boolean doOrder(Order order) throws ServiceException {
        boolean flag;
        try {
            flag = orderDAO.doOrderByUser(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }

    public boolean doConcreteOrder(Order order) throws ServiceException {
        boolean flag;
        try {
            flag = orderDAO.doConcreteOrderByUser(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }

    public void orderService(int idClient, Date arrivalDate, Date departureDate, String[] services) throws ServiceException {
        int idOrder;
        try {
            idOrder = orderDAO.getIdOrder(idClient, arrivalDate, departureDate); // проверка на 0
            for (int i = 0; i < services.length; i++){
                orderDAO.orderService(idOrder, Integer.parseInt(services[i]));
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
