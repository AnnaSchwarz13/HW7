package repository;

import entities.Author;

import java.sql.SQLException;

public interface AuthorRepository {
     Author create(Author author) throws SQLException;
    // Author read(int id) throws SQLException;
     void delete(long id) throws SQLException;
}
