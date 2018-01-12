package by.baranovskaya.command.user;

import by.baranovskaya.command.Command;
import by.baranovskaya.exception.DAOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    private static final String PATH_PAGE_MAIN = "/jsp/common/main.jsp";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession(true);
        session.setAttribute("role", null);
        page = PATH_PAGE_MAIN;
        return page;
    }
}
