package by.baranovskaya.command.admin;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstant;
import by.baranovskaya.entity.Room;
import by.baranovskaya.entity.Service;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.AdminService;
import by.baranovskaya.service.HotelService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteServiceCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(DeleteServiceCommand.class);

    private final static String PARAM_ID = "id";

    private HotelService hotelService;

    public DeleteServiceCommand(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        List<Service> serviceList;

        try {
            if(hotelService.deleteSevice(Integer.parseInt(request.getParameter(PARAM_ID)))) {
                serviceList = hotelService.getAllService();
                request.setAttribute("services", serviceList);
                page = PageConstant.PATH_PAGE_ADMIN_SERVICES;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
        }

        return page;
    }
}
