package by.baranovskaya.dao.impl;

import by.baranovskaya.dao.factory.DAOFactory;
import by.baranovskaya.dao.OrderDAO;
import by.baranovskaya.dao.ServiceDAO;
import by.baranovskaya.dao.connection.ConnectionPool;
import by.baranovskaya.dao.connection.ProxyConnection;
import by.baranovskaya.entity.Order;
import by.baranovskaya.entity.User;
import by.baranovskaya.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private final static String SELECT_NEW_ORDERS = "SELECT id_order, users.id_user, email, login, password, role_name, surname, name, middle_name, date_birth, telephone, avatar,\n" +
            " room_number, arrival_date, departure_date, no_adults, no_children, type_apartment, breakfast, price, status FROM orders \n" +
            " JOIN users ON users.id_user = orders.id_user\n" +
            " JOIN roles ON roles.id_role = users.id_role WHERE roles.role_name = 'Пользователь' AND orders.status = 'принят'";
    private final static String SELECT_EXECUTED_ORDERS = "SELECT id_order, users.id_user, email, login, password, role_name, surname, name, middle_name, date_birth, telephone, avatar,\n" +
            " room_number, arrival_date, departure_date, no_adults, no_children, type_apartment, breakfast, price, status FROM orders \n" +
            " JOIN users ON users.id_user = orders.id_user\n" +
            " JOIN roles ON roles.id_role = users.id_role WHERE roles.role_name = 'Пользователь' AND orders.status != 'принят'";
    private final static String INSERT_ORDER = "INSERT INTO orders(id_user, arrival_date, departure_date, no_adults, no_children, type_apartment, breakfast) VALUES (?,?,?,?,?,?,?)";
    private final static String INSERT_ORDER_WITH_ROOM_NUMBER = "INSERT INTO orders(id_user, room_number, arrival_date, departure_date, no_adults, no_children, type_apartment, breakfast) VALUES (?,?,?,?,?,?,?,?)";
    private final static String FIND_ID_ORDER = "SELECT id_order FROM orders WHERE id_user=? AND arrival_date=? AND departure_date=?";
    private final static String INSERT_ORDER_SERVICE = "INSERT INTO order_service(id_order, id_service) VALUES (?,?)";
    private final static String SELECT_USER_ORDERS = "SELECT id_order, room_number, arrival_date, departure_date, no_adults, no_children, type_apartment, breakfast, price, status FROM orders WHERE id_user=?";
    private final static String SELECT_ORDER_BY_ID = "SELECT id_order, id_user, room_number, arrival_date, departure_date, no_adults, no_children, type_apartment, breakfast, price, status FROM orders WHERE id_order=?";
    private final static String UPDATE_ORDER = "UPDATE orders SET room_number=?, arrival_date=?, departure_date=?, no_adults=?, no_children=?, type_apartment=?, breakfast=?, price=?, status=? WHERE id_order=?";
    private final static String UPDATE_STATUS_ORDER = "UPDATE orders SET status='отклонен' WHERE id_order=?";

    @Override
    public boolean addOrderByUser(Order order) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_ORDER);
            preparedStatement.setInt(1, order.getUser().getIdUser());
            preparedStatement.setDate(2, order.getArrivalDate());
            preparedStatement.setDate(3, order.getDepartureDate());
            preparedStatement.setInt(4, order.getNoAdults());
            preparedStatement.setInt(5, order.getNoChildren());
            preparedStatement.setString(6, order.getTypeApartment());
            preparedStatement.setString(7, order.getBreakfast());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception inserting order " + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }

    @Override
    public boolean doConcreteOrderByUser(Order order) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_ORDER_WITH_ROOM_NUMBER);
            preparedStatement.setInt(1, order.getUser().getIdUser());
            preparedStatement.setInt(2, order.getRoomNumber());
            preparedStatement.setDate(3, order.getArrivalDate());
            preparedStatement.setDate(4, order.getDepartureDate());
            preparedStatement.setInt(5, order.getNoAdults());
            preparedStatement.setInt(6, order.getNoChildren());
            preparedStatement.setString(7, order.getTypeApartment());
            preparedStatement.setString(8, order.getBreakfast());
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
    public int getIdOrder(int idUser, Date arrivalDate, Date departureDate) throws DAOException{
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_ID_ORDER);
            preparedStatement.setInt(1, idUser);
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

   /* @Override
    public boolean orderService(int idUser, int idService) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_ORDER_SERVICE);
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idService);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception inserting inserting services" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }*/

    @Override
    public List<Order> getUserOrders(int idUser) throws DAOException {
        List<Order> orderList = new ArrayList<>();
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_USER_ORDERS);
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Order order = new Order();
                order.setIdOrder(resultSet.getInt("id_order"));
                //order.setIdUser(resultSet.getInt("id_user"));           // TODO ???
                order.setRoomNumber(resultSet.getInt("room_number"));
                order.setArrivalDate(resultSet.getDate("arrival_date"));
                order.setDepartureDate(resultSet.getDate("departure_date"));
                order.setNoAdults(resultSet.getInt("no_adults"));
                order.setNoChildren(resultSet.getInt("no_children"));
                order.setTypeApartment(resultSet.getString("type_apartment"));
                order.setBreakfast(resultSet.getString("breakfast"));
                order.setPrice(resultSet.getDouble("price"));
                order.setStatus(resultSet.getString("status"));
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception selecting user orders" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return orderList;
    }

    @Override
    public List<Order> getExecutedOrders() throws DAOException {
        List<Order> orderList = new ArrayList<>();
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ServiceDAO serviceDAO = DAOFactory.getInstance().getServiceDAO();
        try {
            preparedStatement = connection.prepareStatement(SELECT_EXECUTED_ORDERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Order order = new Order();
                User user = new User();
                order.setIdOrder(resultSet.getInt("id_order"));
                user.setIdUser(resultSet.getInt("id_user"));
                user.setLogin(resultSet.getString("email"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role_name"));
                user.setSurname(resultSet.getString("surname"));
                user.setName(resultSet.getString("name"));
                user.setMiddleName(resultSet.getString("middle_name"));
                user.setDateBirth(resultSet.getDate("date_birth"));
                user.setTelephone(resultSet.getString("telephone"));
                user.setAvatar(resultSet.getString("avatar"));
                order.setUser(user);
                order.setRoomNumber(resultSet.getInt("room_number"));
                order.setArrivalDate(resultSet.getDate("arrival_date"));
                order.setDepartureDate(resultSet.getDate("departure_date"));
                order.setNoAdults(resultSet.getInt("no_adults"));
                order.setNoChildren(resultSet.getInt("no_children"));
                order.setTypeApartment(resultSet.getString("type_apartment"));
                order.setBreakfast(resultSet.getString("breakfast"));
                order.setPrice(resultSet.getDouble("price"));
                order.setStatus(resultSet.getString("status"));
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception selecting orders" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return orderList;
    }

    @Override
    public List<Order> getNewOrders() throws DAOException {
        List<Order> orderList = new ArrayList<>();
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ServiceDAO serviceDAO = DAOFactory.getInstance().getServiceDAO();
        try {
            preparedStatement = connection.prepareStatement(SELECT_NEW_ORDERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Order order = new Order();
                User user = new User();
                order.setIdOrder(resultSet.getInt("id_order"));
                user.setIdUser(resultSet.getInt("id_user"));
                user.setLogin(resultSet.getString("email"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role_name"));
                user.setSurname(resultSet.getString("surname"));
                user.setName(resultSet.getString("name"));
                user.setMiddleName(resultSet.getString("middle_name"));
                user.setDateBirth(resultSet.getDate("date_birth"));
                user.setTelephone(resultSet.getString("telephone"));
                user.setAvatar(resultSet.getString("avatar"));
                order.setUser(user);
                order.setRoomNumber(resultSet.getInt("room_number"));
                order.setArrivalDate(resultSet.getDate("arrival_date"));
                order.setDepartureDate(resultSet.getDate("departure_date"));
                order.setNoAdults(resultSet.getInt("no_adults"));
                order.setNoChildren(resultSet.getInt("no_children"));
                order.setTypeApartment(resultSet.getString("type_apartment"));
                order.setBreakfast(resultSet.getString("breakfast"));
                order.setPrice(resultSet.getDouble("price"));
                order.setStatus(resultSet.getString("status"));
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception selecting orders" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return orderList;
    }

    @Override
    public Order getOrderById(int idOrder) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        Order order = new Order();
        try {
            preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_ID);
            preparedStatement.setInt(1, idOrder);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                order.setIdOrder(resultSet.getInt("id_order"));
                //order.setIdUser(resultSet.getInt("id_user"));
                order.setRoomNumber(resultSet.getInt("room_number"));
                order.setArrivalDate(resultSet.getDate("arrival_date"));
                order.setDepartureDate(resultSet.getDate("departure_date"));
                order.setNoAdults(resultSet.getInt("no_adults"));
                order.setNoChildren(resultSet.getInt("no_children"));
                order.setTypeApartment(resultSet.getString("type_apartment"));
                order.setBreakfast(resultSet.getString("breakfast"));
                order.setPrice(resultSet.getDouble("price"));
                order.setStatus(resultSet.getString("status"));
            }
        } catch (SQLException e) {
            throw new DAOException("Exception selecting users orders" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return order;
    }

    @Override
    public boolean updateOrder(Order order) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_ORDER);
            preparedStatement.setInt(1, order.getRoomNumber());
            preparedStatement.setDate(2, order.getArrivalDate());
            preparedStatement.setDate(3, order.getDepartureDate());
            preparedStatement.setInt(4, order.getNoAdults());
            preparedStatement.setInt(5, order.getNoChildren());
            preparedStatement.setString(6, order.getTypeApartment());
            preparedStatement.setString(7, order.getBreakfast());
            preparedStatement.setDouble(8, order.getPrice());
            preparedStatement.setString(9, order.getStatus());
            preparedStatement.setInt(10, order.getIdOrder());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception updating order" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }

    @Override
    public boolean updateStatusById(int idOrder) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_STATUS_ORDER);
            preparedStatement.setInt(1, idOrder);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception updating order" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }
}
