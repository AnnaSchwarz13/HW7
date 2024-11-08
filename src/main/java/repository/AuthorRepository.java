package repository;

import entities.Author;

import java.sql.SQLException;

public interface AuthorRepository {
    public Author create(Author author) throws SQLException;
    public Author read(int id) throws SQLException;
    public void delete(int id) throws SQLException;
}
