package by.baranovskaya.command.admin;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.MessageConstants;
import by.baranovskaya.constant.MessageProperty;
import by.baranovskaya.constant.PageConstants;
import by.baranovskaya.constant.ParameterConstants;
import by.baranovskaya.controller.Router;
import by.baranovskaya.entity.Order;
import by.baranovskaya.service.TypeRoomService;
import by.baranovskaya.validation.OrderValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class CalculatePriceCommand implements Command {
    private TypeRoomService typeRoomService;

    public CalculatePriceCommand(TypeRoomService typeRoomService) {
        this.typeRoomService = typeRoomService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        Router router = new Router();
        Order order;

        order = initOrder(request);
        if (order != null) {
            double price;
            price = typeRoomService.calculatePrice(order);
            order.setPrice(price);
            request.getSession().setAttribute(ParameterConstants.ORDER, order);
            page = PageConstants.ISSUE_ORDER;
        } else {
            setErrorMessage(request, MessageConstants.ERROR_ORDER_LABEL, MessageProperty.ERROR_ORDER_MESSAGE);
            router.setRouteType(Router.RouteType.FORWARD);
            page = PageConstants.EDIT_ORDER_PAGE;
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.FORWARD);
        return router;
    }

    private Order initOrder(HttpServletRequest request) {
        Order order = (Order) request.getSession().getAttribute(ParameterConstants.ORDER);

        int roomNumber = Integer.parseInt(request.getParameter(ParameterConstants.ROOM_NUMBER));
        Date arrivalDate = Date.valueOf(request.getParameter(ParameterConstants.ARRIVAL_DATE));
        Date departureDate = Date.valueOf(request.getParameter(ParameterConstants.DEPARTURE_DATE));
        int noAdults = Integer.parseInt(request.getParameter(ParameterConstants.NO_ADULTS));
        int noChildren = Integer.parseInt(request.getParameter(ParameterConstants.NO_CHILDREN));
        String typeApartment = request.getParameter(ParameterConstants.TYPE_APARTMENT);
        String breakfast = request.getParameter(ParameterConstants.BREAKFAST);

        if (OrderValidator.validateOrder(arrivalDate, departureDate, noAdults, noChildren, typeApartment, breakfast)
                && OrderValidator.validateNumber(roomNumber)) {
            order.setRoomNumber(roomNumber);
            order.setArrivalDate(arrivalDate);
            order.setDepartureDate(departureDate);
            order.setNoAdults(Integer.parseInt(request.getParameter(ParameterConstants.NO_ADULTS)));
            order.setNoChildren(Integer.parseInt(request.getParameter(ParameterConstants.NO_CHILDREN)));
            order.setTypeApartment(request.getParameter(ParameterConstants.TYPE_APARTMENT));
            order.setBreakfast(request.getParameter(ParameterConstants.BREAKFAST));
        }

        return order;
    }
}
