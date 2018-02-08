package by.baranovskaya.command.user;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.MessageConstants;
import by.baranovskaya.constant.MessageProperty;
import by.baranovskaya.constant.PageConstants;
import by.baranovskaya.constant.ParameterConstants;
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

public class RegistrationCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(RegistrationCommand.class);

    private UserService userService;

    public RegistrationCommand(UserService userService) {
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
                if (!userService.checkLoginIsExist(user.getLogin())) {
                    userService.registerUser(user);
                    router.setRouteType(Router.RouteType.REDIRECT);
                    page = PageConstants.MAIN_PAGE;
                } else {
                    setErrorMessage(request, MessageConstants.ERROR_REGISTRATION_LABEL, MessageProperty.ERROR_USER_IS_EXIST_MESSAGE);
                    router.setRouteType(Router.RouteType.FORWARD);
                    page = PageConstants.REGISTRATION_PAGE;
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
            }
        } else {
            setErrorMessage(request, MessageConstants.ERROR_REGISTRATION_LABEL, MessageProperty.ERROR_REGISTRATION_MESSAGE);
            router.setRouteType(Router.RouteType.FORWARD);
            page = PageConstants.REGISTRATION_PAGE;
        }

        router.setPagePath(page);

        return router;
    }

    private User initUser(HttpServletRequest request) {
        User user = new User();
        String email = request.getParameter(ParameterConstants.EMAIL);
        String login = request.getParameter(ParameterConstants.LOGIN);
        String password = request.getParameter(ParameterConstants.PASSWORD);
        String surname = request.getParameter(ParameterConstants.SURNAME);
        String name = request.getParameter(ParameterConstants.NAME);
        String middleName = request.getParameter(ParameterConstants.MIDDLE_NAME);
        Date dateBirth = Date.valueOf(request.getParameter(ParameterConstants.DATE_BIRTH));
        String telephone = request.getParameter(ParameterConstants.PHONE);

        if (DataValidator.validateRegistration(email, login, password, surname, name, middleName, dateBirth, telephone)) {
            user.setEmail(email);
            user.setLogin(login);
            user.setPassword(password);
            user.setSurname(surname);
            user.setName(name);
            user.setMiddleName(middleName);
            user.setDateBirth(dateBirth);
            user.setTelephone(telephone);
        }

        return user;
    }
}
