package by.baranovskaya.command;

import by.baranovskaya.command.admin.*;
import by.baranovskaya.command.user.GetServicesCommand;
import by.baranovskaya.command.user.RegistrationCommand;
import by.baranovskaya.command.common.ChangeLocaleCommand;
import by.baranovskaya.command.common.LoginCommand;
import by.baranovskaya.command.common.LogoutCommand;
import by.baranovskaya.command.user.ReservationCommand;
import by.baranovskaya.service.*;

public enum CommandType {
    LOGIN(new LoginCommand(new UserService())),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand(new UserService())),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    PRINT_USER(new PrintClientsCommand(new AdminService())),
    DELETE_CLIENT(new DeleteClientCommand(new AdminService())),
    ADD_ROOM(new AddRoomCommand(new RoomService())),
    EDIT_ROOM(new EditRoomCommand(new RoomService())),
    UPDATE_ROOM(new UpdateRoomCommand(new RoomService())),
    GET_TYPES(new GetTypesCommand(new RoomService())),
    PRINT_ROOM(new PrintRoomsCommand(new RoomService())),
    DELETE_ROOM(new DeleteRoomCommand(new RoomService())),
    ADD_SERVICE(new AddServiceCommand(new HotelService())),
    EDIT_SERVICE(new EditServiceCommand(new HotelService())),
    UPDATE_SERVICE(new UpdateServiceCommand(new HotelService())),
    GET_SERVICES(new GetServicesCommand(new HotelService())),
    PRINT_SERVICE(new PrintServicesCommand(new HotelService())),
    DELETE_SERVICE(new DeleteServiceCommand(new HotelService())),
    RESERVATION(new ReservationCommand(new OrderService()));

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
