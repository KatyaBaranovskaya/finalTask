package by.baranovskaya.command.user;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstant;
import by.baranovskaya.entity.Client;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.UserService;
import by.baranovskaya.validation.AuthenticationValidation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private UserService userService;

    private final static Logger LOGGER = LogManager.getLogger(RegistrationCommand.class);

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
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

        if(AuthenticationValidation.validateRegistration(client)){
            try {
                if(userService.registerUser(client)){
                    page = PageConstant.PATH_PAGE_MAIN;
                } else{
                    //TODO user is exist
                    page = PageConstant.PATH_PAGE_REGISTER;
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
            }
        } else {
            //TODO warn incorrect info
            page = PageConstant.PATH_PAGE_REGISTER;;
        }
        return page;
    }
}
