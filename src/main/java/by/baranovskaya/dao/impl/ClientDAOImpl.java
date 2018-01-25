package by.baranovskaya.dao.impl;

import by.baranovskaya.dao.ClientDAO;
import by.baranovskaya.dao.connection.ConnectionPool;
import by.baranovskaya.dao.connection.ProxyConnection;
import by.baranovskaya.entity.Client;
import by.baranovskaya.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {
    private final static String SELECT_CLIENT = "SELECT id_client, email, login, password, role_name, surname, name, middle_name, date_birth, telephone, avatar \n" +
            "FROM hotel.clients JOIN roles ON roles.id_role = clients.id_role WHERE roles.role_name = 'Пользователь'";
    private final static String INSERT_CLIENT = "INSERT INTO clients(email, login, password, surname, name, middle_name, date_birth, telephone) VALUES (?,?,?,?,?,?,?,?,?)";
    public final static String DELETE_CLIENT = "DELETE FROM clients WHERE id_client=?";
    private final static String FIND_CLIENT = "SELECT id_client, email, login, password, role_name, surname, name, middle_name, date_birth, telephone, avatar \n" +
            "FROM hotel.clients JOIN roles ON roles.id_role = clients.id_role WHERE login = ? AND password = ?";
    private final static String FIND_CLIENT_BY_ID = "SELECT id_client, email, login, password, role_name, surname, name, middle_name, date_birth, telephone, avatar \n" +
            "FROM hotel.clients JOIN roles ON roles.id_role = clients.id_role WHERE id_client = ?";
    public final static String UPDATE_PASSWORD_BY_ID = "UPDATE clients SET password=? WHERE id_client=?";
    public final static String UPDATE_AVATAR_BY_ID = "UPDATE clients SET avatar=? WHERE id_client=?";
    public final static String UPDATE_ACCOUNT_INFO_BY_ID = "UPDATE clients SET surname=?, name=?, middle_name=?, date_birth=?, telephone=? WHERE id_client=?";

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
                client.setTelephone(resultSet.getString("telephone"));
                client.setAvatar(resultSet.getString("avatar"));
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
            preparedStatement.setString(4, client.getSurname());
            preparedStatement.setString(5, client.getName());
            preparedStatement.setString(6, client.getMiddleName());
            preparedStatement.setDate(7, client.getDateBirth());
            preparedStatement.setString(8, client.getTelephone());
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
    public Client findClientByLoginPassword(String login, String password) throws DAOException { /// нормально так???
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_CLIENT);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Client client = new Client();
                client.setIdClient(resultSet.getInt("id_client"));
                client.setEmail(resultSet.getString("email"));
                client.setLogin(resultSet.getString("login"));
                client.setPassword(resultSet.getString("password"));
                client.setRole(resultSet.getString("role_name"));
                client.setSurname(resultSet.getString("surname"));
                client.setName(resultSet.getString("name"));
                client.setMiddleName(resultSet.getString("middle_name"));
                client.setDateBirth(resultSet.getDate("date_birth"));
                client.setTelephone(resultSet.getString("telephone"));
                client.setAvatar(resultSet.getString("avatar"));
                return client;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Exception selecting client by login and password" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public Client findClientById(int idClient) throws DAOException { /// нормально так???
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_CLIENT_BY_ID);
            preparedStatement.setInt(1, idClient);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Client client = new Client();
                client.setIdClient(resultSet.getInt("id_client"));
                client.setEmail(resultSet.getString("email"));
                client.setLogin(resultSet.getString("login"));
                client.setPassword(resultSet.getString("password"));
                client.setRole(resultSet.getString("role_name"));
                client.setSurname(resultSet.getString("surname"));
                client.setName(resultSet.getString("name"));
                client.setMiddleName(resultSet.getString("middle_name"));
                client.setDateBirth(resultSet.getDate("date_birth"));
                client.setTelephone(resultSet.getString("telephone"));
                client.setAvatar(resultSet.getString("avatar"));
                return client;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Exception selecting client by id" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public boolean updatePasswordById(int idClient, String password) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_PASSWORD_BY_ID);
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, idClient);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception updating password" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }

    @Override
    public boolean updateAvatarById(int idClient, String avatarPath) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_AVATAR_BY_ID);
            preparedStatement.setString(1, avatarPath);
            preparedStatement.setInt(2, idClient);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception updating avatar" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }

    @Override
    public boolean updateClientInfo(Client client) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT_INFO_BY_ID);
            preparedStatement.setString(1, client.getSurname());
            preparedStatement.setString(2, client.getName());
            preparedStatement.setString(3, client.getMiddleName());
            preparedStatement.setDate(4, client.getDateBirth());
            preparedStatement.setString(5, client.getTelephone());
            preparedStatement.setInt(6, client.getIdClient());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception updating avatar" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }
}
