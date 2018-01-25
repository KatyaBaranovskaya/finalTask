package by.baranovskaya.command.common;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstant;
import by.baranovskaya.entity.Client;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.UserService;
import by.baranovskaya.validation.Validation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    private final static String PARAM_LOGIN = "login";
    private final static String PARAM_PASSWORD = "password";
    private final static String ADMIN_ROLE = "Администратор";
    private final static String USER_ROLE = "Пользователь";
    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String loginValue = request.getParameter(PARAM_LOGIN);
        String passValue = request.getParameter(PARAM_PASSWORD);
        HttpSession session = request.getSession(true);
        Client client = null;

        if(Validation.validateLogin(loginValue, passValue)){
            try {
                client = userService.checkUserIsExist(loginValue, passValue);
                switch (client.getRole()) {
                    case ADMIN_ROLE:
                        session.setAttribute("role", "admin");
                        session.setAttribute("admin", client);
                        page = PageConstant.PATH_PAGE_ADMIN_MAIN;
                        break;
                    case USER_ROLE:
                        session.setAttribute("role", "user");
                        session.setAttribute("client", client);
                        page = PageConstant.PATH_PAGE_USER_MAIN;
                        break;
                    default:
                        //TODO user doesn't exist
                        page = PageConstant.PATH_PAGE_LOGIN;
                        break;
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
            }
        } else {
            //TODO warn incorrect info
            page = PageConstant.PATH_PAGE_LOGIN;
        }
        return page;
    }
}
