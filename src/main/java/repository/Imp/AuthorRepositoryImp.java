package repository.Imp;

import entities.Author;
import entities.User;
import repository.AuthorRepository;
import repository.Datasource;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRepositoryImp implements AuthorRepository {

    UserRepositoryImp userRepo = new UserRepositoryImp();
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
    private static final String UPDATE_SQL = """
            UPDATE Authors
            SET ? = ?
            where id = ?
            """;

    @Override
    public Author create(Author author) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(INSERT_SQL)) {
            statement.setString(1, author.getFirstName());
            statement.setString(2, author.getLastName());
            statement.setDate(3, (Date) author.getBirthDate());
            statement.setString(4, author.getNationalCode());
            long userId = UserRepositoryImp.findByUsername(author.getUsername());
            statement.setLong(5, userId);

            statement.executeUpdate();

            return author;
        }
    }

    @Override
    public Author read(int id) throws SQLException {
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
                User user = userRepo.read(userId);
                author = new Author(authorFirstname, authorLastname, user.getUsername()
                        , user.getPassword(), nationalCode, bithdate);
            }

            return author;
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

}
