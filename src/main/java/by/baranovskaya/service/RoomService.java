package by.baranovskaya.service;

import by.baranovskaya.dao.DAOFactory;
import by.baranovskaya.dao.RoomDAO;
import by.baranovskaya.entity.Room;
import by.baranovskaya.exception.DAOException;
import by.baranovskaya.exception.ServiceException;

import java.util.List;
import java.util.Set;

public class RoomService {
    private final static int recordsPerPage = 5;
    private RoomDAO roomDAO = DAOFactory.getRoomDAO();

    public boolean addRoom(Room room) throws ServiceException {
        boolean flag = false;
        try {
            flag = roomDAO.addRoom(room);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }


    public boolean deleteRoom(int roomNumber) throws ServiceException {
        boolean flag = false;
        try {
            flag = roomDAO.deleteRoom(roomNumber);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }

    public List<Room> getAllRoom() throws ServiceException {
        List<Room> listRoom = null;
        try {
            listRoom =  roomDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return listRoom;
    }

    public List<Room> getRooms(int noPage) throws ServiceException {
        List<Room> listRoom = getAllRoom(); // исключение ?
        int step = (noPage - 1) * recordsPerPage;
        if (step + recordsPerPage >= listRoom.size()) {
            listRoom = listRoom.subList(step, listRoom.size());
        } else {
            listRoom = listRoom.subList(step, step + recordsPerPage);
        }
        return listRoom;
    }

    public int getNoOfPages() throws ServiceException {
        int noOfRecords = 0;
        try {
            noOfRecords = roomDAO.countRoom();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    }

    public Set<String> getTypesRoom() throws ServiceException {
        Set<String> listTypes  = null;
        try {
            listTypes =  roomDAO.getTypesRoom();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return listTypes;
    }

    public Room findRoomByNumber(int roomNumber) throws ServiceException {
        Room room = null;
        try {
            room =  roomDAO.findRoomByNumber(roomNumber);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return room;
    }

    public boolean updateRoom(Room room) throws ServiceException {
        boolean flag = false;
        try {
            flag = roomDAO.updateRoomByNumber(room);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }
}
