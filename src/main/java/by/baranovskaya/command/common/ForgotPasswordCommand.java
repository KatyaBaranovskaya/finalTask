package by.baranovskaya.command.common;

import by.baranovskaya.command.Command;
import by.baranovskaya.constant.MessageConstants;
import by.baranovskaya.constant.MessageProperty;
import by.baranovskaya.constant.PageConstants;
import by.baranovskaya.constant.ParameterConstants;
import by.baranovskaya.controller.Router;
import by.baranovskaya.entity.User;
import by.baranovskaya.exception.ServiceException;
import by.baranovskaya.sender.generator.PasswordGenerator;
import by.baranovskaya.sender.MailSender;
import by.baranovskaya.service.UserService;
import by.baranovskaya.validation.DataValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ForgotPasswordCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(ForgotPasswordCommand.class);

    private UserService userService;

    public ForgotPasswordCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        String page = null;
        User user;
        Router router = new Router();
        String login = request.getParameter(ParameterConstants.LOGIN);
        String email = request.getParameter(ParameterConstants.EMAIL);

        if (DataValidator.validateLoginEmail(login, email)) {
            try {
                user = userService.checkLoginEmailIsExist(login, email);
                if(user != null){
                    String pass = PasswordGenerator.generate();
                    userService.updatePassword(user.getIdUser(), pass);
                    MailSender.sendMail(ParameterConstants.TITLE_EMAIL_MESSAGE, ParameterConstants.TEXT_EMAIL_MESSAGE + pass, email);
                    page =  PageConstants.LOGIN_PAGE;
                    router.setRouteType(Router.RouteType.REDIRECT);
                } else{
                    setErrorMessage(request, MessageConstants.ERROR_EMAIL_LABEL, MessageProperty.ERROR_EMAIL_MESSAGE);
                    router.setRouteType(Router.RouteType.FORWARD);
                    page =  PageConstants.FORGOT_PASS_PAGE;
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e);
            }
        } else {
            setErrorMessage(request, MessageConstants.ERROR_EMAIL_LABEL, MessageProperty.ERROR_EMAIL_MESSAGE);
            router.setRouteType(Router.RouteType.FORWARD);
            page = PageConstants.FORGOT_PASS_PAGE;
        }

        router.setPagePath(page);
        return router;
    }
}