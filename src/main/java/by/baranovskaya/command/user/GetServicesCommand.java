package by.baranovskaya.command.user;

import by.baranovskaya.command.Command;
import by.baranovskaya.command.admin.GetTypesCommand;
import by.baranovskaya.constant.PageConstant;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.HotelService;
import by.baranovskaya.service.RoomService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

public class GetServicesCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(GetTypesCommand.class);

    private HotelService hotelService;

    public GetServicesCommand(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Set<String> listServices = new HashSet<>();
        try {
            listServices = hotelService.getTypesService();
            request.setAttribute("services", listServices);
            page = PageConstant.PATH_PAGE_USER_RESERVATION;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
        }
        return page;
    }
}
