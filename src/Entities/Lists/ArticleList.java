package Entities.Lists;

import Entities.Article;

import java.util.Arrays;
//todo: make a mother class user list or list
public class ArticleList {
    private Article[] articles;
    private int index;

    public ArticleList() {
        this.articles = new Article[10];
        this.index = 0;
    }

    public void add(Article article) {
        articles[index] = article;
        index++;

        if (index >= articles.length)
            articles = Arrays.copyOf(articles, articles.length * 2);
    }

    public int getIndex() {
        return index;
    }

    public Article getArticles(int index) {
        return articles[index];
    }

}
