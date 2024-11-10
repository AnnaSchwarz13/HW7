package repository;

import entities.Author;

import java.sql.SQLException;

public interface AuthorRepository {
    Author create(Author author) throws SQLException;

    // Author read(int id) throws SQLException;

    void delete(long id) throws SQLException;

    void setUpdatePassword(Author author, String password) throws SQLException;

    Author findByUserId(long userId) throws SQLException;
}
