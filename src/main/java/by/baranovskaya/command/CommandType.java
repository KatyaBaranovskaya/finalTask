package by.baranovskaya.command;

import by.baranovskaya.command.user.RegistrationCommand;
import by.baranovskaya.command.common.ChangeLocaleCommand;
import by.baranovskaya.command.user.LoginCommand;
import by.baranovskaya.command.user.LogoutCommand;
import by.baranovskaya.service.UserService;

public enum CommandType {
    LOGIN(new LoginCommand(new UserService())),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand(new UserService())),
    CHANGE_LOCALE(new ChangeLocaleCommand());
    //LOGOUT(new LogoutCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
