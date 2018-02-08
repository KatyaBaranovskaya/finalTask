package by.baranovskaya.command.admin;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstants;
import by.baranovskaya.constant.ParameterConstants;
import by.baranovskaya.controller.Router;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.RoomService;
import by.baranovskaya.service.TypeRoomService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public class EditRoomsCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(EditRoomsCommand.class);

    private RoomService roomService;
    private TypeRoomService typeRoomService;

    public EditRoomsCommand(RoomService roomService, TypeRoomService typeRoomService) {
        this.roomService = roomService;
        this.typeRoomService = typeRoomService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        Router router = new Router();
        Set<Integer> numbersList;
        Set<String> typesList;

        try {
            numbersList = roomService.getRoomNumbers();
            typesList = typeRoomService.getTypes();
            request.setAttribute(ParameterConstants.NUMBERS, numbersList);
            request.setAttribute(ParameterConstants.TYPES, typesList);
            page = PageConstants.EDIT_ROOM_PAGE;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.FORWARD);
        return router;
    }
}
