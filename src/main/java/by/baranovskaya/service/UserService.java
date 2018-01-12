package by.baranovskaya.service;

import by.baranovskaya.dao.ClientDAO;
import by.baranovskaya.dao.DAOFactory;
import by.baranovskaya.entity.Client;
import by.baranovskaya.exception.DAOException;

public class UserService {
    private ClientDAO dao = DAOFactory.getClientDao();

    public int checkUserIsExist(String login, String password) throws DAOException {
        return dao.findClientByLoginPassword(login, password);
    }

    public boolean registerUser(Client client) throws DAOException {
        return dao.addClient(client);
    }
}
