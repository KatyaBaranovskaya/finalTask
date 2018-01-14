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
    private final static String SELECT_ROOM = "SELECT type_room, capacity, price, description, room_number, status\n" +
            "FROM hotel.types_room JOIN rooms ON rooms.id_type = types_room.id_type";
    private final static String INSERT_ROOM_TYPE = "INSERT INTO types_room(type_room, capacity, price, description) VALUES (?,?,?,?)";
    private final static String INSERT_ROOM = "INSERT INTO rooms(room_number, id_type, status) VALUES (?,LAST_INSERT_ID(),?)";


    public List<Room> getAll() throws DAOException {
        List<Room> listRoom = new ArrayList<>();
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ROOM);
            while (resultSet.next()) {
                Room room = new Room();
                room.setRoomNumber(resultSet.getInt("room_number"));
                room.setStatus(resultSet.getString("status"));
                room.setTypeRoom(resultSet.getString("type_room"));
                room.setCapacity(resultSet.getInt("capacity"));
                room.setPrice(resultSet.getDouble("price"));
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
            preparedStatement.setString(2, room.getStatus());
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
    public boolean addRoomType(Room room) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_ROOM_TYPE);
            preparedStatement.setString(1, room.getTypeRoom());
            preparedStatement.setInt(2, room.getCapacity());
            preparedStatement.setDouble(3, room.getPrice());
            preparedStatement.setString(4, room.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Exception inserting room" + e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return true;
    }
}
