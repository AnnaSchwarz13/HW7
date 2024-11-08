package entities;

import entities.enums.ArticleStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class Article {
    private Long id;
    private Author author;
    private String title;
    private entities.Category category;
    private String content;
    private entities.List brief;
    private Date createDate;
    boolean isPublished;
    private Date lastUpdateDate;
    private Date publishDate;
    private ArticleStatus status;

    public Article(Long id,String title,  String content,Category category,
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
    public Article(){

    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public boolean isPublished() {
        return isPublished;
    }

    @Override
    public String toString() {
        return  title +"\n"+
                "category:" + category.getTitle() +"\n"+
                "status : " + status +"\n"+
                "createDate : " + createDate + "\n"+
                "lastUpdateDate : " + lastUpdateDate  +
                " \n" + content+"\n\n" +
                "brief : " + brief.toString() ;
    }
}

