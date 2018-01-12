package by.baranovskaya.validation;

import by.baranovskaya.entity.Client;

public class AuthenticationValidation {
    public static boolean validateLogin(String loginValue, String passValue){
         return (loginValue != null && !loginValue.isEmpty() && passValue != null && !passValue.isEmpty());
    }

    public static boolean validateRegistration(Client client){
        return (!client.getSurname().isEmpty() && !client.getName().isEmpty() && !client.getMiddleName().isEmpty() &&
                !client.getEmail().isEmpty() && !client.getLogin().isEmpty() && !client.getPassword().isEmpty()
                && client.getDateBirth() != null && !client.getTelephone().isEmpty());
    }
}
