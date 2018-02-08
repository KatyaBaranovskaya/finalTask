package by.baranovskaya.dao.impl;

import by.baranovskaya.dao.TypeRoomDAO;
import by.baranovskaya.dao.connection.ConnectionPool;
import by.baranovskaya.dao.connection.ProxyConnection;
import by.baranovskaya.entity.TypeRoom;
import by.baranovskaya.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TypeRoomImpl implements TypeRoomDAO {
    private final static String COUNT_ROOM_TYPES = "SELECT count(*) FROM room_types";
    private final static String SELECT_ROOM_TYPES = "SELECT id_type, type_name, capacity, price, description, image FROM room_types";
    private final static String INSERT_TYPE_ROOM = "INSERT INTO room_types(type_name, capacity, price, description, image) VALUES (?,?,?,?,?)";
    private final static String DELETE_TYPE_ROOM = "DELETE FROM room_types WHERE id_type=?";
    private final static String FIND_TYPE_ROOM_BY_NUMBER = "SELECT id_type, type_name, capacity, price, description, image FROM room_types WHERE id_type=?";
    private final static String UPDATE_TYPE_ROOM_BY_ID = "UPDATE room_types SET type_name=? capacity=?, price=?, description=?, image=? WHERE id_type=?";
    private final static String SELECT_TYPES = "SELECT type_name FROM room_types";
    private final static String FIND_TYPE_ROOM_BY_NAME = "SELECT id_type, type_name, capacity, price, description, image FROM room_types WHERE type_name=?";
    private final static String FIND_PRICE_BY_TYPE = "SELECT price FROM room_types WHERE type_name=? ";
    private final static String FIND_ROOM_TYPES_BY_PRICE = "SELECT id_type, type_name, capacity, price, description, image FROM room_types WHERE price < ? AND price > ?";

    @Override
    public List<TypeRoom> getRoomTypes() throws DAOException {
        List<TypeRoom> typeRoomList = new ArrayList<>();
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ROOM_TYPES);
            while (resultSet.next()) {
                TypeRoom typeRoom = initTypeRoomFromResultSet(resultSet);
                typeRoomList.add(typeRoom);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception selecting all room types" + e);
        } finally {
            close(statement);
            close(connection);
        }
        return typeRoomList;
    }

    @Override
    public int countRoomTypes() throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(COUNT_ROOM_TYPES);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception counting all room types" + e);
        } finally {
            close(statement);
            close(connection);
        }
        return 0;
    }

    @Override
    public boolean deleteTypeRoom(int idType) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DELETE_TYPE_ROOM);
            preparedStatement.setInt(1, idType);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception deleting type room" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }

    @Override
    public TypeRoom findTypeRoomById(int idType) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        TypeRoom typeRoom = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_TYPE_ROOM_BY_NUMBER);
            preparedStatement.setInt(1, idType);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                typeRoom = initTypeRoomFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception selecting type room by id" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return typeRoom;
    }

    @Override
    public boolean addTypeRoom(TypeRoom typeRoom) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_TYPE_ROOM);
            preparedStatement.setString(1, typeRoom.getTypeRoom());
            preparedStatement.setInt(2, typeRoom.getCapacity());
            preparedStatement.setDouble(3, typeRoom.getPrice());
            preparedStatement.setString(4, typeRoom.getDescription());
            preparedStatement.setString(5, typeRoom.getImage());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception inserting type room" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }

    @Override
    public boolean updateTypeRoomById(TypeRoom typeRoom) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_TYPE_ROOM_BY_ID);
            preparedStatement.setString(1, typeRoom.getTypeRoom());
            preparedStatement.setInt(2, typeRoom.getCapacity());
            preparedStatement.setDouble(3, typeRoom.getPrice());
            preparedStatement.setString(4, typeRoom.getImage());
            preparedStatement.setString(5, typeRoom.getDescription());
            preparedStatement.setString(6, typeRoom.getImage());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception updating type room" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }

    @Override
    public Set<String> getTypes() throws DAOException {
        Set<String> typesList = new TreeSet<>();
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_TYPES);
            while (resultSet.next()) {
                typesList.add(resultSet.getString("type_name"));
            }
        } catch (SQLException e) {
            throw new DAOException("Exception selecting all types" + e);
        } finally {
            close(statement);
            close(connection);
        }
        return typesList;
    }

    @Override
    public TypeRoom getTypeByName(String type) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        TypeRoom typeRoom = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_TYPE_ROOM_BY_NAME);
            preparedStatement.setString(1, type);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                typeRoom = initTypeRoomFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception selecting type room by type" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return typeRoom;
    }

    @Override
    public double findPriceByType(String typeRoom) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        double price = 0;
        try {
            preparedStatement = connection.prepareStatement(FIND_PRICE_BY_TYPE);
            preparedStatement.setString(1, typeRoom);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                price = resultSet.getInt("price");
            }
        } catch (SQLException e) {
            throw new DAOException("Exception selecting price by type" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return price;
    }

    @Override
    public List<TypeRoom> findRoomTypesByPrice(int minPrice, int maxPrice) throws DAOException {
        List<TypeRoom> typeRoomList = new ArrayList<>();
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_ROOM_TYPES_BY_PRICE);
            preparedStatement.setInt(1, maxPrice);
            preparedStatement.setInt(2, minPrice);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TypeRoom typeRoom = initTypeRoomFromResultSet(resultSet);
                typeRoomList.add(typeRoom);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception searching room types by price" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return typeRoomList;
    }

    private TypeRoom initTypeRoomFromResultSet(ResultSet resultSet) throws SQLException {
        TypeRoom typeRoom = new TypeRoom();
        typeRoom.setIdType(resultSet.getInt("id_type"));
        typeRoom.setTypeRoom(resultSet.getString("type_name"));
        typeRoom.setCapacity(resultSet.getInt("capacity"));
        typeRoom.setPrice(resultSet.getDouble("price"));
        typeRoom.setDescription(resultSet.getString("description"));
        typeRoom.setImage(resultSet.getString("image"));
        return typeRoom;
    }
}
