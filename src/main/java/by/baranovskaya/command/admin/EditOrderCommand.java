package by.baranovskaya.command.admin;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstants;
import by.baranovskaya.constant.ParameterConstants;
import by.baranovskaya.controller.Router;
import by.baranovskaya.entity.Order;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.OrderService;
import by.baranovskaya.service.RoomService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class EditOrderCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(EditOrderCommand.class);

    private RoomService roomService;
    private OrderService orderService;

    public EditOrderCommand(RoomService roomService, OrderService orderService) {
        this.roomService = roomService;
        this.orderService = orderService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        Router router = new Router();
        Order order;
        List<Integer> numbersList;
        int idOrder = Integer.parseInt(request.getParameter(ParameterConstants.ID));

        try {
            order = orderService.getOrder(idOrder);
            numbersList = roomService.getFreeRoomNumbers();
            request.setAttribute(ParameterConstants.NUMBERS, numbersList);
            request.getSession().setAttribute(ParameterConstants.ORDER, order);
            page = PageConstants.EDIT_ORDER_PAGE;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.FORWARD);
        return router;
    }
}
