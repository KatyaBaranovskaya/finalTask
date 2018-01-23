package by.baranovskaya.dao;

import by.baranovskaya.entity.Room;
import by.baranovskaya.exception.DAOException;

import java.util.List;
import java.util.Set;

public interface RoomDAO extends AbstractDAO {
    List<Room> getAll() throws DAOException;
    boolean addRoom(Room room) throws DAOException;
    int countRoom() throws DAOException;
    boolean deleteRoom(int roomNumber) throws DAOException;
    Set<String> getTypesRoom() throws DAOException;
    Room findRoomByNumber(int roomNumber) throws DAOException;
    boolean updateRoomByNumber(Room room) throws DAOException;
}
