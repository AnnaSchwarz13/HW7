package service.Imp;

import entities.User;
import entities.enums.Role;
import repository.Imp.UserRepositoryImp;
import service.UserService;

import java.sql.SQLException;

public class UserServiceImp implements UserService {
    UserRepositoryImp userRepositoryImp = new UserRepositoryImp();
    AuthenticationServiceImp authenticationServiceImp = new AuthenticationServiceImp();

    @Override
    public void userLogin(String username, String password, Role role) throws SQLException {
        for (User checkingUser : userRepositoryImp.all()) {
            if (checkingUser.getUsername().equals(username)) {
                if (checkingUser.getPassword().equals(password)) {
                    if (authenticationServiceImp.checkRole(role, checkingUser)) {
                        authenticationServiceImp.setLoggedUser(userRepositoryImp.findByUsername(username));
                        System.out.println("User logged in successfully...");
                        return;
                    }
                }
                break;
            }
        }
        System.out.println("Username or password is wrong!");
    }
@Override
    public void userLogout() {
        authenticationServiceImp.logout();
    }
}

