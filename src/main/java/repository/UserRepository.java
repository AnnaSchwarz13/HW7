package repository;

import entities.User;

import java.sql.SQLException;

public interface UserRepository {
    //CRUD
    User create(User article) throws SQLException;
    void delete(long id) throws SQLException;

}

