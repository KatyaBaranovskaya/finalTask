package by.baranovskaya.service;

import by.baranovskaya.dao.UserDAO;
import by.baranovskaya.dao.factory.DAOFactory;
import by.baranovskaya.entity.User;
import by.baranovskaya.exception.DAOException;
import by.baranovskaya.exception.ServiceException;

import java.util.List;

public class AdminService {
    private UserDAO userDAO = DAOFactory.getInstance().getClientDAO();

    public List<User> getAllUsers() throws ServiceException {
        try {
            return userDAO.getAllUsers();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public boolean deleteUser(int idUser) throws ServiceException {
        try {
            return  userDAO.deleteUser(idUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
