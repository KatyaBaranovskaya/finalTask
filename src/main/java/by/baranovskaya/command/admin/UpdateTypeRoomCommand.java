package by.baranovskaya.command.admin;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstants;
import by.baranovskaya.constant.ParameterConstants;
import by.baranovskaya.controller.Router;
import by.baranovskaya.entity.TypeRoom;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.TypeRoomService;
import by.baranovskaya.validation.TypeRoomValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UpdateTypeRoomCommand implements Command {
    //private final static Logger LOGGER = LogManager.getLogger(UpdateTypeRoomCommand.class);
    private final static Logger LOGGER = LogManager.getLogger();


    private TypeRoomService typeRoomService;

    public UpdateTypeRoomCommand(TypeRoomService typeRoomService) {
        this.typeRoomService = typeRoomService;
    }


    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        Router router = new Router();
        TypeRoom typeRoom = new TypeRoom();
        typeRoom.setIdType(Integer.parseInt(request.getParameter(ParameterConstants.ID_TYPE_ROOM)));
        typeRoom.setTypeRoom(request.getParameter(ParameterConstants.TYPE_ROOM));
        typeRoom.setCapacity(Integer.parseInt(request.getParameter(ParameterConstants.CAPACITY)));
        typeRoom.setPrice(Double.parseDouble(request.getParameter(ParameterConstants.PRICE)));
        typeRoom.setDescription(request.getParameter(ParameterConstants.DESCRIPTION));
        if(request.getParameter(ParameterConstants.IMAGE).equals("")){
            typeRoom.setImage(request.getParameter(ParameterConstants.OLD_IMAGE));
        } else {
            typeRoom.setImage(request.getParameter(ParameterConstants.IMAGE));
        }

        if(TypeRoomValidator.validateTypeRoom(typeRoom)){
            try {
                if(typeRoomService.updateTypeRoom(typeRoom)){
                    request.getSession().setAttribute("roomTypes", typeRoomService.getTypesRoom());
                    page = PageConstants.SERVICES_PAGE;
                } else{
                    //TODO err
                    page = PageConstants.EDIT_TYPE_ROOM_PAGE;
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
            }
        } else {
            //TODO warn incorrect info
            page = PageConstants.EDIT_TYPE_ROOM_PAGE;;
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.REDIRECT);
        return router;
    }
}
