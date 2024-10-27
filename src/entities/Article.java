package entities;

import entities.enums.ArticleStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Article {
    private String title;
    private entities.Category category;
    private String content;
    private entities.List brief;
    private String createDate;
    boolean isPublished;
    private String lastUpdateDate;
    private String publishDate;
    private ArticleStatus status;

    public Article(String title, Category category, String content,
                   double id, List brief, String createDate,
                   boolean isPublished, String lastUpdateDate, ArticleStatus status) {
        this.title = title;
        this.category = category;
        this.content = content;
        this.brief = brief;
        this.createDate = createDate;
        this.isPublished = isPublished;
        this.lastUpdateDate = lastUpdateDate;
        this.status = status;
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

