package repository.Imp;

import entities.Category;
import repository.CategoryRepository;
import repository.Datasource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CategoryRepositoryImp implements CategoryRepository {

    private static final String INSERT_SQL =
            "INSERT INTO Categories(title, description) VALUES (?, ?)";

    private static final String DELETE_BY_ID_SQL = """
            DELETE FROM Categories
            WHERE id = ?
            """;

    private static final String FIND_BY_ID_SQL = """
            SELECT * FROM Categories
            WHERE id = ?
            """;
    private static final String FIND_COUNT_SQL = """
            SELECT COUNT(*) FROM Categories
            """;
    public static final String READ_ALL_SQL = """
            SELECT * FROM Categories
            """;

    public static final String FIND_BY_TITLE_SQL = """
            SELECT * FROM Categories
            WHERE title = ?
            """;


    @Override
    public Category create(Category category) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(INSERT_SQL)) {
            statement.setString(1, category.getTitle());
            statement.setString(2, category.getDescription());
            statement.executeUpdate();
            return category;
        }
    }

    @Override
    public Category read(int id) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            Category category = null;
            if (resultSet.next()) {
                long categoryId = resultSet.getLong(1);
                String categoryTitle = resultSet.getString(2);
                String categoryDescription = resultSet.getString(3);

                category = new Category(categoryId, categoryTitle, categoryDescription);
            }

            return category;
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(DELETE_BY_ID_SQL)) {
            statement.setLong(1, id);
            var affectedRows = statement.executeUpdate();
            System.out.println("# of Contacts deleted: " + affectedRows);
        }
    }

    public static int findCount() throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(FIND_COUNT_SQL)) {
            ResultSet resultSet = statement.executeQuery();
            int categoryIndex=0;
            if (resultSet.next()) {
                categoryIndex = resultSet.getInt(1);
            }
            return categoryIndex;
        }
    }
    static public List<Category> all() {
        try (var statement = Datasource.getConnection().prepareStatement(READ_ALL_SQL)) {
            ResultSet resultSet = statement.executeQuery();
            List<Category> categories = new LinkedList<>();
            while (resultSet.next()) {
                long categoryId = resultSet.getLong(1);
                String title = resultSet.getString(2);
                String description = resultSet.getString(3);
                Category category = new Category(categoryId, title, description);
                categories.add(category);
            }

            return new ArrayList<>(categories);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Category findCategoryByTile(String title) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(FIND_BY_TITLE_SQL)) {
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();

            Category category = null;
            if (resultSet.next()) {
                long categoryId = resultSet.getLong(1);
                String categoryTitle = resultSet.getString(2);
                String categoryDescription = resultSet.getString(3);

                category = new Category(categoryId, categoryTitle, categoryDescription);
            }

            return category;
        }
    }


}