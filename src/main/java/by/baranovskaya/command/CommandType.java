package by.baranovskaya.command;

import by.baranovskaya.command.admin.*;
import by.baranovskaya.command.user.RegistrationCommand;
import by.baranovskaya.command.common.ChangeLocaleCommand;
import by.baranovskaya.command.common.LoginCommand;
import by.baranovskaya.command.common.LogoutCommand;
import by.baranovskaya.service.AdminService;
import by.baranovskaya.service.HotelService;
import by.baranovskaya.service.RoomService;
import by.baranovskaya.service.UserService;

public enum CommandType {
    LOGIN(new LoginCommand(new UserService())),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand(new UserService())),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    PRINT_USER(new PrintClientCommand(new AdminService())),
    DELETE_CLIENT(new DeleteClientCommand(new AdminService())),
    ADD_TYPE_ROOM(new AddRoomCommand(new RoomService())),
    PRINT_ROOM(new PrintRoomCommand(new RoomService())),
    DELETE_ROOM(new DeleteRoomCommand(new RoomService())),
    ADD_SERVICE(new AddServiceCommand(new HotelService())),
    PRINT_SERVICE(new PrintServiceCommand(new HotelService())),
    DELETE_SERVICE(new DeleteServiceCommand(new HotelService()));

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
