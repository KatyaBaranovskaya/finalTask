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
        boolean flag;
        try {
            flag = serviceDAO.addService(service);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }

    public List<Service> getServices() throws ServiceException {
        List<Service> serviceList;
        try {
            serviceList =  serviceDAO.getAllServices();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return serviceList;
    }

    public boolean deleteSevice(int idService) throws ServiceException {
        boolean flag;
        try {
            flag = serviceDAO.deleteService(idService);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }

    public Service findServiceById(int idService) throws ServiceException {
        Service service;
        try {
            service =  serviceDAO.findServiceById(idService);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return service;
    }

    public boolean updateService(Service service) throws ServiceException {
        boolean flag;
        try {
            flag = serviceDAO.updateServiceById(service);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }

  /*  public Map<Integer, String> getTypesService() throws ServiceException {
        Map<Integer, String> mapTypes;
        try {
            mapTypes =  serviceDAO.getTypesService();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return mapTypes;
    }*/
}
