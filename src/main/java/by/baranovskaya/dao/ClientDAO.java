package by.baranovskaya.dao;

import by.baranovskaya.entity.Client;
import by.baranovskaya.exception.DAOException;
import by.baranovskaya.exception.DBException;

import java.util.List;

public interface ClientDAO extends AbstractDAO{
    List<Client> getAll() throws DAOException;
    boolean addClient(Client client) throws DAOException;
    boolean findClientByLoginPassword(String login, String password) throws DAOException;
}
