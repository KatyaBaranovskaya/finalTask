package by.baranovskaya.command.common;

import by.baranovskaya.command.Command;
import by.baranovskaya.controller.Router;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return null;
    }
}
