package by.baranovskaya.command.user;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstants;
import by.baranovskaya.constant.ParameterConstants;
import by.baranovskaya.controller.Router;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.HotelService;
import by.baranovskaya.service.TypeRoomService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public class LoadReservationPageCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(LoadReservationPageCommand.class);

    private TypeRoomService typeRoomService;

    public LoadReservationPageCommand(TypeRoomService typeRoomService) {
        this.typeRoomService = typeRoomService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        Router router = new Router();
        Set<String> typesList;

        try {
            typesList = typeRoomService.getTypes();
            request.setAttribute(ParameterConstants.TYPES, typesList);
            page = PageConstants.RESERVATION_PAGE;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.FORWARD);
        return router;
    }
}
