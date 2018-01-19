package by.baranovskaya.service;

import by.baranovskaya.dao.ClientDAO;
import by.baranovskaya.dao.DAOFactory;
import by.baranovskaya.dao.RoomDAO;
import by.baranovskaya.dao.ServiceDAO;
import by.baranovskaya.entity.Client;
import by.baranovskaya.entity.Room;
import by.baranovskaya.entity.Service;
import by.baranovskaya.exception.DAOException;
import by.baranovskaya.exception.ServiceException;

import java.util.List;

public class AdminService {
    private ClientDAO clientDAO = DAOFactory.getClientDAO();
    private RoomDAO roomDAO = DAOFactory.getRoomDAO();
    private ServiceDAO serviceDAO = DAOFactory.getServiceDAO();

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

    public boolean addService(Service service) throws ServiceException {
        boolean flag = false;
        try {
            flag = serviceDAO.addService(service);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }

    public List<Client> getAllClient() throws ServiceException {
        List<Client> clientList = null;
        try {
            clientList =  clientDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return clientList;
    }
}
