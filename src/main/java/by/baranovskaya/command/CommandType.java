package by.baranovskaya.command;

import by.baranovskaya.command.admin.*;
import by.baranovskaya.command.common.*;
import by.baranovskaya.command.user.*;
import by.baranovskaya.command.user.RegistrationCommand;
import by.baranovskaya.service.*;

public enum CommandType {
    LOGIN(new LogInCommand(new UserService())),
    LOGOUT(new LogOutCommand()),
    REGISTRATION(new RegistrationCommand(new UserService())),
    FORGOT_PASSWORD(new ForgotPasswordCommand(new UserService())),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    SHOW_USERS(new ShowUsersCommand(new AdminService())),
    DELETE_USER(new DeleteUserCommand(new AdminService())),
    EDIT_USER(new EditUserCommand(new UserService())),
    ADD_TYPE_ROOM(new AddTypeRoomCommand(new TypeRoomService())),
    DELETE_TYPE_ROOM(new DeleteTypeRoomCommand(new TypeRoomService())),
    EDIT_TYPE_ROOM(new EditTypeRoomCommand(new TypeRoomService())),
    SHOW_ROOM_TYPES(new ShowRoomTypesCommand(new TypeRoomService())),
    UPDATE_TYPE_ROOM(new UpdateTypeRoomCommand(new TypeRoomService())),
    SHOW_TYPE_ROOM(new ShowTypeRoomCommand(new TypeRoomService())),
    ADD_ROOM(new AddRoomCommand(new RoomService(), new TypeRoomService())),
    DELETE_ROOM(new DeleteRoomCommand(new RoomService())),
    EDIT_ROOMS(new EditRoomsCommand(new RoomService(), new TypeRoomService())),
    UPDATE_ROOM(new UpdateRoomCommand(new RoomService(), new TypeRoomService())),
    FIND_ROOMS(new FindRoomsCommand(new TypeRoomService())),
    /*GET_TYPES(new GetTypesCommand(new TypeRoomService())),*/
    ADD_SERVICE(new AddServiceCommand(new HotelService())),
    EDIT_SERVICE(new EditServiceCommand(new HotelService())),
    UPDATE_SERVICE(new UpdateServiceCommand(new HotelService())),
    LOAD_RESERVATION_PAGE(new LoadReservationPageCommand(new TypeRoomService())),
    SHOW_SERVICES(new ShowServicesCommand(new HotelService())),
    DELETE_SERVICE(new DeleteServiceCommand(new HotelService())),
    RESERVATION(new ReservationCommand(new OrderService())),
    RESERVATION_ROOM(new ReservationRoomCommand()),
    SHOW_APPLICATIONS(new ShowApplicationsCommand(new OrderService())),
    SHOW_EXECUTED_APPLICATIONS(new ShowExecutedApplicationsCommand(new OrderService())),
    CHANGE_PASSWORD(new ChangePasswordCommand(new UserService())),
    CHANGE_AVATAR(new ChangeAvatarCommand(new UserService())),
    CHANGE_ACCOUNT_INFORMATION(new ChangeAccountInfoCommand(new UserService())),
    SHOW_USER_ORDERS(new ShowUserOrdersCommand(new OrderService())),
    EDIT_ORDER(new EditOrderCommand(new RoomService(), new OrderService())),
    CALCULATE_PRICE(new CalculatePriceCommand(new TypeRoomService())),
    ISSUE_ORDER(new IssueOrderCommand(new OrderService())),
    REJECT_ORDER(new RejectOrderCommand(new OrderService())),
    LOAD_PAGE(new LoadPageCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
