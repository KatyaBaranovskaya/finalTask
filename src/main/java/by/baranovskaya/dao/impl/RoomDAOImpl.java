package by.baranovskaya.dao.impl;

import by.baranovskaya.dao.RoomDAO;
import by.baranovskaya.dao.connection.ConnectionPool;
import by.baranovskaya.dao.connection.ProxyConnection;
import by.baranovskaya.entity.Client;
import by.baranovskaya.entity.Room;
import by.baranovskaya.exception.DAOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    private final static String SELECT_ALL_ROOM = "SELECT room_number, type_room, capacity, price, status, picture, description FROM rooms";
    private final static String COUNT_ROOM = "SELECT count(*) FROM rooms";
    private final static String INSERT_ROOM = "INSERT INTO rooms(room_number, type_room, capacity, price, status, picture, description) VALUES (?,?,?,?,?,?,?)";
    public final static String DELETE_ROOM = "DELETE FROM rooms WHERE room_number=?";

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
            System.out.println(room);
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
}
   /* int affected = preparedStatement.executeUpdate();
    return (affectrd > 0);*/