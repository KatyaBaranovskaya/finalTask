package by.baranovskaya.validation;

import by.baranovskaya.entity.Client;
import by.baranovskaya.entity.Room;
import by.baranovskaya.entity.Service;

public class Validation {
    public static boolean validateLogin(String loginValue, String passValue) {
         return (loginValue != null && !loginValue.isEmpty() && passValue != null && !passValue.isEmpty());
    }

    public static boolean validateRegistration(Client client) {
        return (!client.getSurname().isEmpty() && !client.getName().isEmpty() && !client.getMiddleName().isEmpty() &&
                !client.getEmail().isEmpty() && !client.getLogin().isEmpty() && !client.getPassword().isEmpty()
                && client.getDateBirth() != null && !client.getTelephone().isEmpty());
    }

    public static boolean validateRoom(Room room) {
        return (room.getRoomNumber() != 0 && !room.getStatus().isEmpty() && !room.getTypeRoom().isEmpty() &&
                room.getCapacity() != 0 && room.getPrice() != 0 && !room.getDescription().isEmpty());
    }

    public static boolean validateService(Service service) {
        return (!service.getTypeService().isEmpty() &&  service.getPrice() != 0);
    }
}
