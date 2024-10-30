package service;

import entities.Article;
import entities.AuthorArticle;
import java.sql.Date;
import java.time.LocalDate;

import entities.List;

import static service.DateService.todaysDateAsString;

public class Filtering {
    DateService dateService = new DateService();
    Date today = Date.valueOf(LocalDate.now());
    List filteredList = new List();
    public List filterTo1Year(List list , String whichDate) {
//TODO: make a new method of three.
        switch (whichDate) {
            case "published" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = ((AuthorArticle) list.getObjects(i)).getArticle();
                    Date publishedDate = Date.valueOf(article.getPublishDate().substring(0, 10));
                    if (dateService.timeIntervalOfTwoDates(today,publishedDate, "1year")) {
                        filteredList.add(article);
                    }
                }
                return filteredList;
            }
            case "lastUpdate" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = ((AuthorArticle) list.getObjects(i)).getArticle();
                    Date lastUpdate = Date.valueOf(article.getLastUpdateDate().substring(0, 10));
                    if ( dateService.timeIntervalOfTwoDates(today,lastUpdate,"1year")) {
                        filteredList.add(article);
                    }
                }
                return filteredList;
            }
            case "created" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = ((AuthorArticle) list.getObjects(i)).getArticle();
                    Date creatDate = Date.valueOf(article.getCreateDate().substring(0, 10));
                    if (dateService.timeIntervalOfTwoDates(today, creatDate, "1year")) {
                        filteredList.add(article);
                    }
                }
                return filteredList;
            }
        }
        return null;
    }
    public List filterTo6Month(List list , String whichDate) {

        switch (whichDate) {
            case "published" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = ((AuthorArticle) list.getObjects(i)).getArticle();
                    Date publishedDate = Date.valueOf(article.getPublishDate().substring(0, 10));
                    if (dateService.timeIntervalOfTwoDates(today, publishedDate, "6month")) {
                        filteredList.add(article);
                    }
                }
                return filteredList;
            }
            case "lastUpdate" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = ((AuthorArticle) list.getObjects(i)).getArticle();
                    Date lastUpdate = Date.valueOf(article.getLastUpdateDate().substring(0, 10));
                    if (dateService.timeIntervalOfTwoDates(today, lastUpdate, "6month")){
                        filteredList.add(article);
                    }
                }
                return filteredList;
            }
            case "created" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = ((AuthorArticle) list.getObjects(i)).getArticle();
                    Date creatDate = Date.valueOf(article.getCreateDate().substring(0, 10));
                    if (dateService.timeIntervalOfTwoDates(today, creatDate, "6month")) {
                        filteredList.add(article);
                    }
                }
                return filteredList;
            }
        }
        return null;
    }
    public List filterTo1Month(List list , String whichDate) {

        switch (whichDate) {
            case "published" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = ((AuthorArticle) list.getObjects(i)).getArticle();
                    Date publishedDate = Date.valueOf(article.getPublishDate().substring(0, 10));
                    if (dateService.timeIntervalOfTwoDates(today, publishedDate, "1month")) {
                        filteredList.add(article);
                    }
                }
                return filteredList;
            }
            case "lastUpdate" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = ((AuthorArticle) list.getObjects(i)).getArticle();
                    Date lastUpdate = Date.valueOf(article.getLastUpdateDate().substring(0, 10));
                    if (dateService.timeIntervalOfTwoDates(today, lastUpdate, "1month")) {
                        filteredList.add(article);
                    }
                }
                return filteredList;
            }
            case "created" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = ((AuthorArticle) list.getObjects(i)).getArticle();
                    Date creatDate = Date.valueOf(article.getCreateDate().substring(0, 10));
                    if (dateService.timeIntervalOfTwoDates(today, creatDate, "1month")) {
                        filteredList.add(article);
                    }
                }
                return filteredList;
            }
        }
        return null;
    }
    public List filterTo1Week(List list , String whichDate) {

        switch (whichDate) {
            case "published" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = ((AuthorArticle) list.getObjects(i)).getArticle();
                    Date publishedDate = Date.valueOf(article.getPublishDate().substring(0, 10));
                    if (dateService.timeIntervalOfTwoDates(today, publishedDate, "1week")) {
                        filteredList.add(article);
                    }
                }
                return filteredList;
            }
            case "lastUpdate" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = ((AuthorArticle) list.getObjects(i)).getArticle();
                    Date lastUpdate = Date.valueOf(article.getLastUpdateDate().substring(0, 10));
                    if (dateService.timeIntervalOfTwoDates(today, lastUpdate, "1week")) {
                        filteredList.add(article);
                    }
                }
                return filteredList;
            }
            case "created" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = ((AuthorArticle) list.getObjects(i)).getArticle();
                    Date creatDate = Date.valueOf(article.getCreateDate().substring(0, 10));

                    if (dateService.timeIntervalOfTwoDates(today, creatDate, "1week")) {
                        filteredList.add(article);
                    }
                }
                return filteredList;
            }
        }
        return null;
    }
    public List filterTo24Hour(List list , String whichDate) {

        switch (whichDate) {
            case "published" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = ((AuthorArticle) list.getObjects(i)).getArticle();
                    Date publishedDate = Date.valueOf(article.getPublishDate().substring(0, 10));
                    if ( dateService.timeIntervalOfTwoDates(today, publishedDate, "24hour")) {
                        filteredList.add(article);
                    }
                }
                return filteredList;
            }
            case "lastUpdate" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = ((AuthorArticle) list.getObjects(i)).getArticle();
                    Date lastUpdate = Date.valueOf(article.getLastUpdateDate().substring(0, 10));
                    if (dateService.timeIntervalOfTwoDates(today, lastUpdate, "24hour")) {
                        filteredList.add(article);
                    }
                }
                return filteredList;
            }
            case "created" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = ((AuthorArticle) list.getObjects(i)).getArticle();
                    Date creatDate = Date.valueOf(article.getCreateDate().substring(0, 10));
                    if (dateService.timeIntervalOfTwoDates(today, creatDate, "24hour")) {
                        filteredList.add(article);
                    }
                }
                return filteredList;
            }
        }
        return null;
    }
}
