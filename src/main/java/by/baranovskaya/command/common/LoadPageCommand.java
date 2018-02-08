package by.baranovskaya.command.common;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.ParameterConstants;
import by.baranovskaya.controller.Router;

import javax.servlet.http.HttpServletRequest;

public class LoadPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setPagePath(request.getParameter(ParameterConstants.PAGE));
        router.setRouteType(Router.RouteType.FORWARD);
        return router;
    }
}