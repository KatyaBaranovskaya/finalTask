package by.baranovskaya.dao;

import by.baranovskaya.entity.Order;
import by.baranovskaya.exception.DAOException;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.Date;

public interface OrderDAO extends AbstractDAO {
    boolean doOrderByUser(Order order) throws DAOException;
    boolean doConcreteOrderByUser(Order order) throws DAOException;
    int getIdOrder(int idClient, Date arrivalDate, Date departureDate) throws DAOException;
    boolean orderService(int idClient, int idService) throws DAOException;
}
