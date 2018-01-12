package by.baranovskaya.command.common;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.PageConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession(true);
        session.setAttribute("role", null);
        page = PageConstant.PATH_PAGE_MAIN;
        return page;
    }
}
