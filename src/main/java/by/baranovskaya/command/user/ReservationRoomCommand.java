package by.baranovskaya.command.user;

import by.baranovskaya.command.Command;
import by.baranovskaya.command.admin.GetTypesCommand;
import by.baranovskaya.constant.PageConstant;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.HotelService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class ReservationRoomCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(ReservationRoomCommand.class);
    private final static String PARAM_ID = "id";

    private HotelService hotelService;

    public ReservationRoomCommand(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Map<Integer, String> mapServices;
        HttpSession session = request.getSession(true);
        session.setAttribute("roomNumber", Integer.parseInt(request.getParameter(PARAM_ID)));
        try {
            mapServices = hotelService.getTypesService();
            request.setAttribute("services", mapServices);
            page = PageConstant.PATH_PAGE_USER_RESERVATION;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
        }
        return page;
    }
}
