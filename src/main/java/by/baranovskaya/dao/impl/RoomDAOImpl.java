package by.baranovskaya.dao.impl;

import by.baranovskaya.dao.RoomDAO;
import by.baranovskaya.dao.connection.ConnectionPool;
import by.baranovskaya.dao.connection.ProxyConnection;
import by.baranovskaya.entity.Room;
import by.baranovskaya.entity.TypeRoom;
import by.baranovskaya.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Logger;

public class RoomDAOImpl implements RoomDAO {
    private final static String SELECT_ROOM_NUMBERS = "SELECT room_number FROM hotel.rooms";
    private final static String SELECT_FREE_ROOM_NUMBERS = "SELECT room_number FROM rooms WHERE status = 'свободен'";
    private final static String INSERT_ROOM = "INSERT INTO rooms(room_number, id_type, status) VALUES (?,?,?)";
    private final static String DELETE_ROOM = "DELETE FROM rooms WHERE room_number=?";
    private final static String UPDATE_ROOM_BY_NUMBER = "UPDATE rooms SET id_type=?, status=? WHERE room_number=?";

    @Override
    public boolean deleteRoomNumber(int roomNumber) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DELETE_ROOM);
            preparedStatement.setInt(1, roomNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception deleting room: " + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }

    @Override
    public Set<Integer> getRoomNumbers() throws DAOException {
        Set<Integer> numbersList = new TreeSet<>();
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ROOM_NUMBERS);
            while (resultSet.next()) {
                numbersList.add(resultSet.getInt("room_number"));
            }
        } catch (SQLException e) {
            throw new DAOException("Exception selecting room numbers: " + e);
        } finally {
            close(statement);
            close(connection);
        }
        return numbersList;
    }

    @Override
    public List<Integer> getFreeRoomNumbers() throws DAOException {
        List<Integer> numbersList = new ArrayList<>();
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_FREE_ROOM_NUMBERS);
            while (resultSet.next()) {
                numbersList.add(resultSet.getInt("room_number"));
            }
        } catch (SQLException e) {
            throw new DAOException("Exception selecting free room numbers: " + e);
        } finally {
            close(statement);
            close(connection);
        }
        return numbersList;
    }

    @Override
    public boolean addRoom(Room room) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_ROOM);
            preparedStatement.setInt(1, room.getRoomNumber());
            preparedStatement.setInt(2, room.getTypeRoom().getIdType());
            preparedStatement.setString(3, room.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception inserting room: " + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }

    @Override
    public boolean updateRoomByNumber(Room room) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_ROOM_BY_NUMBER);
            preparedStatement.setInt(1, room.getTypeRoom().getIdType());
            preparedStatement.setString(2, room.getStatus());
            preparedStatement.setInt(3, room.getRoomNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception updating room: " + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }
}