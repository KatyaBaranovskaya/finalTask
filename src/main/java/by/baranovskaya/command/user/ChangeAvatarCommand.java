package by.baranovskaya.command.user;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.*;
import by.baranovskaya.controller.Router;
import by.baranovskaya.entity.User;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.UserService;
import by.baranovskaya.validation.DataValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeAvatarCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(ChangeAvatarCommand.class);

    private UserService userService;

    public ChangeAvatarCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        Router router = new Router();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(RoleType.USER);
        String avatar = request.getParameter(ParameterConstants.AVATAR);

        if (DataValidator.validateAvatar(avatar)) {
            try {
                if (userService.updateAvatar(user.getIdUser(), avatar)) {
                    user.setAvatar(avatar);
                    request.getSession().setAttribute(RoleType.USER, user);
                    page = PageConstants.USER_ACCOUNT_PAGE;
                    router.setRouteType(Router.RouteType.REDIRECT);
                } else {
                    setErrorMessage(request, MessageConstants.ERROR_CHANGE_AVATAR_LABEL, MessageProperty.ERROR_CHANGE_AVATAR_MESSAGE);
                    router.setRouteType(Router.RouteType.FORWARD);
                    page = PageConstants.USER_ACCOUNT_PAGE;
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
            }
        } else {
            setErrorMessage(request, MessageConstants.ERROR_CHANGE_AVATAR_LABEL, MessageProperty.ERROR_CHANGE_AVATAR_MESSAGE);
            router.setRouteType(Router.RouteType.FORWARD);
            page = PageConstants.USER_ACCOUNT_PAGE;
        }

        router.setPagePath(page);
        return router;
    }
}
