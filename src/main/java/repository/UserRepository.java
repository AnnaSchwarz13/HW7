package repository;

import entities.User;

import java.sql.SQLException;

public interface UserRepository {
    //CRUD
    public User create(User article) throws SQLException;
    public User read(int id) throws SQLException;
    public void delete(int id) throws SQLException;
   // public User update(User article) throws SQLException;
}

