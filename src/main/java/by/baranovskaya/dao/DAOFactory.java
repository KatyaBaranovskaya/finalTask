package by.baranovskaya.dao;

import by.baranovskaya.dao.impl.ClientDAOImpl;
import by.baranovskaya.dao.impl.OrderDAOImpl;
import by.baranovskaya.dao.impl.RoomDAOImpl;
import by.baranovskaya.dao.impl.ServiceDAOImpl;

public class DAOFactory {

    public static ClientDAO getClientDAO(){
        return  new ClientDAOImpl();
    }

    public static RoomDAO getRoomDAO(){
        return  new RoomDAOImpl();
    }

    public static ServiceDAO getServiceDAO(){
        return  new ServiceDAOImpl();
    }

    public static OrderDAO getOrderDAO(){
        return  new OrderDAOImpl();
    }
}
