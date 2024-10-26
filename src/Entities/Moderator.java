package Entities;

import Entities.enums.Role;

public class Moderator extends User {

    public Moderator(String username, String password) {
        super(username, password, Role.MODERATOR);
    }
}
