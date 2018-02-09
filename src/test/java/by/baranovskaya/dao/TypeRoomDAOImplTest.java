package by.baranovskaya.dao;

import by.baranovskaya.dao.factory.DAOFactory;
import by.baranovskaya.entity.TypeRoom;
import by.baranovskaya.exception.DAOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class TypeRoomDAOImplTest {
    private static TypeRoomDAO typeRoomDAO;
    private static TypeRoom typeRoom;

    @BeforeClass
    public static void init() {
        typeRoomDAO = DAOFactory.getInstance().getTypeRoomDAO();
        typeRoom = new TypeRoom();
        typeRoom.setTypeRoom("SNGL");
        typeRoom.setCapacity(2);
        typeRoom.setPrice(34);
        typeRoom.setDescription("Удобные апартаменты на двоих");
        typeRoom.setImage("room1.jpg");
    }

    @Test
    public void getAllRoomTypesTest() throws DAOException {
        List<TypeRoom> typeRoomList = typeRoomDAO.getRoomTypes();
        Assert.assertFalse(typeRoomList.isEmpty());
    }

    @Test
    public void addTypeRoomTest() throws DAOException {
        typeRoomDAO.addTypeRoom(typeRoom);
    }
}
