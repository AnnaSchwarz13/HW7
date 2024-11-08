package repository;

import entities.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
    //CRUD
    User create(User article) throws SQLException;
    User read(int id) throws SQLException;
    void delete(int id) throws SQLException;
   // public User update(User article) throws SQLException;
}

