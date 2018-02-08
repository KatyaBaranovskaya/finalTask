package by.baranovskaya.command.admin;

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

public class UpdateTypeRoomCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(UpdateTypeRoomCommand.class);

    private TypeRoomService typeRoomService;

    public UpdateTypeRoomCommand(TypeRoomService typeRoomService) {
        this.typeRoomService = typeRoomService;
    }


    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        Router router = new Router();
        TypeRoom typeRoom;

        typeRoom = initTypeRoom(request);
        if (typeRoom != null) {
            try {
                if (typeRoomService.updateTypeRoom(typeRoom)) {
                    int currentPage = (Integer) request.getSession().getAttribute(ParameterConstants.CURRENT_PAGE);
                    request.getSession().setAttribute(ParameterConstants.ROOM_TYPES, typeRoomService.getRooms(currentPage));
                    page = PageConstants.ROOM_TYPES_PAGE;
                    router.setRouteType(Router.RouteType.REDIRECT);
                } else {
                    page = PageConstants.EDIT_TYPE_ROOM_PAGE;
                    router.setRouteType(Router.RouteType.FORWARD);
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
            }
        } else {
            setErrorMessage(request, MessageConstants.ERROR_TYPE_ROOM_LABEL, MessageProperty.ERROR_TYPE_ROOM_MESSAGE);
            router.setRouteType(Router.RouteType.FORWARD);
            page = PageConstants.EDIT_TYPE_ROOM_PAGE;
        }

        router.setPagePath(page);
        return router;
    }

    private TypeRoom initTypeRoom(HttpServletRequest request) {
        TypeRoom typeRoom = new TypeRoom();
        int idType = Integer.parseInt(request.getParameter(ParameterConstants.ID_TYPE_ROOM));
        String typeApartment = request.getParameter(ParameterConstants.TYPE_ROOM);
        int capacity = Integer.parseInt(request.getParameter(ParameterConstants.CAPACITY));
        double price = Double.parseDouble(request.getParameter(ParameterConstants.PRICE));
        String description = request.getParameter(ParameterConstants.DESCRIPTION);
        String image;
        if (request.getParameter(ParameterConstants.IMAGE).equals("")) {
            image = request.getParameter(ParameterConstants.OLD_IMAGE);
        } else {
            image = request.getParameter(ParameterConstants.IMAGE);
        }

        if (DataValidator.validateTypeRoom(typeApartment, capacity, price, description, image)) {
            typeRoom.setIdType(idType);
            typeRoom.setTypeRoom(typeApartment);
            typeRoom.setCapacity(capacity);
            typeRoom.setPrice(price);
            typeRoom.setDescription(description);
            typeRoom.setImage(image);
        }

        return typeRoom;
    }
}
