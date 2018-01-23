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

public class EditRoomCommand implements Command{
    private final static Logger LOGGER = LogManager.getLogger(EditRoomCommand.class);

    private final static String PARAM_ID = "id";

    private RoomService roomService;

    public EditRoomCommand(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Room room = null;
        try {
            room = roomService.findRoomByNumber(Integer.parseInt(request.getParameter(PARAM_ID)));
            request.setAttribute("room", room);
            page = PageConstant.PATH_PAGE_ADMIN_EDIT_ROOM;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
        }
        return page;
    }
}
