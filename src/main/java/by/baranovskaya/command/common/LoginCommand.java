package by.baranovskaya.command.common;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstant;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.UserService;
import by.baranovskaya.validation.AuthenticationValidation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private final static String PARAM_LOGIN = "login";
    private final static String PARAM_PASSWORD = "password";
    private final static int  ADMIN_ROLE = 1;
    private final static int  USER_ROLE = 2;
    private final static int  NONE = 0;
    private UserService userService;

    private final static Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String loginValue = request.getParameter(PARAM_LOGIN);
        String passValue = request.getParameter(PARAM_PASSWORD);
        HttpSession session = request.getSession(true);

        if(AuthenticationValidation.validateLogin(loginValue, passValue)){
            try {
                switch (userService.checkUserIsExist(loginValue, passValue)) {
                    case ADMIN_ROLE:
                        session.setAttribute("role", "admin");
                        page = PageConstant.PATH_PAGE_ADMIN_MAIN;
                        break;
                    case USER_ROLE:
                        session.setAttribute("role", "user");
                        page = PageConstant.PATH_PAGE_USER_MAIN;
                        break;
                    case NONE:
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
