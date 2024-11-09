package repository.Imp;


import entities.Article;
import entities.Author;
import entities.Category;
import entities.enums.ArticleStatus;
import repository.ArticleRepository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import repository.Datasource;

public class ArticleRepositoryImp implements ArticleRepository {
    //CRUD create read update delete
    static CategoryRepositoryImp categoryRepositoryImp = new CategoryRepositoryImp();
    private static final String INSERT_SQL = """
             INSERT INTO Articles(title, text,category_id, published_date  ,
                                    created_date , last_updated_date\s
                                  , author_id , is_published, article_status)
             VALUES (?, ? ,? ,? ,? ,? , ?,?)
            """;

    private static final String DELETE_BY_ID_SQL = """
            DELETE FROM Articles
            WHERE id = ?
            """;

    private static final String FIND_BY_ID_SQL = """
            SELECT * FROM Articles
            WHERE id = ?
            """;
    private static final String PUBLISHED_ARTICLES_SQL = """
            SELECT id FROM Articles
            WHERE ARTICLE_STATUS = 'PUBLISHED'
            """;

    private static final String PENDING_ARTICLES_SQL = """
            SELECT id FROM Articles
            WHERE ARTICLE_STATUS = 'PENDING'
            """;
    private static final String UPDATE_Article_Status_SQL = """
            UPDATE Articles
            SET ? = ? , ?=? , ?=? , ?=?
            where id = ?
            """;
    public static final String FIND_BY_TITLE_SQL = """
            SELECT * FROM Articles
            WHERE title = ?
            """;


    @Override
    public Article create(Article article) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(INSERT_SQL)) {
            statement.setString(1, article.getTitle());
            statement.setString(2, article.getContent());
            statement.setLong(3, (article.getCategory()).getId());
            statement.setDate(4, null);
            statement.setDate(5, Date.valueOf(LocalDate.now()));
            statement.setDate(6, Date.valueOf(LocalDate.now()));
            statement.setLong(7, article.getAuthor().getId());
            statement.setBoolean(8,false);
            statement.setString(9, "NOT_PUBLISHED");
            statement.executeUpdate();
            return article;
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


    public static Article read(long id) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            Article article = null;
            if (resultSet.next()) {
                long articleId = resultSet.getLong(1);
                String title = resultSet.getString(2);
                String text = resultSet.getString(3);
                int categoryId = resultSet.getInt(4);
                Date publishDate = resultSet.getDate(5);
                Date createDate = resultSet.getDate(6);
                Date lastUpdateDate = resultSet.getDate(7);
                int authorId = resultSet.getInt(8);
                boolean published = resultSet.getBoolean(9);
                String status = resultSet.getString(10);
                Category category = categoryRepositoryImp.read(categoryId);
                Author author = AuthorRepositoryImp.read(authorId);
                article = new Article(articleId, title, text, category, createDate,
                        published, lastUpdateDate, ArticleStatus.valueOf(status), author);

                if (published) {
                    article.setPublishDate(publishDate);
                }

            }

            return article;
        }
    }

    static public List<Article> allPublished() {
        return getArticles(PUBLISHED_ARTICLES_SQL);

    }

    static public List<Article> allPending() {
        return getArticles(PENDING_ARTICLES_SQL);

    }

    private static List<Article> getArticles(String publishedArticlesSql) {
        try (var statement = Datasource.getConnection().prepareStatement(publishedArticlesSql)) {
            ResultSet resultSet = statement.executeQuery();
            List<Article> publishedArticles = new LinkedList<>();
            while (resultSet.next()) {
                Article article = read(resultSet.getInt(1));
                publishedArticles.add(article);
            }

            return new ArrayList<>(publishedArticles);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    static public void updateStatusPublished(Article article) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(UPDATE_Article_Status_SQL)) {
            statement.setString(1, "article_status");
            statement.setString(2, "PUBLISHED");
            statement.setString(3, "published_date");
            statement.setDate(4, Date.valueOf(LocalDate.now()));
            statement.setString(5, "last_updated_date");
            statement.setDate(6, Date.valueOf(LocalDate.now()));
            statement.setString(7, "is_published");
            statement.setBoolean(8, true);
            statement.setLong(9, article.getId());
            statement.executeUpdate();
        }

    }

    static public void updateStatusNotPublished(Article article) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(UPDATE_Article_Status_SQL)) {
            statement.setString(1, "article_status");
            statement.setString(2, "NOT_PUBLISHED");
            ChangStatus(article, statement);
        }
    }

    static public void updateStatusPending(Article article) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(UPDATE_Article_Status_SQL)) {
            statement.setString(1, "article_status");
            statement.setString(2, "PENDING");
            ChangStatus(article, statement);
        }
    }

    private static void ChangStatus(Article article, PreparedStatement statement) throws SQLException {
        statement.setString(3, "published_date");
        statement.setDate(4, null);
        statement.setString(5, "last_updated_date");
        statement.setDate(6, Date.valueOf(LocalDate.now()));
        statement.setString(7, "is_published");
        statement.setBoolean(8, false);
        statement.setLong(9, article.getId());
        statement.executeUpdate();
    }

    public static Article findArticleByTile(String title) throws SQLException {
        try (var statement = Datasource.getConnection().prepareStatement(FIND_BY_TITLE_SQL)) {
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            Article article =null;
            if (resultSet.next()) {
             article=read(resultSet.getInt(1));
            }
            else{
                System.out.println("No Article found for title: " + title);
            }

            return article;

        }
    }


}



