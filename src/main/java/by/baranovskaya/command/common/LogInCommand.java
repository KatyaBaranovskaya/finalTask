package by.baranovskaya.command.common;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.*;
import by.baranovskaya.controller.Router;
import by.baranovskaya.entity.User;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.manager.MessageManager;
import by.baranovskaya.service.UserService;
import by.baranovskaya.validation.UserValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogInCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(LogInCommand.class);

    private UserService userService;

    public LogInCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        Router router = new Router();
        String loginValue = request.getParameter(ParameterConstants.LOGIN);
        String passValue = request.getParameter(ParameterConstants.PASSWORD);
        User user;

        if (UserValidator.validateLoginPassword(loginValue, passValue)) {
            try {
                user = userService.checkUserIsExist(loginValue, passValue);
                if (user != null) {
                    page = setRole(request, user);
                } else {
                    setErrorMessage(request, MessageConstants.ERROR_LOGIN_LABEL, MessageProperty.ERROR_LOGIN_MESSAGE);
                    page = PageConstants.LOGIN_PAGE;
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
            }
        } else {
            setErrorMessage(request, MessageConstants.ERROR_LOGIN_LABEL, MessageProperty.ERROR_LOGIN_MESSAGE);
            page = PageConstants.LOGIN_PAGE;
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.FORWARD);
        return router;
    }

    private String setRole(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        switch (user.getRole()) {
            case RoleType.ADMIN_ROLE:
                session.setAttribute(ParameterConstants.ROLE, RoleType.ADMIN);
                session.setAttribute(RoleType.ADMIN, user);
                break;
            case RoleType.USER_ROLE:
                session.setAttribute(ParameterConstants.ROLE, RoleType.USER);
                session.setAttribute(RoleType.USER, user);
                break;
        }

        return PageConstants.MAIN_PAGE;
    }
}
