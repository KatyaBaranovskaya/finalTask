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
        List<User> userList;
        try {
            userList =  userDAO.getAllUsers();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return userList;
    }

    public boolean deleteUser(int idUser) throws ServiceException {
        boolean flag;
        try {
            flag = userDAO.deleteUser(idUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }
}
