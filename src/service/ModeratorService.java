package service;

import Entities.Moderator;
import database.DataBase;

public class ModeratorService {
//todo:make a mother class userService
    public void moderatorLogin(String username, String password) {
        for(int i = 0; i< DataBase.moderatorList.getIndex(); i++){
            Moderator checkingUser =DataBase.moderatorList.getModerators(i);
            if (checkingUser.getUsername().equals(username)) {
                if (checkingUser.getPassword().equals(password)) {
                    AuthenticationService.setLoggedModerator(checkingUser);
                    System.out.println("User logged in successfully...");
                    return;
                }
                break;
            }
        }
        System.out.println("Username or password is wrong!");
    }

    public void moderatorLogout() {
        DataBase.moderator =null;
    }
}
