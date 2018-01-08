package by.baranovskaya.command;

import by.baranovskaya.exception.DAOException;
import by.baranovskaya.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private static final String PATH_PAGE_LOGIN = "/jsp/login.jsp";
    private static final String PATH_PAGE_MAIN = "/jsp/main.jsp";
    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) throws DAOException {
        String page = null;
        String loginValue = request.getParameter(PARAM_LOGIN);
        String passValue = request.getParameter(PARAM_PASSWORD);

        if(loginValue != null && !loginValue.isEmpty() && passValue != null && !passValue.isEmpty()){
            if(userService.checkUserIsExist(loginValue, passValue)){
                request.setAttribute("user", loginValue);// ?????
                page = PATH_PAGE_MAIN;
            } else{
                //request.setAttribute("");
                page = PATH_PAGE_LOGIN;
            }
        } else {
            //request.setAttribute("");
            page = PATH_PAGE_LOGIN;
        }
        return page;
    }
}
