package service.Imp;

import entities.User;
import entities.enums.Role;
import repository.Imp.UserRepositoryImp;

public class AuthenticationServiceImp {
    static UserRepositoryImp userRepositoryImp = new UserRepositoryImp();
    private static User loggedInUser;

    public void setLoggedUser(User user) {
        if (loggedInUser == null) {
            loggedInUser = user;
        }
    }

    public void logout() {
        if (loggedInUser != null) {
            loggedInUser = null;
        }
    }

    public boolean checkRole(Role role, User user) {
        return user.getRole().equals(role);
    }

    public User getLoggedUser() {
        return loggedInUser;
    }

    public boolean isUserNameNew(String username, Role role) {
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
