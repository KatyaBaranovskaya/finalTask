package by.baranovskaya.command.admin;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstant;
import by.baranovskaya.entity.Room;
import by.baranovskaya.exception.DAOException;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.AdminService;
import by.baranovskaya.service.HotelService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PrintRoomCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(PrintRoomCommand.class);

    private HotelService hotelService;

    public PrintRoomCommand(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        List<Room> listRoom;

        try {
            listRoom = hotelService.getAllRoom();
            request.setAttribute("rooms", listRoom);
            page = PageConstant.PATH_PAGE_ADMIN_ROOMS;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
        }
        return page;
    }
}
