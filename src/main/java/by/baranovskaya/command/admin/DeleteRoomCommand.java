package by.baranovskaya.command.admin;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstant;
import by.baranovskaya.entity.Room;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.AdminService;
import by.baranovskaya.service.HotelService;
import by.baranovskaya.service.RoomService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteRoomCommand implements Command{
    private final static Logger LOGGER = LogManager.getLogger(DeleteRoomCommand.class);

    private final static String PARAM_ID = "id";
    private RoomService roomService;

    public DeleteRoomCommand(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        List<Room> listRoom;

        try {
            if(roomService.deleteRoom(Integer.parseInt(request.getParameter(PARAM_ID)))) {
                listRoom = roomService.getAllRoom();
                request.setAttribute("rooms", listRoom);
                page = PageConstant.PATH_PAGE_ADMIN_ROOMS;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
        }

        return page;
    }
}
