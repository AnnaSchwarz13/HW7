package service;

import entities.enums.Role;

import java.sql.SQLException;

public interface UserService {
    void userLogin(String username, String password, Role role) throws SQLException;
    void userLogout();
}
