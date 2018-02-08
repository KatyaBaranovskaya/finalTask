package by.baranovskaya.dao;

import by.baranovskaya.entity.Order;
import by.baranovskaya.exception.DAOException;

import java.sql.Date;
import java.util.List;

public interface OrderDAO extends AbstractDAO {
    boolean addOrderByUser(Order order) throws DAOException;

    boolean doConcreteOrderByUser(Order order) throws DAOException;

    int getIdOrder(int idUser, Date arrivalDate, Date departureDate) throws DAOException;

    //boolean orderService(int idUser, int idService) throws DAOException;

    List<Order> getUserOrders(int idUser) throws DAOException;

    List<Order> getNewOrders() throws DAOException;

    List<Order> getExecutedOrders() throws DAOException;

    Order getOrderById(int idOrder) throws DAOException;

    boolean updateStatusById(int idOrder) throws DAOException;

    boolean updateOrder(Order order) throws DAOException;
}
