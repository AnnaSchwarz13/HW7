package service;

import entities.User;
import entities.enums.Role;
import repository.Imp.UserRepositoryImp;

import java.sql.SQLException;

public class UserService {
UserRepositoryImp userRepositoryImp = new UserRepositoryImp();

    public void userLogin(String username, String password , Role role) throws SQLException {
        for(User checkingUser : userRepositoryImp.all()){
            if (checkingUser.getUsername().equals(username)) {
                if (checkingUser.getPassword().equals(password)) {
                    if (AuthenticationService.checkRole(role, checkingUser)) {
                        AuthenticationService.setLoggedUser(userRepositoryImp.findByUsername(username));
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

