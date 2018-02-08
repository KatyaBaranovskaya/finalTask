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

public class UpdateServiceCommand implements Command{
   // private final static Logger LOGGER = LogManager.getLogger(UpdateServiceCommand.class);
   private final static Logger LOGGER = LogManager.getLogger();


    private HotelService hotelService;

    public UpdateServiceCommand(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        Router router = new Router();
        Service service = new Service();
        service.setIdService(Integer.parseInt(request.getParameter(ParameterConstants.ID_SERVICE)));
        service.setTypeService(request.getParameter(ParameterConstants.TYPE_SERVICE));
        service.setDescription(request.getParameter(ParameterConstants.DESCRIPTION));
        if(request.getParameter(ParameterConstants.IMAGE).equals("")){
            service.setImage(request.getParameter(ParameterConstants.OLD_IMAGE));
        } else {
            service.setImage(request.getParameter(ParameterConstants.IMAGE));
        }

        if(ServiceValidator.validateService(service)){
            try {
                if(hotelService.updateService(service)){
                    request.getSession().setAttribute("services", hotelService.getServices());
                    page = PageConstants.SERVICES_PAGE;
                } else{
                    //TODO err
                    page = PageConstants.EDIT_SERVICE_PAGE;
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
            }
        } else {
            //TODO warn incorrect info
            page = PageConstants.EDIT_SERVICE_PAGE;
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.REDIRECT);
        return router;
    }
}
