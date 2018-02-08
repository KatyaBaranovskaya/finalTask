package by.baranovskaya.service;

import by.baranovskaya.dao.factory.DAOFactory;
import by.baranovskaya.dao.OrderDAO;
import by.baranovskaya.entity.Order;
import by.baranovskaya.exception.DAOException;
import by.baranovskaya.exception.ServiceException;

import java.util.List;

public class OrderService {
    private OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();

    public boolean doOrder(Order order) throws ServiceException {
        try {
            return orderDAO.addOrder(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public List<Order> getUserOrders(int idClient) throws ServiceException {
        try {
            return orderDAO.getUserOrders(idClient);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public List<Order> getNewOrders() throws ServiceException {
        try {
            return orderDAO.getNewOrders();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public List<Order> getExecutedOrders() throws ServiceException {
        try {
            return orderDAO.getExecutedOrders();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public Order getOrder(int idOrder) throws ServiceException {
        try {
            return orderDAO.getOrderById(idOrder);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public boolean updateStatus(int idOrder) throws ServiceException {
        try {
            return orderDAO.updateStatusById(idOrder);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public boolean issueOrder(Order order) throws ServiceException {
        try {
            return orderDAO.updateOrder(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
