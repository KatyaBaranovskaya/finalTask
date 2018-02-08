package by.baranovskaya.controller;

import by.baranovskaya.command.ActionFactory;
import by.baranovskaya.command.Command;
import by.baranovskaya.command.common.EmptyCommand;
import by.baranovskaya.constant.PageConstants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<Command> commandOptional = ActionFactory.defineCommand(request.getParameter("command"));
        Command command = commandOptional.orElse(new EmptyCommand());
        Router router = command.execute(request);
        if (router != null) {
            String page = router.getPagePath();
            switch (router.getRouteType()){
                case FORWARD:{
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
                    requestDispatcher.forward(request, response);
                    break;
                }
                case REDIRECT:{
                    response.sendRedirect(request.getContextPath() + page);
                    break;
                }
                default:{
                    response.sendRedirect(PageConstants.ERROR_PAGE);
                }
            }
        } else {
            request.getSession().invalidate();
            response.sendRedirect(PageConstants.MAIN_PAGE);
        }
    }
}


