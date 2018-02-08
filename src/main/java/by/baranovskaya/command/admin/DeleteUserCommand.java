package by.baranovskaya.command.admin;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstants;
import by.baranovskaya.constant.ParameterConstants;
import by.baranovskaya.controller.Router;
import by.baranovskaya.entity.User;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteUserCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(DeleteUserCommand.class);

    private UserService userService;

    public DeleteUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        Router router = new Router();
        List<User> userList;
        int idUser = Integer.parseInt(request.getParameter(ParameterConstants.ID));

        try {
            if (userService.deleteUser(idUser)) {
                userList = userService.getAllUsers();
                request.setAttribute(ParameterConstants.USERS, userList);
                page = PageConstants.CLIENTS_PAGE;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
        }

        router.setPagePath(page);
        router.setRouteType(Router.RouteType.FORWARD);
        return router;
    }
}
