package by.baranovskaya.service;

import by.baranovskaya.dao.factory.DAOFactory;
import by.baranovskaya.dao.ServiceDAO;
import by.baranovskaya.entity.Service;
import by.baranovskaya.exception.DAOException;
import by.baranovskaya.exception.ServiceException;

import java.util.List;

public class HotelService {
    private ServiceDAO serviceDAO = DAOFactory.getInstance().getServiceDAO();

    public boolean addService(Service service) throws ServiceException {
        try {
            return serviceDAO.addService(service);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public List<Service> getServices() throws ServiceException {
        try {
            return serviceDAO.getAllServices();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public boolean deleteService(int idService) throws ServiceException {
        try {
            return serviceDAO.deleteService(idService);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public Service findService(int idService) throws ServiceException {
        try {
            return serviceDAO.findServiceById(idService);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public boolean updateService(Service service) throws ServiceException {
        try {
            return serviceDAO.updateServiceById(service);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
