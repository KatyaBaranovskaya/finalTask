package by.baranovskaya.command.user;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstants;
import by.baranovskaya.constant.ParameterConstants;
import by.baranovskaya.constant.RoleType;
import by.baranovskaya.controller.Router;
import by.baranovskaya.entity.Order;
import by.baranovskaya.entity.User;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.OrderService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowUserOrdersCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(ShowUserOrdersCommand.class);

    private OrderService orderService;

    public ShowUserOrdersCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        Router router = new Router();
        int idUser;
        List<Order> orderList;

        if (request.getParameter(ParameterConstants.ID).equals("")) {
            User user = (User) request.getSession().getAttribute(RoleType.USER);
            idUser = user.getIdUser();
        } else {
            idUser = Integer.parseInt(request.getParameter(ParameterConstants.ID));
        }

        try {
            orderList = orderService.getUserOrders(idUser);
            request.setAttribute(ParameterConstants.ORDERS, orderList);
            page = PageConstants.ORDERS_PAGE;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.FORWARD);
        return router;
    }
}
