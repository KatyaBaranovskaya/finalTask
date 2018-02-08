package by.baranovskaya.command.admin;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.MessageConstants;
import by.baranovskaya.constant.MessageProperty;
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

public class UpdateServiceCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(UpdateServiceCommand.class);

    private HotelService hotelService;

    public UpdateServiceCommand(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        Router router = new Router();
        Service service;

        service = initService(request);
        if (service != null) {
            try {
                if (hotelService.updateService(service)) {
                    request.getSession().setAttribute(ParameterConstants.SERVICES, hotelService.getServices());
                    router.setRouteType(Router.RouteType.REDIRECT);
                    page = PageConstants.SERVICES_PAGE;
                } else {
                    page = PageConstants.ADD_SERVICE_PAGE;
                    router.setRouteType(Router.RouteType.FORWARD);
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
            }
        } else {
            setErrorMessage(request, MessageConstants.ERROR_SERVICE_LABEL, MessageProperty.ERROR_SERVICE_MESSAGE);
            router.setRouteType(Router.RouteType.FORWARD);
            page = PageConstants.ADD_SERVICE_PAGE;;
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.REDIRECT);
        return router;
    }

    private Service initService(HttpServletRequest request) {
        Service service = new Service();
        int idService = Integer.parseInt(request.getParameter(ParameterConstants.ID_SERVICE));
        String typeService = request.getParameter(ParameterConstants.TYPE_SERVICE);
        String description = request.getParameter(ParameterConstants.DESCRIPTION);
        String image;
        if (request.getParameter(ParameterConstants.IMAGE).equals("")) {
            image = request.getParameter(ParameterConstants.OLD_IMAGE);
        } else {
            image = request.getParameter(ParameterConstants.IMAGE);
        }

        if (ServiceValidator.validateService(typeService, description, image)) {
            service.setIdService(idService);
            service.setTypeService(typeService);
            service.setDescription(description);
            service.setImage(image);
        }

        return service;
    }
}
