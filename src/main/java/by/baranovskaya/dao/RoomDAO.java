package by.baranovskaya.dao;

import by.baranovskaya.entity.Room;
import by.baranovskaya.entity.TypeRoom;
import by.baranovskaya.exception.DAOException;

import java.util.List;
import java.util.Set;

public interface RoomDAO extends AbstractDAO {

    boolean deleteRoomNumber(int roomNumber) throws DAOException;

    Set<Integer> getRoomNumbers() throws DAOException;

    List<Integer> getFreeRoomNumbers() throws DAOException;

    boolean addRoom(Room room) throws DAOException;

    boolean updateRoomByNumber(Room room) throws DAOException;
}
