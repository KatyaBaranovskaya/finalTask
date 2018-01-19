package by.baranovskaya.command;

import by.baranovskaya.command.admin.*;
import by.baranovskaya.command.user.RegistrationCommand;
import by.baranovskaya.command.common.ChangeLocaleCommand;
import by.baranovskaya.command.common.LoginCommand;
import by.baranovskaya.command.common.LogoutCommand;
import by.baranovskaya.service.AdminService;
import by.baranovskaya.service.HotelService;
import by.baranovskaya.service.UserService;

public enum CommandType {
    LOGIN(new LoginCommand(new UserService())),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand(new UserService())),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    PRINT_USER(new PrintClientCommand(new AdminService())),
    ADD_ROOM(new AddRoomCommand(new AdminService())),
    PRINT_ROOM(new PrintRoomCommand(new HotelService())),
    ADD_SERVICE(new AddServiceCommand(new AdminService())),
    PRINT_SERVICE(new PrintServiceCommand(new HotelService()));

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
