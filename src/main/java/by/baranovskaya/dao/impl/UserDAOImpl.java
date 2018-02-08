package by.baranovskaya.dao.impl;

import by.baranovskaya.dao.UserDAO;
import by.baranovskaya.dao.connection.ConnectionPool;
import by.baranovskaya.dao.connection.ProxyConnection;
import by.baranovskaya.entity.User;
import by.baranovskaya.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private final static String SELECT_USER = "SELECT id_user, email, login, password, role_name, surname, name, middle_name, date_birth, telephone, avatar \n" +
            "FROM hotel.users JOIN roles ON roles.id_role = users.id_role WHERE roles.role_name = 'Пользователь'";
    private final static String INSERT_USER = "INSERT INTO users(email, login, password, surname, name, middle_name, date_birth, telephone) VALUES (?,?,?,?,?,?,?,?)";
    public final static String DELETE_USER = "DELETE FROM users WHERE id_user=?";
    private final static String FIND_USER_BY_LOGIN_PASS = "SELECT id_user, email, login, password, role_name, surname, name, middle_name, date_birth, telephone, avatar \n" +
            "FROM hotel.users JOIN roles ON roles.id_role = users.id_role WHERE login = ? AND password = ?";
    private final static String FIND_USER_BY_LOGIN = "SELECT id_user FROM users WHERE login = ? ";
    private final static String FIND_USER_BY_LOGIN_EMAIL = "SELECT id_user, email, login, password, role_name, surname, name, middle_name, date_birth, telephone, avatar \n" +
            "FROM hotel.users JOIN roles ON roles.id_role = users.id_role WHERE login = ? AND email = ?";
    private final static String FIND_USER_BY_ID = "SELECT id_user, email, login, password, role_name, surname, name, middle_name, date_birth, telephone, avatar \n" +
            "FROM hotel.users JOIN roles ON roles.id_role = users.id_role WHERE id_user = ?";
    public final static String UPDATE_PASSWORD_BY_ID = "UPDATE users SET password=? WHERE id_user=?";
    public final static String UPDATE_AVATAR_BY_ID = "UPDATE users SET avatar=? WHERE id_user=?";
    public final static String UPDATE_ACCOUNT_INFO_BY_ID = "UPDATE users SET surname=?, name=?, middle_name=?, date_birth=?, telephone=? WHERE id_user=?";

    @Override
    public List<User> getAllUsers() throws DAOException {
        List<User> listUser = new ArrayList<>();
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_USER);
            while (resultSet.next()) {
                User user = new User();
                user.setIdUser(resultSet.getInt("id_user"));
                user.setEmail(resultSet.getString("email"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role_name"));
                user.setSurname(resultSet.getString("surname"));
                user.setName(resultSet.getString("name"));
                user.setMiddleName(resultSet.getString("middle_name"));
                user.setDateBirth(resultSet.getDate("date_birth"));
                user.setTelephone(resultSet.getString("telephone"));
                user.setAvatar(resultSet.getString("avatar"));
                listUser.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception selecting all clients" + e);
        } finally {
            close(statement);
            close(connection);
        }
        return listUser;
    }

    @Override
    public boolean addUser(User user) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setString(5, user.getName());
            preparedStatement.setString(6, user.getMiddleName());
            preparedStatement.setDate(7, user.getDateBirth());
            preparedStatement.setString(8, user.getTelephone());
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
    public boolean deleteUser(int idUser) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setInt(1, idUser);
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
    public User findUserByLoginPassword(String login, String password) throws DAOException { /// нормально так???
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN_PASS);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setIdUser(resultSet.getInt("id_user"));
                user.setEmail(resultSet.getString("email"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role_name"));
                user.setSurname(resultSet.getString("surname"));
                user.setName(resultSet.getString("name"));
                user.setMiddleName(resultSet.getString("middle_name"));
                user.setDateBirth(resultSet.getDate("date_birth"));
                user.setTelephone(resultSet.getString("telephone"));
                user.setAvatar(resultSet.getString("avatar"));
                return user;
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
    public boolean findUserByLogin(String login) throws DAOException { /// нормально так???
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            return  (resultSet.next());
        } catch (SQLException e) {
            throw new DAOException("Exception selecting client by login and password" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public User findUserByLoginEmail(String login, String email) throws DAOException { /// нормально так???
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN_EMAIL);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setIdUser(resultSet.getInt("id_user"));
                user.setEmail(resultSet.getString("email"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role_name"));
                user.setSurname(resultSet.getString("surname"));
                user.setName(resultSet.getString("name"));
                user.setMiddleName(resultSet.getString("middle_name"));
                user.setDateBirth(resultSet.getDate("date_birth"));
                user.setTelephone(resultSet.getString("telephone"));
                user.setAvatar(resultSet.getString("avatar"));
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DAOException("Exception selecting client by login and email" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public User findUserById(int idUser) throws DAOException { /// нормально так???
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_USER_BY_ID);
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setIdUser(resultSet.getInt("id_user"));
                user.setEmail(resultSet.getString("email"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role_name"));
                user.setSurname(resultSet.getString("surname"));
                user.setName(resultSet.getString("name"));
                user.setMiddleName(resultSet.getString("middle_name"));
                user.setDateBirth(resultSet.getDate("date_birth"));
                user.setTelephone(resultSet.getString("telephone"));
                user.setAvatar(resultSet.getString("avatar"));
                return user;
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
    public boolean updatePasswordById(int idUser, String password) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_PASSWORD_BY_ID);
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, idUser);
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
    public boolean updateUserInfo(User user) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT_INFO_BY_ID);
            preparedStatement.setString(1, user.getSurname());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getMiddleName());
            preparedStatement.setDate(4, user.getDateBirth());
            preparedStatement.setString(5, user.getTelephone());
            preparedStatement.setInt(6, user.getIdUser());
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
