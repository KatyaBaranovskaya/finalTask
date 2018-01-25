package by.baranovskaya.command.admin;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstant;
import by.baranovskaya.entity.Order;
import by.baranovskaya.entity.Service;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.OrderService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PrintApplicationCommand implements Command{
    private final static Logger LOGGER = LogManager.getLogger(PrintApplicationCommand.class);

    private OrderService orderService;

    public PrintApplicationCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        /*List<Order> orderList;

        try {
            orderList = orderService.getAllService();
            request.setAttribute("services", orderList);
            page = PageConstant.PATH_PAGE_ADMIN_SERVICES;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
        }*/
        return page;
    }
}
