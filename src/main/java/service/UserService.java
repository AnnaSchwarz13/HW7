package service;

import entities.User;
import entities.enums.Role;
import repository.Imp.UserRepositoryImp;

import java.sql.SQLException;

public class UserService {


    public void userLogin(String username, String password , Role role) throws SQLException {
        for(User checkingUser : UserRepositoryImp.all()){
            if (checkingUser.getUsername().equals(username)) {
                if (checkingUser.getPassword().equals(password)) {
                    if (AuthenticationService.checkRole(role, checkingUser)) {
                        AuthenticationService.setLoggedUser(UserRepositoryImp.findByUsername(username));
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

