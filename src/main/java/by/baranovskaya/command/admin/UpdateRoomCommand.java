package by.baranovskaya.command.admin;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.MessageConstants;
import by.baranovskaya.constant.MessageProperty;
import by.baranovskaya.constant.PageConstants;
import by.baranovskaya.constant.ParameterConstants;
import by.baranovskaya.controller.Router;
import by.baranovskaya.entity.Room;
import by.baranovskaya.entity.TypeRoom;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.RoomService;
import by.baranovskaya.service.TypeRoomService;
import by.baranovskaya.validation.DataValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UpdateRoomCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(AddRoomCommand.class);

    private RoomService roomService;
    private TypeRoomService typeRoomService;

    public UpdateRoomCommand(RoomService roomService, TypeRoomService typeRoomService) {
        this.roomService = roomService;
        this.typeRoomService = typeRoomService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        Router router = new Router();
        Room room;

        room = initRoom(request);
        if (room != null) {
            try {
                if (roomService.updateRoom(room)) {
                    page = PageConstants.ROOM_TYPES_PAGE;
                    router.setRouteType(Router.RouteType.REDIRECT);
                } else {
                    page = PageConstants.EDIT_ROOM_PAGE;
                    router.setRouteType(Router.RouteType.FORWARD);
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
            }
        } else {
            setErrorMessage(request, MessageConstants.ERROR_ROOM_LABEL, MessageProperty.ERROR_ROOM_MESSAGE);
            router.setRouteType(Router.RouteType.FORWARD);
            page = PageConstants.EDIT_ROOM_PAGE;
        }

        router.setPagePath(page);
        return router;
    }

    private Room initRoom(HttpServletRequest request) {
        Room room = new Room();
        int roomNumber = Integer.parseInt(request.getParameter(ParameterConstants.ROOM_NUMBER));
        TypeRoom typeRoom = getTypeRoom(request.getParameter(ParameterConstants.TYPE_ROOM));
        String status = request.getParameter(ParameterConstants.STATUS);

        if (DataValidator.validateRoom(roomNumber, typeRoom, status)) {
            room.setRoomNumber(roomNumber);
            room.setTypeRoom(typeRoom);
            room.setStatus(status);
        }
        return room;
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
