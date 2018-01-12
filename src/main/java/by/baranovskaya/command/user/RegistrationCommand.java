package by.baranovskaya.command.user;

import by.baranovskaya.command.Command;
import by.baranovskaya.entity.Client;
import by.baranovskaya.exception.CommandException;
import by.baranovskaya.exception.DAOException;
import by.baranovskaya.service.UserService;
import by.baranovskaya.validation.AuthenticationValidation;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class RegistrationCommand implements Command {
    private static final String PARAM_EMAIL = "mail";
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_SURNAME = "surname";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_MIDDLE_NAME = "middleName";
    private static final String PARAM_DATE_BIRTH = "date";
    private static final String PARAM_PHONE = "phone";
    private static final String PATH_PAGE_REGISTER = "/jsp/common/registration.jsp";
    private static final String PATH_PAGE_MAIN = "/jsp/common/main.jsp";
    private UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
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

        // проверка есть ли в базе такой?!!!!!!!!!!!!!!
        if(AuthenticationValidation.validateRegistration(client)){
            try {

                if(userService.registerUser(client)){
                    page = PATH_PAGE_MAIN;
                } else{
                    //request.setAttribute("er");
                    page = PATH_PAGE_REGISTER;
                }
            } catch (DAOException e) {
                throw new CommandException(e);
            }
        } else {
            //request.setAttribute("");
            page = PATH_PAGE_REGISTER;
        }
        return page;
    }
}
