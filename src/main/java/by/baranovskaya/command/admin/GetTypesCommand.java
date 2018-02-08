/*
package by.baranovskaya.command.admin;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstant;
import by.baranovskaya.controller.Router;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.TypeRoomService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public class GetTypesCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(GetTypesCommand.class);

    private TypeRoomService typeRoomService;

    public GetTypesCommand(TypeRoomService typeRoomService) {
        this.typeRoomService = typeRoomService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        Router router = new Router();
        Set<String> typesList;

        try {
            typesList = typeRoomService.getTypes();
            request.setAttribute("types", typesList);
            page = PageConstant.EDIT_ROOM_PAGE;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.FORWARD);
        return router;
    }
}
*/
