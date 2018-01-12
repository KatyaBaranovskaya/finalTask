package by.baranovskaya.command.user;

import by.baranovskaya.command.Command;
import by.baranovskaya.exception.CommandException;
import by.baranovskaya.exception.DAOException;
import by.baranovskaya.service.UserService;
import by.baranovskaya.validation.AuthenticationValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private final static String PARAM_LOGIN = "login";
    private final static String PARAM_PASSWORD = "password";
    private final static String PATH_PAGE_LOGIN = "/jsp/common/login.jsp";
    private final static String PATH_PAGE_USER_MAIN = "/jsp/user/userMain.jsp";
    private final static String PATH_PAGE_ADMIN_MAIN = "/jsp/admin/adminMain.jsp";
    private final static int  ADMIN_ROLE = 1;
    private final static int  USER_ROLE = 2;
    private final static int  NONE = 0;
    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        String loginValue = request.getParameter(PARAM_LOGIN);
        String passValue = request.getParameter(PARAM_PASSWORD);
        HttpSession session = request.getSession(true);

        if(AuthenticationValidation.validateLogin(loginValue, passValue)){
            try {
                switch (userService.checkUserIsExist(loginValue, passValue)) {
                    case ADMIN_ROLE:
                        session.setAttribute("role", "admin");
                        page = PATH_PAGE_ADMIN_MAIN;
                        break;
                    case USER_ROLE:
                        session.setAttribute("role", "user");
                        page = PATH_PAGE_USER_MAIN;
                        break;
                    case NONE:
                        //что нет такого пользователя
                        page = PATH_PAGE_LOGIN;
                        break;
                }
            } catch (DAOException e) {
                throw new CommandException(e); //что-то еще дописать!  и нормально ли блок стоит??????
            }
        } else {
            //что некорректная инфа
            page = PATH_PAGE_LOGIN;
        }
        return page;
    }
}
