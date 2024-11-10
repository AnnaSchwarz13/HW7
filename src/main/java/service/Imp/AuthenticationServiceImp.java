package service.Imp;

import entities.User;
import entities.enums.Role;
import repository.Imp.UserRepositoryImp;

public class AuthenticationServiceImp {
    static UserRepositoryImp userRepositoryImp = new UserRepositoryImp();
    public static User loggedInUser;

    private AuthenticationServiceImp() {
    }

    public static void setLoggedUser(User user) {
        if (loggedInUser == null) {
            loggedInUser = user;
        }
    }

    public static void logout() {
        if (loggedInUser != null) {
            loggedInUser = null;
        }
    }

    public static boolean checkRole(Role role, User user) {
        return user.getRole().equals(role);
    }

    public static User getLoggedUser() {
        return loggedInUser;
    }

    public static boolean isUserNameNew(String username, Role role) {
        for (User checkingUser : userRepositoryImp.all()) {
            if (checkingUser.getUsername().equals(username)) {
                if (checkingUser.getRole().equals(role)) {
                    return false;
                }
            }
        }
        return true;
    }

}
