package by.baranovskaya.dao;

import by.baranovskaya.exception.DAOException;

import java.sql.Date;

public interface OrderDAO extends AbstractDAO {
    boolean doOrderByUser(int idClient, Date arrivalDate, Date departureDate) throws DAOException;
    boolean doConcreteOrderByUser(int idClient, int roomNumber, Date arrivalDate, Date departureDate) throws DAOException;
    int getIdOrder(int idClient, Date arrivalDate, Date departureDate) throws DAOException;
    boolean orderService(int idClient, int idService) throws DAOException;
}
