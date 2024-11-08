package entities;

import entities.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class User {
    String username;
    String password;
    Role role;
    long id;

    public User(long id,String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.id = id;
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}