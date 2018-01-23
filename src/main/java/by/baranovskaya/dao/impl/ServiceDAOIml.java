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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServiceDAOIml implements ServiceDAO{
    private final static String SELECT_SERVICE = "SELECT id_service, type_service, price FROM hotel.services";
    private final static String INSERT_SERVICE = "INSERT INTO services(type_service, price) VALUES (?,?)";
    public final static String DELETE_SERVICE = "DELETE FROM services WHERE id_service=?";
    public final static String FIND_SERVICE_BY_ID = "SELECT id_service, type_service, price FROM services WHERE id_service=?";
    public final static String UPDATE_SERVICE_BY_ID = "UPDATE services SET type_service=?, price=? WHERE id_service=?";
    private final static String SELECT_TYPES_SERVICE = "SELECT type_service FROM hotel.services;";

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
                service.setPrice(resultSet.getDouble("price"));
            }
        } catch (SQLException e) {
            throw new DAOException("Exception selecting room by ID" + e);
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
            preparedStatement.setDouble(2, service.getPrice());
            preparedStatement.setInt(3, service.getIdService());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception updating service" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }

    @Override
    public Set<String> getTypesService() throws DAOException {
        Set<String> setServices = new HashSet<>();
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_TYPES_SERVICE);
            while (resultSet.next()) {
                setServices.add(resultSet.getString("type_service"));
            }
        } catch (SQLException e) {
            throw new DAOException("Exception selecting all types" + e);
        } finally {
            close(statement);
            close(connection);
        }
        return setServices;
    }
}
