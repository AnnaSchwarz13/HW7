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
        if(user.getRole().equals(role)) {
            return true;
        }
        return false;
    }

    public static User getLoggedUser() {
        return DataBase.loggedInUser;
    }
    public static boolean isUserNameNew(String username) {
        for(int i = 0; i< DataBase.userList.getIndex(); i++) {
            User checkingAuthor =(User) DataBase.userList.getObjects(i);
            if (checkingAuthor.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

}
