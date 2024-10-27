package entities;

import lombok.Getter;

@Getter
public class AuthorArticle {
    private final Article article;
    private final Author author;
    public AuthorArticle(Article article, Author author) {
        this.article = article;
        this.author = author;
    }

}
