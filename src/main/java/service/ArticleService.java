package service;

import entities.Article;

import java.sql.SQLException;
import java.util.List;

public interface ArticleService {
    void addArticle() throws SQLException;
    void showAnArticleList(List<Article> articles) throws SQLException;
    void changeArticleStatus(Article choosenArticle) throws SQLException;
    void changeDetailsOfArticle(Article choosenArticle) throws SQLException;
    void displayArticle(Article choosenArticle);
}
