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
import java.util.*;

public class ServiceDAOImpl implements ServiceDAO{
    private final static String SELECT_SERVICES = "SELECT id_service, type_service,description, image FROM hotel.services";
    private final static String INSERT_SERVICE = "INSERT INTO services(type_service, description, image) VALUES (?,?,?)";
    private final static String DELETE_SERVICE = "DELETE FROM services WHERE id_service=?";
    private final static String FIND_SERVICE_BY_ID = "SELECT id_service, type_service, description, image FROM services WHERE id_service=?";
    private final static String UPDATE_SERVICE_BY_ID = "UPDATE services SET type_service=?, description=?, image=? WHERE id_service=?";

    @Override
    public List<Service> getAllServices() throws DAOException {
        List<Service> serviceList = new ArrayList<>();
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_SERVICES);
            while (resultSet.next()) {
                Service service = new Service();
                service.setIdService(resultSet.getInt("id_service"));
                service.setTypeService(resultSet.getString("type_service"));
                service.setDescription(resultSet.getString("description"));
                service.setImage(resultSet.getString("image"));
                serviceList.add(service);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception selecting all service: " + e);
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
            preparedStatement.setString(2, service.getDescription());
            preparedStatement.setString(3, service.getImage());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception inserting service: " + e);
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
            throw new DAOException("Exception deleting service: " + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }

    @Override
    public Service findServiceById(int idService) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        Service service = new Service();
        try {
            preparedStatement = connection.prepareStatement(FIND_SERVICE_BY_ID);
            preparedStatement.setInt(1, idService);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                service.setIdService(resultSet.getInt("id_service"));
                service.setTypeService(resultSet.getString("type_service"));
                service.setDescription(resultSet.getString("description"));
                service.setImage(resultSet.getString("image"));
            }
        } catch (SQLException e) {
            throw new DAOException("Exception selecting service by id: " + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return service;
    }

    @Override
    public boolean updateServiceById(Service service) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_SERVICE_BY_ID);
            preparedStatement.setString(1, service.getTypeService());
            preparedStatement.setString(2, service.getDescription());
            preparedStatement.setString(3, service.getImage());
            preparedStatement.setInt(4, service.getIdService());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception updating service: " + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }
}
