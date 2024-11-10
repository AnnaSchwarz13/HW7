package repository;

import entities.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryRepository {
    Category create(Category category) throws SQLException;

    // Category read(long id) throws SQLException;
    void delete(long id) throws SQLException;

    long findCount() throws SQLException;

    List<Category> all();

    Category findCategoryByTile(String title) throws SQLException;
}
