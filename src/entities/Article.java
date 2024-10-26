package entities;

import entities.Lists.TagList;
import entities.enums.ArticleStatus;

public class Article {
    private String title;
    private Category category;
    private String content;
    private double id;
    private TagList brief;
    private String createDate;
    boolean isPublished;
    private String lastUpdateDate;
    private String publishDate;
    private ArticleStatus status;


    public void setId(double id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TagList getBrief() {
        return brief;
    }

    public void setBrief(TagList brief) {
        this.brief = brief;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
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


    @Override
    public String toString() {//todo: write a method for that
        return  title +"\n"+
                "category:" + category.getTitle() +"\n"+
                "status : " + status +"\n"+
                "createDate : " + createDate + "\n"+
                "lastUpdateDate : " + lastUpdateDate  +
                " \n" + content+"\n\n" +
                "brief : " + brief.toStringTags() ;
    }
}

