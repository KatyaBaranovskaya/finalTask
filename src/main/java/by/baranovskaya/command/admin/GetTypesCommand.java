package by.baranovskaya.command.admin;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstant;
import by.baranovskaya.entity.Client;
import by.baranovskaya.entity.Room;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.RoomService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public class GetTypesCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(GetTypesCommand.class);

    private RoomService roomService;

    public GetTypesCommand(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Set<String> listTypes;

        try {
            listTypes = roomService.getTypesRoom();
            request.setAttribute("types", listTypes);
            page = PageConstant.PATH_PAGE_ADMIN_ADD_ROOM;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
        }
        return page;
    }
}
