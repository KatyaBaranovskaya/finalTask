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

    public List<Client> getAllClient() throws ServiceException {
        List<Client> clientList;
        try {
            clientList =  clientDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return clientList;
    }

    public boolean deleteClient(int idClient) throws ServiceException {
        boolean flag;
        try {
            flag = clientDAO.deleteClient(idClient);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }
}
