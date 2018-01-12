package by.baranovskaya.command.common;

import by.baranovskaya.command.Command;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}