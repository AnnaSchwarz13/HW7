package repository;

import entities.Category;

import java.sql.SQLException;

public interface CategoryRepository {
    Category create(Category category) throws SQLException;
   // Category read(long id) throws SQLException;
    void delete(long id) throws SQLException;
}
