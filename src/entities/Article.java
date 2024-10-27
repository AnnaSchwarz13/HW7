package entities;

import entities.enums.ArticleStatus;

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

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(entities.Category category) {
        this.category = category;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List getBrief() {
        return brief;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public void setStatus(ArticleStatus status) {
        this.status = status;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
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

