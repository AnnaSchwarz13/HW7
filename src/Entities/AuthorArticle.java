package Entities;

public class AuthorArticle {
    private final Article article;
    private final Author author;
    public AuthorArticle(Article article, Author author) {
        this.article = article;
        this.author = author;
    }

    public Article getArticle() {
        return article;
    }

    public Author getAuthor() {
        return author;
    }
}
