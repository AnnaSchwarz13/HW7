package repository.Imp;

import entities.Author;
import entities.User;
import repository.AuthorRepository;
import repository.Datasource;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRepositoryImp implements AuthorRepository {
    UserRepositoryImp userRepositoryImp = new UserRepositoryImp();

    private static final String INSERT_SQL = """
            INSERT INTO Authors(firstname, lastname, birthday , national_code, user_id)
                    VALUES (?, ?, ? ,? ,?)
            """;

    private static final String DELETE_BY_ID_SQL = """
            DELETE FROM Authors
            WHERE id = ?
            """;

    private static final String FIND_BY_ID_SQL = """
            SELECT * FROM Authors
            WHERE id = ?
            """;
    private static final String FIND_AUTHOR_BY_USERID_SQL = """
            SELECT * FROM Authors
            WHERE user_id = ?
            """;
    private static final String UPDATE_PASSWORD_SQL = """
            UPDATE Users
            SET password = ?
            WHERE username = ?
            """;

    @Override
    public Author create(Author author) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(INSERT_SQL)) {
            statement.setString(1, author.getFirstName());
            statement.setString(2, author.getLastName());
            statement.setDate(3, (Date) author.getBirthDate());
            statement.setString(4, author.getNationalCode());
            long userId = userRepositoryImp.findByUsername(author.getUsername()).getId();
            statement.setLong(5, userId);

            statement.executeUpdate();

            return author;
        }
    }

    public static Author read(long id) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            Author author = null;
            if (resultSet.next()) {
                long authorId = resultSet.getLong(1);
                String authorFirstname = resultSet.getString(2);
                String authorLastname = resultSet.getString(3);
                Date bithdate = resultSet.getDate(4);
                String nationalCode = resultSet.getString(5);
                int userId = resultSet.getInt(6);
                User user = UserRepositoryImp.read(userId);
                author = new Author(authorFirstname, authorLastname, user.getUsername()
                        , user.getPassword(), nationalCode, bithdate);
                author.setId(authorId);
            }

            return author;
        }
    }

    @Override
    public Author findByUserId(long userId) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(FIND_AUTHOR_BY_USERID_SQL)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            Author author = null;
            if (resultSet.next()) {
                author = read(resultSet.getLong(1));
            }
            return author;
        }
    }

    @Override
    public void delete(long id) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(DELETE_BY_ID_SQL)) {
            statement.setLong(1, id);
            var affectedRows = statement.executeUpdate();
            System.out.println("# of Contacts deleted: " + affectedRows);
        }
    }

    //update
    @Override
    public void setUpdatePassword(Author author, String password) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(UPDATE_PASSWORD_SQL)) {
            statement.setString(1, password);
            statement.setString(2, author.getUsername());
            statement.executeUpdate();
        }
    }
}
