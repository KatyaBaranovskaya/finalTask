package by.baranovskaya.command.common;

import by.baranovskaya.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {
    private final static String PARAM_LOCALE = "locale";
    private final static String PARAM_URL = "url";

    @Override
    public String execute(HttpServletRequest request) {
        String locale = request.getParameter(PARAM_LOCALE);
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(PARAM_LOCALE, locale);

        return request.getParameter(PARAM_URL);
    }
}
