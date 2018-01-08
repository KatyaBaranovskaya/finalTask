package by.baranovskaya.dao;

import by.baranovskaya.dao.impl.ClientDAOImpl;

public class DAOFactory {
    public static ClientDAO getClientDao(){
        return  new ClientDAOImpl();
    }
}
