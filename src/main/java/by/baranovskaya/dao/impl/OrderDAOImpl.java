package by.baranovskaya.dao.impl;

import by.baranovskaya.dao.OrderDAO;
import by.baranovskaya.dao.connection.ConnectionPool;
import by.baranovskaya.dao.connection.ProxyConnection;
import by.baranovskaya.exception.DAOException;

import java.sql.*;

public class OrderDAOImpl implements OrderDAO {
    private final static String INSERT_ORDER = "INSERT INTO orders(id_client, arrival_date, departure_date) VALUES (?,?,?)";
    private final static String INSERT_ORDER_WITH_ROOM_NUMBER = "INSERT INTO orders(id_client, room_number, arrival_date, departure_date) VALUES (?,?,?,?)";
    public final static String FIND_ID_ORDER = "SELECT id_order FROM orders WHERE id_client=? AND arrival_date=? AND departure_date=?";
    private final static String INSERT_ORDER_SERVICE = "INSERT INTO order_service(id_order, id_service) VALUES (?,?)";

    @Override
    public boolean doOrderByUser(int idClient, Date arrivalDate, Date departureDate) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_ORDER);
            preparedStatement.setInt(1, idClient);
            preparedStatement.setDate(2, arrivalDate);
            preparedStatement.setDate(3, departureDate);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception inserting order" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }

    @Override
    public boolean doConcreteOrderByUser(int idClient, int roomNumber, Date arrivalDate, Date departureDate) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_ORDER_WITH_ROOM_NUMBER);
            preparedStatement.setInt(1, idClient);
            preparedStatement.setInt(2, roomNumber);
            preparedStatement.setDate(3, arrivalDate);
            preparedStatement.setDate(4, departureDate);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception inserting order" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }

    @Override
    public int getIdOrder(int idClient, Date arrivalDate, Date departureDate) throws DAOException{
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_ID_ORDER);
            preparedStatement.setInt(1, idClient);
            preparedStatement.setDate(2, arrivalDate);
            preparedStatement.setDate(3, departureDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception finding order" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return 0;
    }

    @Override
    public boolean orderService(int idClient, int idService) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_ORDER_SERVICE);
            preparedStatement.setInt(1, idClient);
            preparedStatement.setInt(2, idService);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception inserting inserting services" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }
}
