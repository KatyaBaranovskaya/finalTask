package by.baranovskaya.service;

import by.baranovskaya.reader.FileReader;
import by.baranovskaya.constant.ParameterConstants;
import by.baranovskaya.dao.TypeRoomDAO;
import by.baranovskaya.dao.factory.DAOFactory;
import by.baranovskaya.entity.Order;
import by.baranovskaya.entity.TypeRoom;
import by.baranovskaya.exception.DAOException;
import by.baranovskaya.exception.ParseDataException;
import by.baranovskaya.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Set;

import static by.baranovskaya.constant.ParameterConstants.RECORDS_PER_PAGE;

public class TypeRoomService {
    private final static Logger LOGGER = LogManager.getLogger(TypeRoomService.class);

    private TypeRoomDAO typeRoomDAO = DAOFactory.getInstance().getTypeRoomDAO();

    public boolean addTypeRoom(TypeRoom typeRoom) throws ServiceException {
        try {
            return typeRoomDAO.addTypeRoom(typeRoom);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public boolean deleteTypeRoom(int roomNumber) throws ServiceException {
        try {
            return typeRoomDAO.deleteTypeRoom(roomNumber);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
 
    public boolean updateTypeRoom(TypeRoom typeRoom) throws ServiceException {
        try {
            return typeRoomDAO.updateTypeRoomById(typeRoom);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public TypeRoom findTypeRoom(int idType) throws ServiceException {
        try {
            return typeRoomDAO.findTypeRoomById(idType);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public List<TypeRoom> getTypesRoom() throws ServiceException {
        try {
            return typeRoomDAO.getRoomTypes();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public List<TypeRoom> getRooms(int noPage) throws ServiceException {
        List<TypeRoom> typeRoomList = getTypesRoom(); // исключение ?
        int step = (noPage - 1) * RECORDS_PER_PAGE;
        if (step + RECORDS_PER_PAGE >= typeRoomList.size()) {
            typeRoomList = typeRoomList.subList(step, typeRoomList.size());
        } else {
            typeRoomList = typeRoomList.subList(step, step + RECORDS_PER_PAGE);
        }
        return typeRoomList;
    }

    public int getNoOfPages() throws ServiceException {
        int noOfRecords;
        try {
            noOfRecords = typeRoomDAO.countRoomTypes();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return (int) Math.ceil(noOfRecords * 1.0 / RECORDS_PER_PAGE);
    }

    public Set<String> getTypes() throws ServiceException {
        try {
            return typeRoomDAO.getTypes();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public TypeRoom getTypeRoom(String type) throws ServiceException {
        try {
            return typeRoomDAO.getTypeByName(type);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    private double getPrice(String type) throws ServiceException {
        try {
            return typeRoomDAO.findPriceByType(type);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public List<TypeRoom> findRoomTypes(int minPrice, int maxPrice) throws ServiceException {
        try {
            return typeRoomDAO.findRoomTypesByPrice(minPrice, maxPrice);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public double calculatePrice(Order order) throws ServiceException, ParseDataException {
        double breakfast = FileReader.readData();
        double price = getPrice(order.getTypeApartment());
        double diff = ((order.getDepartureDate().getTime() - order.getArrivalDate().getTime()) / ParameterConstants.DIVISION_DAYS);
        price *= diff;
        if (order.getBreakfast().equals(ParameterConstants.YES)) {
            price += (breakfast * diff);
        }
        return price;
    }
}
