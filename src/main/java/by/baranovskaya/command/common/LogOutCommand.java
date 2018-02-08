package by.baranovskaya.command.common;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstants;
import by.baranovskaya.controller.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession httpSession = request.getSession(false);

        if (httpSession != null){
            httpSession.invalidate();
        }

        router.setPagePath(PageConstants.INDEX_PAGE);
        router.setRouteType(Router.RouteType.REDIRECT);

        return router;
    }
}
