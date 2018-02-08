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

public class EditTypeRoomCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(EditTypeRoomCommand.class);

    private TypeRoomService typeRoomService;

    public EditTypeRoomCommand(TypeRoomService typeRoomService) {
        this.typeRoomService = typeRoomService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        Router router = new Router();
        TypeRoom typeRoom;
        int idTypeRoom = Integer.parseInt(request.getParameter(ParameterConstants.ID));

        try {
            typeRoom = typeRoomService.findTypeRoom(idTypeRoom);
            request.getSession().setAttribute(ParameterConstants.TYPE_ROOM, typeRoom);
            page = PageConstants.EDIT_TYPE_ROOM_PAGE;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.FORWARD);
        return router;
    }
}
