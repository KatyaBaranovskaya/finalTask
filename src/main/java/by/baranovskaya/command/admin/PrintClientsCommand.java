package by.baranovskaya.command.admin;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstant;
import by.baranovskaya.entity.Client;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.service.AdminService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PrintClientsCommand implements Command{
    private final static Logger LOGGER = LogManager.getLogger(PrintClientsCommand.class);

    private AdminService adminService;

    public PrintClientsCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        List<Client> clientList;

        try {
            clientList = adminService.getAllClient();
            request.setAttribute("clients", clientList);
            page = PageConstant.PATH_PAGE_ADMIN_CLIENTS;
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e);
        }
        return page;
    }
}
