package by.baranovskaya.dao;

import by.baranovskaya.entity.TypeRoom;
import by.baranovskaya.exception.DAOException;

import java.util.List;
import java.util.Set;

public interface TypeRoomDAO extends AbstractDAO {

    List<TypeRoom> getRoomTypes() throws DAOException;

    int countRoomTypes() throws DAOException;

    boolean deleteTypeRoom(int idType) throws DAOException;

    TypeRoom findTypeRoomById(int idType) throws DAOException;

    boolean addTypeRoom(TypeRoom typeRoom) throws DAOException;

    boolean updateTypeRoomById(TypeRoom typeName) throws DAOException;

    Set<String> getTypes() throws DAOException;

    TypeRoom getTypeByName(String typeName) throws DAOException;

    double findPriceByType(String typeRoom) throws DAOException;

    List<TypeRoom> findRoomTypesByPrice(int minPrice, int maxPrice) throws DAOException;
}
