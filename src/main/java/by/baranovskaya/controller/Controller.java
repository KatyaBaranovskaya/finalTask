package by.baranovskaya.controller;

import by.baranovskaya.command.ActionFactory;
import by.baranovskaya.command.Command;
import by.baranovskaya.command.EmptyCommand;
import by.baranovskaya.exception.DAOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class Controller extends HttpServlet {
    private final static String INDEX_PAGE = "/index.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<Command> commandOptional = ActionFactory.defineCommand(request.getParameter("command"));
        Command command = commandOptional.orElse(new EmptyCommand());
        String page = null;

        try {
            page = command.execute(request);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        if(page != null){
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            //request.getSession().setAttribute("nullPage", MessageManager.getMessage("message.nullPage"));
            //response.sendRedirect(request.getContextPath() + "/index.jsp");
            response.sendRedirect(INDEX_PAGE);
        }
    }
}
