package by.baranovskaya.command.user;

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

public class ChangePasswordCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(ChangePasswordCommand.class);

    private final static String PARAM_LAST_PASSWORD = "lastPassword";
    private final static String PARAM_NEW_PASSWORD = "newPassword";

    private UserService userService;

    public ChangePasswordCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession(true);
        Client client = (Client) session.getAttribute("client");
        String lastPassword = request.getParameter(PARAM_LAST_PASSWORD);
        String newPassword = request.getParameter(PARAM_NEW_PASSWORD);

        if (Validation.validatePassword(lastPassword, newPassword)) {
            try {
                if (userService.findClientById(client.getIdClient()).getPassword().equals(lastPassword)) {
                    userService.updatePassword(client.getIdClient(), newPassword);
                    page = PageConstant.PATH_PAGE_USER_ACCOUNT;
                } else {
                    //TODO err
                    page = PageConstant.PATH_PAGE_CHANGE_PASSWORD;
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
            }
        } else {
            //TODO warn incorrect info
            page = PageConstant.PATH_PAGE_CHANGE_PASSWORD;
        }
        return page;
    }
}
