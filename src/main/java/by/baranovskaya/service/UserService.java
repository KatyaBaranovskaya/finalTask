package by.baranovskaya.service;

import by.baranovskaya.dao.ClientDAO;
import by.baranovskaya.dao.DAOFactory;
import by.baranovskaya.entity.Client;
import by.baranovskaya.exception.DAOException;
import by.baranovskaya.exception.ServiceException;

public class UserService {
    private ClientDAO clientDAO = DAOFactory.getClientDAO();

    public Client checkUserIsExist(String login, String password) throws ServiceException {
        Client client;
        try {
            client = clientDAO.findClientByLoginPassword(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return client;
    }

    public boolean registerUser(Client client) throws ServiceException {
        boolean flag;
        try {
            flag = clientDAO.addClient(client);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }

    public Client findClientById(int idClient) throws ServiceException {
        Client client;
        try {
            client = clientDAO.findClientById(idClient);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return client;
    }

    public boolean updatePassword(int idClient, String password) throws ServiceException {
        boolean flag;
        try {
            flag = clientDAO.updatePasswordById(idClient, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }

    public boolean updateAvatar(int idClient, String avatarPath) throws ServiceException {
        boolean flag;
        try {
            flag = clientDAO.updateAvatarById(idClient, avatarPath);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }

    public boolean updateClientInfo(Client client) throws ServiceException {
        boolean flag;
        try {
            flag = clientDAO.updateClientInfo(client);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }
}
