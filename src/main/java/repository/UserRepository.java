package repository;

import entities.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
    //CRUD
    User create(User article) throws SQLException;
    void delete(long id) throws SQLException;
    User findByUsername(String username) throws SQLException;
    List<User> all();

}

