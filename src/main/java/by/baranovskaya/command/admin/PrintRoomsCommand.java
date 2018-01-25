package by.baranovskaya.command.admin;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstant;
import by.baranovskaya.entity.Room;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.RoomService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PrintRoomsCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(PrintRoomsCommand.class);

    private RoomService roomService;

    public PrintRoomsCommand(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int noPage = 1;
        if (request.getParameter("page") != null) {
            noPage = Integer.parseInt(request.getParameter("page"));
        }
        String page = null;
        List<Room> listRoom;

        try {
            listRoom = roomService.getRooms(noPage);

            request.setAttribute("rooms", listRoom);
            request.setAttribute("noOfPages", roomService.getNoOfPages());
            request.setAttribute("currentPage", noPage);

            page = PageConstant.PATH_PAGE_ADMIN_ROOMS;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
        }
        return page;
    }
}
