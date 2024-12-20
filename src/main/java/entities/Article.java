package entities;

import entities.enums.ArticleStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter

public class Article {
    private long id;
    private Author author;
    private String title;
    private entities.Category category;
    private String content;
    private Date createDate;
    boolean isPublished;
    private Date lastUpdateDate;
    private Date publishDate;
    private ArticleStatus status;
    private List<Tag> brief ;

    public Article(long id,String title,  String content,Category category,
                     Date createDate, boolean isPublished,
                   Date lastUpdateDate, ArticleStatus status , Author author) {
        this.title = title;
        this.category = category;
        this.content = content;
        this.createDate = createDate;
        this.isPublished = isPublished;
        this.lastUpdateDate = lastUpdateDate;
        this.status = status;
        this.author = author;
        this.id = id;
    }

    public Article(Author author, String title, Category category, String content) {
        this.author = author;
        this.title = title;
        this.category = category;
        this.content = content;
    }
}



