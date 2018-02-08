package by.baranovskaya.command;

import by.baranovskaya.constant.ParameterConstants;
import by.baranovskaya.controller.Router;
import by.baranovskaya.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest request);

    default void setErrorMessage(HttpServletRequest request, String errorLabel, String errorMessage) {
        String locale = (String) request.getSession().getAttribute(ParameterConstants.LOCALE);
        if (locale == null) {
            locale = ParameterConstants.DEFAULT_LOCALE;
        }
        request.setAttribute(errorLabel, MessageManager.getLocale(locale).getProperty(errorMessage));
    }
}
