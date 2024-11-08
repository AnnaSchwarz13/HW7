package repository.Imp;
import entities.Category;
import repository.CategoryRepository;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRepositoryImp implements CategoryRepository {
    DataSource ds;
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
    @Override
    public Category create(Category category) throws SQLException {
        try (var statement = ds.getConnection().prepareStatement(INSERT_SQL)) {
            statement.setString(1, category.getTitle());
            statement.setString(2, category.getDescription());

            return category;
        }
    }

    @Override
    public Category read(int id) throws SQLException {
        try (var statement = ds.getConnection().prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

           Category category = null;
            if (resultSet.next()) {
                Double categoryId = resultSet.getDouble(1);
                String categoryTitle = resultSet.getString(2);
                String categoryDescription = resultSet.getString(3);

                category = new Category(categoryId, categoryTitle, categoryDescription);
            }

            return category;
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try (var statement = ds.getConnection().prepareStatement(DELETE_BY_ID_SQL)) {
            statement.setLong(1, id);
            var affectedRows = statement.executeUpdate();
            System.out.println("# of Contacts deleted: " + affectedRows);
        }
    }
}
