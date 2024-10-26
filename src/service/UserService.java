package service;

import database.DataBase;
import entities.User;
import entities.enums.Role;

public class UserService {

    public void userLogin(String username, String password , Role role) {
        for(int i = 0; i< DataBase.userList.getIndex(); i++){
            User checkingUser =(User) DataBase.userList.getObjects(i);
            if (checkingUser.getUsername().equals(username)) {
                if (checkingUser.getPassword().equals(password)) {
                    if (AuthenticationService.checkRole(role, checkingUser)) {
                        AuthenticationService.setLoggedUser(checkingUser);
                        System.out.println("User logged in successfully...");
                        return;
                    }
                }
                break;
            }
        }
        System.out.println("Username or password is wrong!");
    }

    public void userLogout() {
     AuthenticationService.logout();
    }
}

