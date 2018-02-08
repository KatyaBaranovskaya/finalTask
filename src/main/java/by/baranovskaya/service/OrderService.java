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
        boolean flag;
        try {
            flag = orderDAO.addOrderByUser(order);
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

   /* public void orderService(int idClient, Date arrivalDate, Date departureDate, String[] services) throws ServiceException {
        int idOrder;
        try {
            idOrder = orderDAO.getIdOrder(idClient, arrivalDate, departureDate); // проверка на 0
            for (int i = 0; i < services.length; i++) {
                orderDAO.orderService(idOrder, Integer.parseInt(services[i]));
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }*/

    public List<Order> getUserOrders(int idClient) throws ServiceException {
        List<Order> orderList;
        try {
            orderList = orderDAO.getUserOrders(idClient);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orderList;
    }

    public List<Order> getNewOrders() throws ServiceException {
        List<Order> orderList;
        try {
            orderList = orderDAO.getNewOrders();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orderList;
    }

    public List<Order> getExecutedOrders() throws ServiceException {
        List<Order> orderList;
        try {
            orderList = orderDAO.getExecutedOrders();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orderList;
    }

    public Order getOrder(int idOrder) throws ServiceException {
        Order order;
        try {
            order = orderDAO.getOrderById(idOrder);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return order;
    }

    public boolean updateStatus(int idOrder) throws ServiceException {
        try {
            return orderDAO.updateStatusById(idOrder);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public boolean updateOrder(Order order) throws ServiceException {
        boolean flag;
        try {
            flag = orderDAO.updateOrder(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }
}
