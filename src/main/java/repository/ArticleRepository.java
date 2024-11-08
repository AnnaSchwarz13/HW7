package repository;

import entities.Article;

import java.sql.SQLException;

public interface ArticleRepository {
    Article create(Article article) throws SQLException;
    void delete(int id) throws SQLException;
    Article read(int id) throws SQLException;
}