package by.baranovskaya.service;

import by.baranovskaya.dao.DAOFactory;
import by.baranovskaya.dao.RoomDAO;
import by.baranovskaya.entity.Room;
import by.baranovskaya.exception.DAOException;
import by.baranovskaya.exception.ServiceException;

import java.util.List;

public class AdminService {
    private RoomDAO roomDAO = DAOFactory.getRoomDAO();

    public List<Room> getAllRoom() throws ServiceException {
        List<Room> listRoom = null;
        try {
            listRoom =  roomDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return listRoom;
    }

    public boolean addRoom(Room room) throws ServiceException {
        boolean flag = false;
        try {
            if(roomDAO.addRoomType(room) && roomDAO.addRoom(room)) {
                flag = true;
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }
}
