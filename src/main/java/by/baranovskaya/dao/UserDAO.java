package by.baranovskaya.dao;

import by.baranovskaya.entity.User;
import by.baranovskaya.exception.DAOException;

import java.util.List;

public interface UserDAO extends AbstractDAO {
    List<User> getAllUsers() throws DAOException;

    boolean addUser(User user) throws DAOException;

    boolean deleteUser(int idUser) throws DAOException;

    User findUserByLoginPassword(String login, String password) throws DAOException;

    boolean findUserByLogin(String login) throws DAOException;

    User findUserByLoginEmail(String login, String email) throws DAOException;

    User findUserById(int idUser) throws DAOException;

    boolean updatePasswordById(int idUser, String password) throws DAOException;

    boolean updateAvatarById(int idClient, String avatarPath) throws DAOException;

    boolean updateUserInfo(User user) throws DAOException;
}
