package repository;

import entities.Category;

import java.sql.SQLException;

public interface CategoryRepository {
    Category create(Category category) throws SQLException;
    Category read(int id) throws SQLException;
    void delete(int id) throws SQLException;
}
