package by.baranovskaya.command.admin;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstants;
import by.baranovskaya.constant.ParameterConstants;
import by.baranovskaya.controller.Router;
import by.baranovskaya.entity.Service;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.HotelService;
import by.baranovskaya.validation.ServiceValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddServiceCommand implements Command {
    //private final static Logger LOGGER = LogManager.getLogger(AddServiceCommand.class);
    private final static Logger LOGGER = LogManager.getLogger();

    private HotelService hotelService;

    public AddServiceCommand(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        Router router = new Router();
        Service service = new Service();
        service.setTypeService(request.getParameter(ParameterConstants.TYPE_SERVICE));
        service.setDescription(request.getParameter(ParameterConstants.DESCRIPTION));
        service.setImage(request.getParameter(ParameterConstants.IMAGE));

        if(ServiceValidator.validateService(service)){
            try {
                if(hotelService.addService(service)){
                    request.getSession().setAttribute("services", hotelService.getServices());
                    page = PageConstants.SERVICES_PAGE;
                } else{
                    //TODO err
                    page = PageConstants.ADD_SERVICE_PAGE;
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
            }
        } else {
            //TODO warn incorrect info
            page = PageConstants.ADD_SERVICE_PAGE;;
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.REDIRECT);
        return router;
    }
}
