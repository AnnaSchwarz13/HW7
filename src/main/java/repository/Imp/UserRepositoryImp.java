package repository.Imp;
import entities.User;
import entities.enums.Role;
import repository.UserRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.String.valueOf;

public class UserRepositoryImp implements UserRepository {
    DataSource ds;
    private static final String INSERT_SQL =
            "INSERT INTO Users(username, password, user_role) VALUES (?, ?, ?)";

    private static final String DELETE_BY_ID_SQL = """
            DELETE FROM Users
            WHERE id = ?
            """;

    private static final String FIND_BY_ID_SQL = """
            SELECT * FROM Users
            WHERE id = ?
            """;
    private static final String UPDATE_SQL = """
            UPDATE Users
            SET ? = ?
            where id = ?
            """;
    @Override
        public User create(User user) throws SQLException {
        var statement = ds.getConnection().prepareStatement(INSERT_SQL);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setString(3,valueOf(user.getRole()));

        statement.execute();
        statement.close();
        return user;
    }
    @Override
    public User read(int id) throws SQLException {
        try (var statement = ds.getConnection().prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            User user = null;
            if (resultSet.next()) {
                Double userId = resultSet.getDouble(1);
                String username = resultSet.getString(2);
                String password = resultSet.getString(3);
                String role = resultSet.getString(4);
                user = new User(userId,username,password, Role.valueOf(role));
            }

            return user;
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
