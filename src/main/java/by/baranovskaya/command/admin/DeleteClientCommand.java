package by.baranovskaya.command.admin;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstant;
import by.baranovskaya.entity.Client;
import by.baranovskaya.entity.Room;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.AdminService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteClientCommand implements Command{
    private final static Logger LOGGER = LogManager.getLogger(DeleteClientCommand.class);

    private final static String PARAM_ID = "id";
    private AdminService adminService;

    public DeleteClientCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        List<Client> clientList;

        try {
            if(adminService.deleteClient(Integer.parseInt(request.getParameter(PARAM_ID)))) {
                clientList = adminService.getAllClient();
                request.setAttribute("clients", clientList);
                page = PageConstant.PATH_PAGE_ADMIN_CLIENTS;
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
        }

        return page;
    }
}
