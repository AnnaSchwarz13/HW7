package repository;

import entities.User;

import java.sql.SQLException;

public interface UserRepository {
    //CRUD
    User create(User article) throws SQLException;
    User read(int id) throws SQLException;
    void delete(int id) throws SQLException;

}

