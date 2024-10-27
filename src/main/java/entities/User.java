package entities;

import entities.enums.Role;

import java.util.Random;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class User {
    Random rand = new Random();
    String username;
    String password;
    Role role;
    double id;

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
        id = rand.nextDouble();
    }
}