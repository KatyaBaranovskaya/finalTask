package by.baranovskaya.dao;

import by.baranovskaya.entity.Service;
import by.baranovskaya.exception.DAOException;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ServiceDAO extends AbstractDAO {
    List<Service> getAll() throws DAOException;
    boolean addService(Service service) throws DAOException;
    boolean deleteService(int idService) throws DAOException;
    Service findServiceById(int idService) throws DAOException;
    boolean updateServiceById(Service service) throws DAOException;
    Map<Integer, String> getTypesService() throws DAOException;
}
