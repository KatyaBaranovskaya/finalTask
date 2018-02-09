package by.baranovskaya.dao.factory;

import by.baranovskaya.dao.*;
import by.baranovskaya.dao.impl.*;

public class DAOFactory {
    private final static DAOFactory instance = new DAOFactory();

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }

    public TypeRoomDAO getTypeRoomDAO() {
        return new TypeRoomImpl();
    }

    public RoomDAO getRoomDAO() {
        return new RoomDAOImpl();
    }

    public ServiceDAO getServiceDAO() {
        return new ServiceDAOImpl();
    }

    public OrderDAO getOrderDAO() {
        return new OrderDAOImpl();
    }
}
