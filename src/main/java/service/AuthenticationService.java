package service;

import entities.User;
import entities.enums.Role;
import repository.Imp.UserRepositoryImp;

public class AuthenticationService {

    private AuthenticationService() {
    }

    public static void setLoggedUser(User user) {
        if(UserService.loggedInUser == null) {
           UserService.loggedInUser = user;
        }
    }
    public static void logout() {
        if (UserService.loggedInUser != null) {
            UserService.loggedInUser = null;
        }
    }

    public static boolean checkRole(Role role , User user) {
        return user.getRole().equals(role);
    }

    public static User getLoggedUser() {
        return UserService.loggedInUser;
    }
    public static boolean isUserNameNew(String username , Role role) {
        for(User checkingUser : UserRepositoryImp.all()) {
            if (checkingUser.getUsername().equals(username)) {
                if(checkingUser.getRole().equals(role)) {
                    return false;
                }
            }
        }
        return true;
    }

}
