package by.baranovskaya.command;

import by.baranovskaya.entity.Client;
import by.baranovskaya.exception.DAOException;
import by.baranovskaya.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class RegistrationCommand implements Command{
    private static final String PARAM_EMAIL = "mail";
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_SURNAME = "surname";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_MIDDLE_NAME = "middleName";
    private static final String PARAM_DATE_BIRTH = "date";
    private static final String PARAM_PHONE = "phone";
    private static final String PATH_PAGE_LOGIN = "/jsp/registration.jsp";
    private static final String PATH_PAGE_MAIN = "/jsp/main.jsp";
    private UserService userService;

    public RegistrationCommand(UserService clientService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) throws DAOException {
        String page = null;
        Client client = new Client();
        client.setEmail(request.getParameter(PARAM_EMAIL));
        client.setLogin(request.getParameter(PARAM_LOGIN));
        client.setPassword(request.getParameter(PARAM_PASSWORD));
        client.setSurname(request.getParameter(PARAM_SURNAME));
        client.setName(request.getParameter(PARAM_NAME));
        client.setMiddleName(request.getParameter(PARAM_MIDDLE_NAME));
        client.setDateBirth(Date.valueOf(request.getParameter(PARAM_DATE_BIRTH)));
        client.setTelephone(request.getParameter(PARAM_PHONE));

        if(client != null){
            if(userService.registerUser(client)){
                //request.setAttribute("user", loginValue);// ?????
                page = PATH_PAGE_MAIN;
            } else{
                //request.setAttribute("er");
                page = PATH_PAGE_LOGIN;
            }
        } else {
            //request.setAttribute("");
            page = PATH_PAGE_LOGIN;
        }
        return page;
    }
}
