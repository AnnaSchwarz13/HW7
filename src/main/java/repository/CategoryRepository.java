package repository;

import entities.Author;
import entities.Category;

import java.sql.SQLException;

public interface CategoryRepository {
    public Category create(Category category) throws SQLException;
    public Category read(int id) throws SQLException;
    public void delete(int id) throws SQLException;
}
