package repository;

import entities.Article;
import entities.Author;
import entities.Category;

import java.sql.SQLException;
import java.util.List;

public interface ArticleRepository {
    Article create(Article article) throws SQLException;

    void delete(long id) throws SQLException;

    //  Article read(int id) throws SQLException;
    Article findArticleByTile(String title) throws SQLException;

    void setLastUpdateDate(Article article) throws SQLException;

    void updateCategory(Article article, Category category) throws SQLException;

    void updateText(Article article, String newValue) throws SQLException;

    void updateTitle(Article article, String newValue) throws SQLException;

    void updateStatusPending(Article article) throws SQLException;

    void updateStatusNotPublished(Article article) throws SQLException;

    void updateStatusPublished(Article article) throws SQLException;

    List<Article> getArticlesOfAnAuthor(Author author);

    List<Article> allPending();

    List<Article> allPublished();

}
