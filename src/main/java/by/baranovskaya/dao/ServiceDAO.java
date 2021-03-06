package by.baranovskaya.dao;

import by.baranovskaya.entity.Service;
import by.baranovskaya.exception.DAOException;

import java.util.List;
import java.util.Map;

public interface ServiceDAO extends AbstractDAO {
    List<Service> getAllServices() throws DAOException;

    boolean addService(Service service) throws DAOException;

    boolean deleteService(int idService) throws DAOException;

    boolean updateServiceById(Service service) throws DAOException;

    Service findServiceById(int idService) throws DAOException;
}
