package by.baranovskaya.command.user;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.*;
import by.baranovskaya.controller.Router;
import by.baranovskaya.entity.User;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.UserService;
import by.baranovskaya.validation.UserValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangePasswordCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(ChangePasswordCommand.class);

    private UserService userService;

    public ChangePasswordCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        Router router = new Router();
        User user = (User) request.getSession().getAttribute(RoleType.USER);
        String lastPassword = request.getParameter(ParameterConstants.LAST_PASSWORD);
        String newPassword = request.getParameter(ParameterConstants.NEW_PASSWORD);

        if (UserValidator.validatePassword(lastPassword, newPassword)) {
            try {
                if (userService.findUserById(user.getIdUser()).getPassword().equals(lastPassword)) {
                    userService.updatePassword(user.getIdUser(), newPassword);
                    page = PageConstants.USER_ACCOUNT_PAGE;
                    router.setRouteType(Router.RouteType.REDIRECT);
                } else {
                    setErrorMessage(request, MessageConstants.ERROR_CHANGE_PASS_LABEL, MessageProperty.ERROR_CHANGE_PASS_MESSAGE);
                    page = PageConstants.CHANGE_PASSWORD_PAGE;
                    router.setRouteType(Router.RouteType.FORWARD);
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
            }
        } else {
            setErrorMessage(request, MessageConstants.ERROR_CHANGE_PASS_LABEL, MessageProperty.ERROR_CHANGE_PASS_MESSAGE);
            page = PageConstants.CHANGE_PASSWORD_PAGE;
            router.setRouteType(Router.RouteType.FORWARD);
        }

        router.setPagePath(page);
        return router;
    }
}
