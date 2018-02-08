package by.baranovskaya.command.admin;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstants;
import by.baranovskaya.constant.ParameterConstants;
import by.baranovskaya.controller.Router;
import by.baranovskaya.entity.Service;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.HotelService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteServiceCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(DeleteServiceCommand.class);

    private HotelService hotelService;

    public DeleteServiceCommand(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        Router router = new Router();
        List<Service> serviceList;
        int idService = Integer.parseInt(request.getParameter(ParameterConstants.ID));

        try {
            if(hotelService.deleteService(idService)) {
                serviceList = hotelService.getServices();
                request.setAttribute(ParameterConstants.SERVICES, serviceList);
                page = PageConstants.SERVICES_PAGE;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.FORWARD);

        return router;
    }
}
