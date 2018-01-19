package by.baranovskaya.dao;

import by.baranovskaya.dao.impl.ClientDAOImpl;
import by.baranovskaya.dao.impl.RoomDAOImpl;
import by.baranovskaya.dao.impl.ServiceDAOIml;

public class DAOFactory {

    public static ClientDAO getClientDAO(){
        return  new ClientDAOImpl();
    }

    public static RoomDAO getRoomDAO(){
        return  new RoomDAOImpl();
    }

    public static ServiceDAO getServiceDAO(){
        return  new ServiceDAOIml();
    }
}
