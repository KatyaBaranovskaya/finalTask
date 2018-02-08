package by.baranovskaya.command.common;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.ParameterConstants;
import by.baranovskaya.controller.Router;

import javax.servlet.http.HttpServletRequest;

public class ChangeLocaleCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();

        String locale = request.getParameter(ParameterConstants.LOCALE);
        String page = request.getParameter(ParameterConstants.URL);
        request.getSession().setAttribute(ParameterConstants.LOCALE, locale);

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.FORWARD);

        return router;
    }
}