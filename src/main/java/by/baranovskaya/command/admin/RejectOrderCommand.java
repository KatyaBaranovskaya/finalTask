package by.baranovskaya.command.admin;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstants;
import by.baranovskaya.constant.ParameterConstants;
import by.baranovskaya.controller.Router;
import by.baranovskaya.entity.Order;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.OrderService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class RejectOrderCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(IssueOrderCommand.class);

    private OrderService orderService;

    public RejectOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        Router router = new Router();
        List<Order> orderList;
        int idOrder = Integer.parseInt(request.getParameter(ParameterConstants.ID));

        try {
            if (orderService.updateStatus(idOrder)) {
                orderList = orderService.getNewOrders();
                request.getSession().setAttribute(ParameterConstants.ORDERS, orderList);
                page = PageConstants.APPLICATIONS_PAGE;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.REDIRECT);
        return router;
    }
}
