package by.baranovskaya.dao.impl;

import by.baranovskaya.dao.connection.ConnectionPool;
import by.baranovskaya.dao.connection.ProxyConnection;
import by.baranovskaya.dao.ClientDAO;
import by.baranovskaya.entity.Client;
import by.baranovskaya.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {
    private final static int ID_ROLE = 2;
    private final static String SELECT_CLIENT = "SELECT id_client, email, login, password, role_name, surname, name, middle_name, date_birth, passport, telephone \n" +
            "FROM hotel.clients JOIN roles ON roles.id_role = clients.id_role WHERE roles.role_name = 'Пользователь'";
    private final static String INSERT_CLIENT = "INSERT INTO clients(email, login, password, id_role, surname, name, middle_name, date_birth, telephone) VALUES (?,?,?,?,?,?,?,?,?)";
    public final static String DELETE_CLIENT = "DELETE FROM clients WHERE id_client=?";
    private final static String FIND_CLIENT = "SELECT id_role FROM hotel.clients WHERE login = ? AND password = ?";

    @Override
    public List<Client> getAll() throws DAOException {
        List<Client> listClient = new ArrayList<>();
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_CLIENT);
            while (resultSet.next()) {
                Client client = new Client();
                client.setIdClient(resultSet.getInt("id_client"));
                client.setLogin(resultSet.getString("email"));
                client.setLogin(resultSet.getString("login"));
                client.setPassword(resultSet.getString("password"));
                client.setRole(resultSet.getString("role_name"));
                client.setSurname(resultSet.getString("surname"));
                client.setName(resultSet.getString("name"));
                client.setMiddleName(resultSet.getString("middle_name"));
                client.setDateBirth(resultSet.getDate("date_birth"));
                client.setPassport(resultSet.getString("passport"));
                client.setTelephone(resultSet.getString("telephone"));
                listClient.add(client);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception selecting all clients" + e);
        } finally {
            close(statement);
            close(connection);
        }
        return listClient;
    }

    @Override
    public boolean addClient(Client client) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_CLIENT);
            preparedStatement.setString(1, client.getEmail());
            preparedStatement.setString(2, client.getLogin());
            preparedStatement.setString(3, client.getPassword());
            preparedStatement.setInt(4, ID_ROLE);
            preparedStatement.setString(5, client.getSurname());
            preparedStatement.setString(6, client.getName());
            preparedStatement.setString(7, client.getMiddleName());
            preparedStatement.setDate(8, client.getDateBirth());
            preparedStatement.setString(9, client.getTelephone());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception inserting client" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }

    @Override
    public boolean deleteClient(int idClient) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DELETE_CLIENT);
            preparedStatement.setInt(1, idClient);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception deleting client" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }

    @Override
    public int findClientByLoginPassword(String login, String password) throws DAOException { /// нормально так???
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_CLIENT);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("id_role");
            } else{
                return 0;
            }
        }  catch (SQLException e) {
            throw new DAOException("Exception selecting client by login and password" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }
}
