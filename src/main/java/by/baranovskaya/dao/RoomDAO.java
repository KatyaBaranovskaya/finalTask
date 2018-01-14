package by.baranovskaya.dao;

import by.baranovskaya.entity.Client;
import by.baranovskaya.entity.Room;
import by.baranovskaya.exception.DAOException;

import java.util.List;

public interface RoomDAO extends AbstractDAO {
    List<Room> getAll() throws DAOException;
    boolean addRoom(Room room) throws DAOException;
    boolean addRoomType(Room room) throws DAOException;
}
