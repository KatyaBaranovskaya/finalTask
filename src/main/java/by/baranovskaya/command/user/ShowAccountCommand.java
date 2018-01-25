package by.baranovskaya.command.user;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstant;
import by.baranovskaya.entity.Client;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ShowAccountCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(ShowAccountCommand.class);

    private UserService userService;

    public ShowAccountCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession(true);
        Client client = (Client) session.getAttribute("user");
        request.setAttribute("client", client);
        page = PageConstant.PATH_PAGE_USER_ACCOUNT;

        return page;
    }
}
