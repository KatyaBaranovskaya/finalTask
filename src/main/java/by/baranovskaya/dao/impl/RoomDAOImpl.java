package by.baranovskaya.dao.impl;

import by.baranovskaya.dao.RoomDAO;
import by.baranovskaya.dao.connection.ConnectionPool;
import by.baranovskaya.dao.connection.ProxyConnection;
import by.baranovskaya.entity.Room;
import by.baranovskaya.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoomDAOImpl implements RoomDAO {
    private final static String SELECT_ALL_ROOM = "SELECT room_number, type_room, capacity, price, status, picture, description FROM rooms";
    private final static String COUNT_ROOM = "SELECT count(*) FROM rooms";
    private final static String SELECT_TYPES_ROOM = "SELECT type_room FROM hotel.rooms;";
    private final static String INSERT_ROOM = "INSERT INTO rooms(room_number, type_room, capacity, price, status, picture, description) VALUES (?,?,?,?,?,?,?)";
    public final static String DELETE_ROOM = "DELETE FROM rooms WHERE room_number=?";
    public final static String FIND_ROOM_BY_NUMBER = "SELECT room_number, type_room, capacity, price, status, picture, description FROM rooms WHERE room_number=?";
    public final static String UPDATE_ROOM_BY_NUMBER = "UPDATE rooms SET room_number=?, type_room=?, capacity=?, price=?, status=?, picture=?, description=? WHERE room_number=?";

    @Override
    public List<Room> getAll() throws DAOException {
        List<Room> listRoom = new ArrayList<>();
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_ROOM);
            while (resultSet.next()) {
                Room room = new Room();
                room.setRoomNumber(resultSet.getInt("room_number"));
                room.setTypeRoom(resultSet.getString("type_room"));
                room.setCapacity(resultSet.getInt("capacity"));
                room.setPrice(resultSet.getDouble("price"));
                room.setStatus(resultSet.getString("status"));
                room.setPicture(resultSet.getString("picture"));
                room.setDescription(resultSet.getString("description"));
                listRoom.add(room);
            }
        } catch (SQLException e) {
            throw new DAOException("Exception selecting all clients" + e);
        } finally {
            close(statement);
            close(connection);
        }
        return listRoom;
    }

    @Override
    public boolean addRoom(Room room) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_ROOM);
            preparedStatement.setInt(1, room.getRoomNumber());
            preparedStatement.setString(2, room.getTypeRoom());
            preparedStatement.setInt(3, room.getCapacity());
            preparedStatement.setDouble(4, room.getPrice());
            preparedStatement.setString(5, room.getStatus());
            preparedStatement.setString(6, room.getPicture());
            preparedStatement.setString(7, room.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception inserting room" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }

    @Override
    public boolean deleteRoom(int roomNumber) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DELETE_ROOM);
            preparedStatement.setInt(1, roomNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception deleting room" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }

    public int countRoom() throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(COUNT_ROOM);
            if (resultSet.next()) {
                return resultSet.getInt(1); // параметр
            }
        } catch (SQLException e) {
            throw new DAOException("Exception counting all room" + e);
        } finally {
            close(statement);
            close(connection);
        }
        return 0;
    }

    @Override
    public Set<String> getTypesRoom() throws DAOException {
        Set<String> setTypes = new HashSet<>();
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_TYPES_ROOM);
            while (resultSet.next()) {
                setTypes.add(resultSet.getString("type_room"));
            }
        } catch (SQLException e) {
            throw new DAOException("Exception selecting all types" + e);
        } finally {
            close(statement);
            close(connection);
        }
        return setTypes;
    }

    @Override
    public Room findRoomByNumber(int roomNumber) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        Room room = new Room();
        try {
            preparedStatement = connection.prepareStatement(FIND_ROOM_BY_NUMBER);
            preparedStatement.setInt(1, roomNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                room.setRoomNumber(resultSet.getInt("room_number"));
                room.setTypeRoom(resultSet.getString("type_room"));
                room.setCapacity(resultSet.getInt("capacity"));
                room.setPrice(resultSet.getDouble("price"));
                room.setStatus(resultSet.getString("status"));
                room.setPicture(resultSet.getString("picture"));
                room.setDescription(resultSet.getString("description"));
            }
        } catch (SQLException e) {
            throw new DAOException("Exception selecting room by number" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return room;
    }

    @Override
    public boolean updateRoomByNumber(Room room) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_ROOM_BY_NUMBER);
            preparedStatement.setInt(1, room.getRoomNumber());
            preparedStatement.setString(2, room.getTypeRoom());
            preparedStatement.setInt(3, room.getCapacity());
            preparedStatement.setDouble(4, room.getPrice());
            preparedStatement.setString(5, room.getStatus());
            preparedStatement.setString(6, room.getPicture());
            preparedStatement.setString(7, room.getDescription());
            preparedStatement.setInt(8, room.getRoomNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception updating room" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }
}
   /* int affected = preparedStatement.executeUpdate();
    return (affectrd > 0);*/