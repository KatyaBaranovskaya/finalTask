package by.baranovskaya.service;

import by.baranovskaya.dao.DAOFactory;
import by.baranovskaya.dao.RoomDAO;
import by.baranovskaya.dao.ServiceDAO;
import by.baranovskaya.entity.Room;
import by.baranovskaya.entity.Service;
import by.baranovskaya.exception.DAOException;
import by.baranovskaya.exception.ServiceException;

import java.util.List;

public class HotelService {
    private ServiceDAO serviceDAO = DAOFactory.getServiceDAO();

    public boolean addService(Service service) throws ServiceException {
        boolean flag = false;
        try {
            flag = serviceDAO.addService(service);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }

    public List<Service> getAllService() throws ServiceException {
        List<Service> serviceList = null;
        try {
            serviceList =  serviceDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return serviceList;
    }

    public boolean deleteSevice(int idService) throws ServiceException {
        boolean flag = false;
        try {
            flag = serviceDAO.deleteService(idService);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }
}
