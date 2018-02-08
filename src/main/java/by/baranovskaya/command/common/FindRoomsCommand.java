package by.baranovskaya.command.common;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.MessageConstants;
import by.baranovskaya.constant.MessageProperty;
import by.baranovskaya.constant.PageConstants;
import by.baranovskaya.constant.ParameterConstants;
import by.baranovskaya.controller.Router;
import by.baranovskaya.entity.TypeRoom;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.TypeRoomService;
import by.baranovskaya.validation.DataValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindRoomsCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger();

    private TypeRoomService typeRoomService;

    public FindRoomsCommand(TypeRoomService typeRoomService) {
        this.typeRoomService = typeRoomService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        List<TypeRoom> typeRoomList;
        int minPrice = Integer.parseInt(request.getParameter(ParameterConstants.MIN));
        int maxPrice = Integer.parseInt(request.getParameter(ParameterConstants.MAX));

        if (DataValidator.validateSearchParam(minPrice, maxPrice)) {
            try {
                typeRoomList = typeRoomService.findRoomTypes(minPrice, maxPrice);
                request.setAttribute(ParameterConstants.ROOM_TYPES, typeRoomList);
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
            }
        } else {
            setErrorMessage(request, MessageConstants.ERROR_PRICE_LABEL, MessageProperty.ERROR_PRICE_MESSAGE);
        }

        router.setPagePath(PageConstants.ROOM_TYPES_PAGE);
        router.setRouteType(Router.RouteType.FORWARD);
        return router;
    }
}
