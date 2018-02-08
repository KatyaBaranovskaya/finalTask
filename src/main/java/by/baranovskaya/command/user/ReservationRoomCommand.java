package by.baranovskaya.command.user;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstants;
import by.baranovskaya.constant.ParameterConstants;
import by.baranovskaya.controller.Router;
import by.baranovskaya.service.HotelService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class ReservationRoomCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        request.getSession().setAttribute(ParameterConstants.TYPE_APARTMENT, request.getParameter(ParameterConstants.ID));
        router.setPagePath(PageConstants.RESERVATION_PAGE);
        router.setRouteType(Router.RouteType.FORWARD);
        return router;
    }
}
