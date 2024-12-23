package repository.Imp;

import entities.Article;
import entities.Tag;
import repository.TagRepository;

import repository.Datasource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TagRepositoryImp implements TagRepository {
    private static final String INSERT_SQL =
            "INSERT INTO Tags(title) VALUES (?)";

    private static final String DELETE_BY_ARTICLE_ID_SQL = """
            DELETE FROM Tags_articles
            WHERE article_id = ?
            """;

    private static final String FIND_BY_ID_SQL = """
            SELECT * FROM Tags
            WHERE id = ?
            """;
    private static final String FIND_COUNT_SQL = """
            SELECT COUNT(*) FROM Tags
            """;
    public static final String READ_ALL_SQL = """
            SELECT * FROM Tags
            """;

    public static final String FIND_BY_TITLE_SQL = """
            SELECT * FROM Tags
            WHERE title = ?
            """;
    public static final String FIND_ARTICLES_TAG = """
            SELECT tag_id FROM Tags_articles
            WHERE article_id = ?
            """;
    public static final String INSET_ARTICLES_TAGS = """
            INSERT INTO Tags_articles(article_id, tag_id) VALUES (?, ?)
            """;


    @Override
    public Tag create(Tag tag) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(INSERT_SQL)) {
            statement.setString(1, tag.getTitle());
            statement.executeUpdate();
            return tag;
        }
    }

    public static Tag read(int id) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Tag tag = null;
            if (resultSet.next()) {
                long tagId = resultSet.getLong(1);
                String title = resultSet.getString(2);
                tag = new Tag(title, tagId);
            }

            return tag;
        }
    }

    @Override
    public void delete(long id) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(DELETE_BY_ARTICLE_ID_SQL)) {
            statement.setLong(1, id);
            var affectedRows = statement.executeUpdate();
            System.out.println("# of Contacts deleted: " + affectedRows);
        }
    }

    @Override
    public int findCount() throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(FIND_COUNT_SQL)) {
            ResultSet resultSet = statement.executeQuery();
            int tagsIndex = 0;
            if (resultSet.next()) {
                tagsIndex = resultSet.getInt(1);
            }
            return tagsIndex;
        }
    }

    @Override
    public List<Tag> all() {
        try (var statement = Datasource.getConnection().prepareStatement(READ_ALL_SQL)) {
            ResultSet resultSet = statement.executeQuery();
            List<Tag> tags = new LinkedList<>();
            while (resultSet.next()) {
                long tagId = resultSet.getLong(1);
                String title = resultSet.getString(2);
                Tag tag = new Tag(title, tagId);
                tags.add(tag);
            }
            return new ArrayList<>(tags);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Tag findTagByTile(String title) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(FIND_BY_TITLE_SQL)) {
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            Tag tag = null;
            if (resultSet.next()) {
                tag = read(resultSet.getInt(1));
            }
            return tag;
        }
    }

    @Override
    public List<Tag> getTags(Article article) {
        try (var statement = Datasource.getConnection().prepareStatement(FIND_ARTICLES_TAG)) {
            statement.setLong(1, article.getId());
            ResultSet resultSet = statement.executeQuery();
            List<Tag> tags = new LinkedList<>();
            while (resultSet.next()) {
                Tag tag = read(resultSet.getInt(1));
                tags.add(tag);
            }
            return new ArrayList<>(tags);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setArticlesTag(List<Tag> tags, Article article) {
        try (var statement = Datasource.getConnection().prepareStatement(INSET_ARTICLES_TAGS)) {
            for (Tag tag : tags) {
                statement.setLong(1, article.getId());
                statement.setLong(2, tag.getId());
                statement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
