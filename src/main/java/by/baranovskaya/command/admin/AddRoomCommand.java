package by.baranovskaya.command.admin;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstant;
import by.baranovskaya.entity.Room;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.RoomService;
import by.baranovskaya.validation.Validation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddRoomCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(AddRoomCommand.class);

    private final static String PARAM_NUMBER = "number";
    private final static String PARAM_TYPE = "type";
    private final static String PARAM_TYPE_OPTION = "typeOption";
    private final static String PARAM_CLASS_APARTMENT = "classApartment";
    private final static String PARAM_CAPACITY = "capacity";
    private final static String PARAM_PRICE = "price";
    private final static String PARAM_STATUS = "status";
    private final static String PARAM_DESCRIPTION = "description";
    private final static String PARAM_IMAGE = "image";

    private RoomService roomService;

    public AddRoomCommand(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Room room = new Room();
        room.setRoomNumber(Integer.parseInt(request.getParameter(PARAM_NUMBER)));
        if (request.getParameter(PARAM_TYPE).equals("")) {
            room.setTypeRoom(request.getParameter(PARAM_TYPE_OPTION));
        } else {
            room.setTypeRoom(request.getParameter(PARAM_TYPE));
        }
        room.setClassApartment(Integer.parseInt(request.getParameter(PARAM_CLASS_APARTMENT)));
        room.setCapacity(Integer.parseInt(request.getParameter(PARAM_CAPACITY)));
        room.setPrice(Double.parseDouble(request.getParameter(PARAM_PRICE)));
        room.setStatus(request.getParameter(PARAM_STATUS));
        room.setDescription(request.getParameter(PARAM_DESCRIPTION));
        room.setPicture(request.getParameter(PARAM_IMAGE));

        if(Validation.validateRoom(room)){
            try {
                if(roomService.addRoom(room)){
                    page = PageConstant.PATH_PAGE_ADMIN_ROOMS;
                } else{
                    //TODO err
                    page = PageConstant.PATH_PAGE_ADMIN_ADD_ROOM;
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
            }
        } else {
            //TODO warn incorrect info
            page = PageConstant.PATH_PAGE_ADMIN_ADD_ROOM;;
        }
        return page;
    }
}
