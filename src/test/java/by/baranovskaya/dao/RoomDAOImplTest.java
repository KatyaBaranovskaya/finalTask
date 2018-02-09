package by.baranovskaya.dao;

import by.baranovskaya.dao.factory.DAOFactory;
import by.baranovskaya.entity.Room;
import by.baranovskaya.entity.TypeRoom;
import by.baranovskaya.exception.DAOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class RoomDAOImplTest {
    private static RoomDAO roomDAO;
    private static Room room;
    private static TypeRoom typeRoom;

    @BeforeClass
    public static void init() {
        roomDAO = DAOFactory.getInstance().getRoomDAO();
        typeRoom = new TypeRoom();
        typeRoom.setIdType(1);
        typeRoom.setTypeRoom("SNGL");
        typeRoom.setCapacity(2);
        typeRoom.setPrice(34);
        typeRoom.setDescription("Удобные апартаменты на двоих");
        typeRoom.setImage("room1.jpg");

        room = new Room();
        room.setRoomNumber(132);
        room.setTypeRoom(typeRoom);
        room.setStatus("свободен");
    }

    @Test
    public void getAllNumbersTest() throws DAOException {
        List<Integer> numbers = roomDAO.getFreeRoomNumbers();
        Assert.assertFalse(numbers.isEmpty());
    }

    @Test
    public void addRoomTest() throws DAOException {
        roomDAO.addRoom(room);
    }

    @Test
    public void deleteRoomNumberTest() throws DAOException {
        roomDAO.deleteRoomNumber(room.getRoomNumber());
    }
}
