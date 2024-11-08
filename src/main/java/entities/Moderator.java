package entities;

import entities.enums.Role;
import service.UserService;

public class Moderator extends User {

    public Moderator(String username, String password) {
        super(UserService.loggedInUser.getId() ,username, password, Role.MODERATOR);
    }
}
