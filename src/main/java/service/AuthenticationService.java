package service;

import entities.User;
import database.DataBase;
import entities.enums.Role;

public class AuthenticationService {
    public AuthenticationService() {
    }

    public static void setLoggedUser(User user) {
        if(DataBase.loggedInUser == null) {
            DataBase.loggedInUser = user;
        }
    }
    public static void logout() {
        if (DataBase.loggedInUser != null) {
            DataBase.loggedInUser = null;
        }
    }

    public static boolean checkRole(Role role , User user) {
        return user.getRole().equals(role);
    }

    public static User getLoggedUser() {
        return DataBase.loggedInUser;
    }
    public static boolean isUserNameNew(String username , Role role) {
        for(int i = 0; i< DataBase.userList.getIndex(); i++) {
            User checkingUser =(User) DataBase.userList.getObjects(i);
            if (checkingUser.getUsername().equals(username)) {
                if(checkingUser.getRole().equals(role)) {
                    return false;
                }
            }
        }
        return true;
    }

}
