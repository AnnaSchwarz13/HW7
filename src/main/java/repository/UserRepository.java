package repository;

import entities.User;

import java.sql.SQLException;

public interface UserRepository {
    //CRUD
    User create(User article) throws SQLException;
    User read(long id) throws SQLException;
    void delete(long id) throws SQLException;

}

