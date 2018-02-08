package by.baranovskaya.service;

import by.baranovskaya.dao.factory.DAOFactory;
import by.baranovskaya.dao.RoomDAO;
import by.baranovskaya.entity.Room;
import by.baranovskaya.exception.DAOException;
import by.baranovskaya.exception.ServiceException;

import java.util.List;
import java.util.Set;

public class RoomService {
    private RoomDAO roomDAO = DAOFactory.getInstance().getRoomDAO();

    public boolean addRoom(Room room) throws ServiceException {
        try {
            return roomDAO.addRoom(room);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


    public boolean deleteRoomNumber(int roomNumber) throws ServiceException {
        try {
           return roomDAO.deleteRoomNumber(roomNumber);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public Set<Integer> getRoomNumbers() throws ServiceException {
        try {
            return roomDAO.getRoomNumbers();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public List<Integer> getFreeRoomNumbers() throws ServiceException {
        try {
            return roomDAO.getFreeRoomNumbers();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
    public boolean updateRoom(Room room) throws ServiceException {
        try {
            return roomDAO.updateRoomByNumber(room);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
