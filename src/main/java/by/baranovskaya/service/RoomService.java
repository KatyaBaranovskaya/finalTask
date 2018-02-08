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
        boolean flag;
        try {
            flag = roomDAO.addRoom(room);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }


    public boolean deleteRoomNumber(int roomNumber) throws ServiceException {
        boolean flag;
        try {
            flag = roomDAO.deleteRoomNumber(roomNumber);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }

   /* public TypeRoom findRoomByNumber(int idType) throws ServiceException {
        TypeRoom typeRoom;
        try {
            typeRoom = roomDAO.findRoomByNumber(idType);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return typeRoom;
    }*/

    public Set<Integer> getRoomNumbers() throws ServiceException {
        Set<Integer> typeRoomList;
        try {
            typeRoomList = roomDAO.getRoomNumbers();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return typeRoomList;
    }

    public List<Integer> getFreeRoomNumbers() throws ServiceException {
        List<Integer> typeRoomList;
        try {
            typeRoomList = roomDAO.getFreeRoomNumbers();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return typeRoomList;
    }
    public boolean updateRoom(Room room) throws ServiceException {
        boolean flag;
        try {
            flag = roomDAO.updateRoomByNumber(room);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }

  /* public Set<String> getTypesRoom() throws ServiceException {
        Set<String> listTypes;
        try {
            listTypes = roomDAO.getTypesRoom();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return listTypes;
    }

    public Room findRoomByNumber(int roomNumber) throws ServiceException {
        Room room;
        try {
            room = roomDAO.findRoomByNumber(roomNumber);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return room;
    }

    public boolean updateRoom(Room room) throws ServiceException {
        boolean flag;
        try {
            flag = roomDAO.updateRoomByNumber(room);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }*/
}
