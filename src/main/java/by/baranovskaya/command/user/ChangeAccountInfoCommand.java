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
import java.sql.Date;

public class ChangeAccountInfoCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(ChangeAccountInfoCommand.class);

    private UserService userService;

    public ChangeAccountInfoCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        Router router = new Router();
        User user;

        user = initUser(request);
        if (user != null) {
            try {
                if (userService.updateUserInfo(user)) {
                    page = PageConstants.USER_ACCOUNT_PAGE;
                    router.setRouteType(Router.RouteType.REDIRECT);
                } else {
                    setErrorMessage(request, MessageConstants.ERROR_USER_INFO_LABEL, MessageProperty.ERROR_USER_INFO_MESSAGE);
                    router.setRouteType(Router.RouteType.FORWARD);
                    page = PageConstants.CHANGE_ACCOUNT_INFO_PAGE;
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
            }
        } else {
            setErrorMessage(request, MessageConstants.ERROR_USER_INFO_LABEL, MessageProperty.ERROR_USER_INFO_MESSAGE);
            router.setRouteType(Router.RouteType.FORWARD);
            page = PageConstants.CHANGE_ACCOUNT_INFO_PAGE;
        }

        router.setPagePath(page);
        return router;
    }

    private User initUser(HttpServletRequest request) {
        String surname = request.getParameter(ParameterConstants.SURNAME);
        String name = request.getParameter(ParameterConstants.NAME);
        String middleName = request.getParameter(ParameterConstants.MIDDLE_NAME);
        Date dateBirth = Date.valueOf(request.getParameter(ParameterConstants.DATE_BIRTH));
        String telephone = request.getParameter(ParameterConstants.PHONE);
        int idUser = Integer.parseInt(request.getParameter(ParameterConstants.ID));

        if (DataValidator.validateUserInfo(surname, name, middleName, dateBirth, telephone)) {
            User user = (User) request.getSession().getAttribute(RoleType.USER);
            if (request.getParameter(ParameterConstants.ID).equals("")) {
                User newUser = new User();
                newUser.setIdUser(idUser);
                newUser.setSurname(surname);
                newUser.setName(name);
                newUser.setMiddleName(middleName);
                newUser.setDateBirth(dateBirth);
                newUser.setTelephone(telephone);
                return newUser;
            } else {
                user.setSurname(surname);
                user.setName(name);
                user.setMiddleName(middleName);
                user.setDateBirth(dateBirth);
                user.setTelephone(telephone);
                request.getSession().setAttribute(RoleType.USER, user);
                return user;
            }
        } else {
            return null;
        }
    }
}
