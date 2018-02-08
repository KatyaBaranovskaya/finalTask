package by.baranovskaya.command.admin;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstants;
import by.baranovskaya.constant.ParameterConstants;
import by.baranovskaya.controller.Router;
import by.baranovskaya.entity.Order;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.sender.MailSender;
import by.baranovskaya.service.OrderService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class IssueOrderCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(IssueOrderCommand.class);

    private OrderService orderService;

    public IssueOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        Router router = new Router();
        Order order = (Order) request.getSession().getAttribute(ParameterConstants.ORDER);
        order.setStatus(ParameterConstants.ISSUE);

        try {
            if (orderService.issueOrder(order)) {
                MailSender.sendMail(ParameterConstants.TITLE_EMAIL_MESSAGE, ParameterConstants.TEXT_EMAIL_ORDER_MESSAGE, order.getUser().getEmail());
                page = PageConstants.MAIN_PAGE;
            } else {
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
