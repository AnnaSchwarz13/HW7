package entities;

import entities.enums.Role;

import java.util.Random;
import lombok.Getter;
import lombok.Setter;
import service.UserService;

@Getter
@Setter

public class User {
    Random rand = new Random();
    String username;
    String password;
    Role role;
    long id;
    UserService userService;

    public User(long id,String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.id = id;
    }

}