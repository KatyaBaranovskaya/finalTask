package by.baranovskaya.command.admin;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstants;
import by.baranovskaya.constant.ParameterConstants;
import by.baranovskaya.controller.Router;
import by.baranovskaya.entity.TypeRoom;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.TypeRoomService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteTypeRoomCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(DeleteTypeRoomCommand.class);

    private TypeRoomService typeRoomService;

    public DeleteTypeRoomCommand(TypeRoomService typeRoomService) {
        this.typeRoomService = typeRoomService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        Router router = new Router();
        List<TypeRoom> typeRoomList;
        int idTypeRoom = Integer.parseInt(request.getParameter(ParameterConstants.ID));

        try {
            if (typeRoomService.deleteTypeRoom(idTypeRoom)) {
                int currentPage = (Integer) request.getSession().getAttribute(ParameterConstants.CURRENT_PAGE);
                typeRoomList = typeRoomService.getRooms(currentPage);
                request.setAttribute(ParameterConstants.ROOM_TYPES, typeRoomList);
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
