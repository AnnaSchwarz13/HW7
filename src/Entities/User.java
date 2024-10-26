package Entities;

import Entities.enums.Role;

import java.util.Random;

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
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Role getRole() {
        return role;
    }
}
