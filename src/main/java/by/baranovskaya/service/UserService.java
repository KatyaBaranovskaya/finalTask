package by.baranovskaya.service;

import by.baranovskaya.dao.ClientDAO;
import by.baranovskaya.dao.DAOFactory;
import by.baranovskaya.entity.Client;
import by.baranovskaya.exception.DAOException;
import by.baranovskaya.exception.ServiceException;

public class UserService {
    private ClientDAO clientDAO = DAOFactory.getClientDAO();

    public int checkUserIsExist(String login, String password) throws ServiceException {
        int id = 0;
        try {
            id = clientDAO.findClientByLoginPassword(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return id;
    }

    public boolean registerUser(Client client) throws ServiceException {
        boolean flag = false;
        try {
            flag = clientDAO.addClient(client);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }
}
