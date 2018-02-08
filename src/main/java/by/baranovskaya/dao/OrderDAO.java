package by.baranovskaya.dao;

import by.baranovskaya.entity.Order;
import by.baranovskaya.exception.DAOException;

import java.sql.Date;
import java.util.List;

public interface OrderDAO extends AbstractDAO {
    boolean addOrder(Order order) throws DAOException;

    List<Order> getUserOrders(int idUser) throws DAOException;

    List<Order> getNewOrders() throws DAOException;

    List<Order> getExecutedOrders() throws DAOException;

    Order getOrderById(int idOrder) throws DAOException;

    boolean updateStatusById(int idOrder) throws DAOException;

    boolean updateOrder(Order order) throws DAOException;
}
