package by.baranovskaya.command;

import by.baranovskaya.service.UserService;

public enum CommandType {
    LOGIN(new LoginCommand(new UserService())),
    REGISTRATION(new RegistrationCommand(new UserService()));
    //LOGOUT(new LogoutCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
