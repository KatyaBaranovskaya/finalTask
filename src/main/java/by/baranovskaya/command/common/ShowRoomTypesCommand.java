package by.baranovskaya.command.common;

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

public class ShowRoomTypesCommand implements Command {
   private final static Logger LOGGER = LogManager.getLogger(ShowRoomTypesCommand.class);

    private TypeRoomService typeRoomService;

    public ShowRoomTypesCommand(TypeRoomService typeRoomService) {
        this.typeRoomService = typeRoomService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        Router router = new Router();
        List<TypeRoom> typeRoomList;
        int noPage = 1;
        if (request.getParameter(ParameterConstants.PAGE) != null) {
            noPage = Integer.parseInt(request.getParameter(ParameterConstants.PAGE));
        }

        try {
            typeRoomList = typeRoomService.getRooms(noPage);
            request.setAttribute(ParameterConstants.ROOM_TYPES, typeRoomList);
            request.setAttribute(ParameterConstants.NO_PAGES, typeRoomService.getNoOfPages());
            request.getSession().setAttribute(ParameterConstants.CURRENT_PAGE, noPage);
            page = PageConstants.ROOM_TYPES_PAGE;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.FORWARD);
        return router;
    }
}
