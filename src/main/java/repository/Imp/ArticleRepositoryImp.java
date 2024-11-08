package repository.Imp;


import entities.Article;
import entities.Author;
import entities.Category;
import entities.enums.ArticleStatus;
import repository.ArticleRepository;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.String.valueOf;


public class ArticleRepositoryImp implements ArticleRepository {
    //CRUD create read update delete
    static DataSource ds;
    static AuthorRepositoryImp authorRepositoryImp;
    static CategoryRepositoryImp categoryRepositoryImp;
    static ArticleRepositoryImp articleRepositoryImp;
    private static final String INSERT_SQL = """
            INSERT INTO Articles(title, text,category_id, published_date  ,
                                   created_date , last_updated_date\s
                                 , author_id , is_published, article_status)
            VALUES (?, ?, ? ,? ,? ,? ,? , ?)
           """;

    private static final String DELETE_BY_ID_SQL = """
            DELETE FROM Articles
            WHERE id = ?
            """;

    private static final String FIND_BY_ID_SQL = """
            SELECT * FROM Articles
            WHERE id = ?
            """;
    private static final String PUBLISHED_ARTICLES_SQL= """
            SELECT id FROM Articles
            WHERE ARTICLE_STATUS = 'PUBLISHED'
            """;

    private static final String PENDING_ARTICLES_SQL = """
            SELECT id FROM Articles
            WHERE ARTICLE_STATUS = 'PENDING'
            """;

    @Override
    public Article create(Article article) throws SQLException {
        try (var statement = ds.getConnection().prepareStatement(INSERT_SQL)) {
            statement.setString(1, article.getTitle());
            statement.setString(2, article.getContent());
            statement.setLong(3, (article.getCategory()).getId());
            statement.setDate(4, (Date) article.getPublishDate());
            statement.setDate(5, (Date) article.getCreateDate());
            statement.setDate(6, (Date) article.getLastUpdateDate());
            statement.setInt(7, article.getAuthor().getId());
            statement.setString(8, valueOf(article.getStatus()));


            return article;
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

    @Override
    public Article read(int id) throws SQLException {
        try (var statement = ds.getConnection().prepareStatement(FIND_BY_ID_SQL)) {
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
                Author author = authorRepositoryImp.read(authorId);
                article = new Article(articleId, title, text, category, createDate,
                        published, lastUpdateDate, ArticleStatus.valueOf(status), author);

                if (published) {
                    article.setLastUpdateDate(lastUpdateDate);
                }

            }

            return article;
        }
    }
        static public List<Article> allPublished(){
            try (var statement = ds.getConnection().prepareStatement(PUBLISHED_ARTICLES_SQL)) {
                ResultSet resultSet = statement.executeQuery();
                List<Article> publishedArticles = new LinkedList<>();
                while (resultSet.next()) {
                  Article article =articleRepositoryImp.read(resultSet.getInt(1));
                  publishedArticles.add(article);
                }

                return new ArrayList<>(publishedArticles);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        static public List<Article> allPending() {
            try (var statement = ds.getConnection().prepareStatement(PENDING_ARTICLES_SQL)) {
                ResultSet resultSet = statement.executeQuery();
                List<Article> pendingArticles = new LinkedList<>();
                while (resultSet.next()) {
                    Article article =articleRepositoryImp.read(resultSet.getInt(1));
                    pendingArticles.add(article);
                }

                return new ArrayList<>(pendingArticles);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    }



