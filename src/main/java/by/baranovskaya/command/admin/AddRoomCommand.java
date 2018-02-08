package by.baranovskaya.command.admin;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstants;
import by.baranovskaya.constant.ParameterConstants;
import by.baranovskaya.controller.Router;
import by.baranovskaya.entity.Room;
import by.baranovskaya.entity.TypeRoom;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.RoomService;
import by.baranovskaya.service.TypeRoomService;
import by.baranovskaya.validation.RoomValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddRoomCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(AddRoomCommand.class);

    private RoomService roomService;
    private TypeRoomService typeRoomService;

    public AddRoomCommand(RoomService roomService, TypeRoomService typeRoomService) {
        this.roomService = roomService;
        this.typeRoomService = typeRoomService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        Router router = new Router();
        Room room = new Room();
        room.setRoomNumber(Integer.parseInt(request.getParameter(ParameterConstants.ROOM_NUMBER)));
        room.setTypeRoom(getTypeRoom(request.getParameter(ParameterConstants.TYPE_ROOM)));
        room.setStatus(request.getParameter(ParameterConstants.STATUS));

        if (RoomValidator.validateRoom(room)) {
            try {
                if (roomService.addRoom(room)) {
                    page = PageConstants.ROOM_TYPES_PAGE;
                } else {
                    //TODO err
                    page = PageConstants.EDIT_ROOM_PAGE;
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
            }
        } else {
            //TODO warn incorrect info
            page = PageConstants.EDIT_ROOM_PAGE;
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.REDIRECT);
        return router;
    }

    private TypeRoom getTypeRoom(String type) {
        TypeRoom typeRoom = null;
        try {
            typeRoom = typeRoomService.getTypeRoom(type);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
        }
        return typeRoom;
    }

}
