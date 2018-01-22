package by.baranovskaya.dao.impl;

import by.baranovskaya.dao.ServiceDAO;
import by.baranovskaya.dao.connection.ConnectionPool;
import by.baranovskaya.dao.connection.ProxyConnection;
import by.baranovskaya.entity.Room;
import by.baranovskaya.entity.Service;
import by.baranovskaya.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAOIml implements ServiceDAO{
    private final static String SELECT_SERVICE = "SELECT id_service, type_service, price FROM hotel.services";
    private final static String INSERT_SERVICE = "INSERT INTO services(type_service, price) VALUES (?,?)";
    public final static String DELETE_SERVICE = "DELETE FROM services WHERE id_service=?";

    public List<Service> getAll() throws DAOException {
        List<Service> serviceList = new ArrayList<>();
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_SERVICE);
            while (resultSet.next()) {
                Service service = new Service();
                service.setIdService(resultSet.getInt("id_service"));
                service.setTypeService(resultSet.getString("type_service"));
                service.setPrice(resultSet.getDouble("price"));
                serviceList.add(service);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception selecting all service" + e);
        } finally {
            close(statement);
            close(connection);
        }
        return serviceList;
    }

    @Override
    public boolean addService(Service service) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_SERVICE);
            preparedStatement.setString(1, service.getTypeService());
            preparedStatement.setDouble(2, service.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception inserting service" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }

    @Override
    public boolean deleteService(int idService) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DELETE_SERVICE);
            preparedStatement.setInt(1, idService);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception deleting service" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }
}
