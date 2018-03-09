package by.baranovskaya.controller.filter;

import by.baranovskaya.constant.PageConstants;
import by.baranovskaya.constant.ParameterConstants;
import by.baranovskaya.constant.RoleType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter( urlPatterns = { "/*" },initParams = { @WebInitParam(name = "INDEX_PATH", value = PageConstants.INDEX_PAGE) })
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        String type = (String) session.getAttribute(ParameterConstants.ROLE);
        if (type == null) {
            session.setAttribute(ParameterConstants.ROLE, RoleType.GUEST);
            RequestDispatcher dispatcher = request.getRequestDispatcher(PageConstants.INDEX_PAGE);
            dispatcher.forward(request, response);
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() { }
}
