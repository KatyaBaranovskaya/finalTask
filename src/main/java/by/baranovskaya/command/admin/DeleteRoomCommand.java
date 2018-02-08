package by.baranovskaya.command.admin;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstants;
import by.baranovskaya.constant.ParameterConstants;
import by.baranovskaya.controller.Router;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.RoomService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteRoomCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(DeleteRoomCommand.class);

    private RoomService roomService;

    public DeleteRoomCommand(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        Router router = new Router();
        int roomNumber = Integer.parseInt(request.getParameter(ParameterConstants.ROOM_NUMBER));

        try {
            if (roomService.deleteRoomNumber(roomNumber)) {
                page = PageConstants.ROOM_TYPES_PAGE;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.FORWARD);
        return router;
    }
}
