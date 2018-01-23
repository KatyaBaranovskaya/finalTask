package by.baranovskaya.command.admin;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstant;
import by.baranovskaya.entity.Room;
import by.baranovskaya.entity.Service;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.HotelService;
import by.baranovskaya.service.RoomService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class EditServiceCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(EditServiceCommand.class);

    private final static String PARAM_ID = "id";

    private HotelService hotelService;

    public EditServiceCommand(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Service service = null;
        try {
            service = hotelService.findServiceById(Integer.parseInt(request.getParameter(PARAM_ID)));
            request.setAttribute("service", service);
            page = PageConstant.PATH_PAGE_ADMIN_EDIT_SERVICE;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
        }
        return page;
    }
}
