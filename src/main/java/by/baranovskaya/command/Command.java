package by.baranovskaya.command;

import by.baranovskaya.exception.DAOException;
import by.baranovskaya.exception.DBException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest request) throws DAOException;
}
