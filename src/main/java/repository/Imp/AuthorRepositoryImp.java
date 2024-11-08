package repository.Imp;

import database.DataBase;
import entities.Author;
import entities.User;
import repository.AuthorRepository;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRepositoryImp implements AuthorRepository {
    DataSource ds;
    UserRepositoryImp userRepo;
    private static final String INSERT_SQL = """
            INSERT INTO Authors(firstname, lastname, birthday , national_cod, user_id)
                    VALUES (?, ?, ,? ,? ,?)
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
        try (var statement = ds.getConnection().prepareStatement(INSERT_SQL)) {
            statement.setString(1, author.getFirstName());
            statement.setString(2, author.getLastName());
            statement.setDate(3, (Date) author.getBirthDate());
            statement.setString(4, author.getNationalCode());
            statement.setDouble(5, DataBase.loggedInUser.getId());

            return author;
        }
    }
    @Override
    public Author read(int id) throws SQLException {
        try (var statement = ds.getConnection().prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            Author author = null;
            if (resultSet.next()) {
                Double authorId = resultSet.getDouble(1);
                String authorFirstname = resultSet.getString(2);
                String authorLastname = resultSet.getString(3);
                Date bithdate = resultSet.getDate(4);
                String nationalCode = resultSet.getString(5);
                int userId = resultSet.getInt(6);
                User user = userRepo.read(userId) ;
                author = new Author(authorFirstname,authorLastname,user.getUsername()
                                    ,user.getPassword(),nationalCode,bithdate);
            }

            return author;
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
