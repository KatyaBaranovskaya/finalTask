package by.baranovskaya.command.user;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.*;
import by.baranovskaya.controller.Router;
import by.baranovskaya.entity.Order;
import by.baranovskaya.entity.User;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.OrderService;
import by.baranovskaya.validation.OrderValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class ReservationCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(ReservationCommand.class);

    private OrderService orderService;

    public ReservationCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        Router router = new Router();
        Order order;

        order = initOrder(request);
        if (order != null) {
            try {
                if (orderService.doOrder(order)) {
                    request.getSession().setAttribute("typeApartment", null);
                    router.setRouteType(Router.RouteType.REDIRECT);
                    page = PageConstants.MAIN_PAGE;
                } else {
                    setErrorMessage(request, MessageConstants.ERROR_ORDER_LABEL, MessageProperty.ERROR_ORDER_MESSAGE);
                    router.setRouteType(Router.RouteType.FORWARD);
                    page = PageConstants.RESERVATION_PAGE;
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
            }
        } else {
            setErrorMessage(request, MessageConstants.ERROR_ORDER_LABEL, MessageProperty.ERROR_ORDER_MESSAGE);
            router.setRouteType(Router.RouteType.FORWARD);
            page = PageConstants.RESERVATION_PAGE;
        }

        router.setPagePath(page);

        return router;
    }

    private Order initOrder(HttpServletRequest request) {
        Order order = new Order();
        Date arrivalDate = Date.valueOf(request.getParameter(ParameterConstants.ARRIVAL_DATE));
        Date departureDate = Date.valueOf(request.getParameter(ParameterConstants.DEPARTURE_DATE));
        int noAdults = Integer.parseInt(request.getParameter(ParameterConstants.NO_ADULTS));
        int noChildren = Integer.parseInt(request.getParameter(ParameterConstants.NO_CHILDREN));
        String typeApartment = request.getParameter(ParameterConstants.TYPE_APARTMENT);
        String breakfast = request.getParameter(ParameterConstants.BREAKFAST);

        System.out.println(typeApartment);
        if (OrderValidator.validateOrder(arrivalDate, departureDate, noAdults, noChildren, typeApartment, breakfast)) {
            User user = (User) request.getSession().getAttribute(RoleType.USER);
            order.setUser(user);
            order.setArrivalDate(arrivalDate);
            order.setDepartureDate(departureDate);
            order.setNoAdults(Integer.parseInt(request.getParameter(ParameterConstants.NO_ADULTS)));
            order.setNoChildren(Integer.parseInt(request.getParameter(ParameterConstants.NO_CHILDREN)));
            order.setTypeApartment(request.getParameter(ParameterConstants.TYPE_APARTMENT));
            order.setBreakfast(request.getParameter(ParameterConstants.BREAKFAST));
        }

        System.out.println(order);
        return order;
    }
}
