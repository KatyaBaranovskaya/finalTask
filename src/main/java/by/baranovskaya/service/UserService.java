package by.baranovskaya.service;

import by.baranovskaya.dao.UserDAO;
import by.baranovskaya.dao.factory.DAOFactory;
import by.baranovskaya.entity.User;
import by.baranovskaya.exception.DAOException;
import by.baranovskaya.exception.ServiceException;

import java.util.List;

public class UserService {
    private UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

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

    public User checkUserIsExist(String login, String password) throws ServiceException {
        try {
            return userDAO.findUserByLoginPassword(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public boolean checkLoginIsExist(String login) throws ServiceException {
        try {
            return userDAO.findUserByLogin(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public User checkLoginEmailIsExist(String login, String email) throws ServiceException {
        try {
            return userDAO.findUserByLoginEmail(login, email);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public boolean registerUser(User user) throws ServiceException {
        try {
            return userDAO.addUser(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public User findUserById(int idClient) throws ServiceException {
        try {
            return userDAO.findUserById(idClient);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public boolean updatePassword(int idClient, String password) throws ServiceException {
        try {
            return userDAO.updatePasswordById(idClient, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public boolean updateAvatar(int idClient, String avatarPath) throws ServiceException {
        try {
            return userDAO.updateAvatarById(idClient, avatarPath);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public boolean updateUserInfo(User user) throws ServiceException {
        try {
            return userDAO.updateUser (user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
