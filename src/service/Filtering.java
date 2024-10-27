package service;

import entities.Article;
import entities.Date;
import entities.List;

public class Filtering {
    DateService dateService = new DateService();
    Date today = new Date(ArticleService.todaysDateAsString().substring(0, 10));
    List filteredList = new List();
    public List filterTo1Year(List list , String whichDate) {

        switch (whichDate) {
            case "published" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = (Article) list.getObjects(i);
                    Date publishedDate = new Date(article.getPublishDate().substring(0, 10));
                    double result = dateService.timeIntervalOfTwoDates(today, publishedDate, "1year");
                    if (result != 0) {
                        filteredList.add(article);
                    }
                }
                return filteredList;
            }
            case "lastUpdate" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = (Article) list.getObjects(i);
                    Date lastUpdate = new Date(article.getLastUpdateDate().substring(0, 10));
                    double result = dateService.timeIntervalOfTwoDates(today, lastUpdate, "1year");
                    if (result != 0) {
                        filteredList.add(article);
                    }
                }
                return filteredList;
            }
            case "created" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = (Article) list.getObjects(i);
                    Date creatDate = new Date(article.getCreateDate().substring(0, 10));
                    double result = dateService.timeIntervalOfTwoDates(today, creatDate, "1year");
                    if (result != 0) {
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
                    Article article = (Article) list.getObjects(i);
                    Date publishedDate = new Date(article.getPublishDate().substring(0, 10));
                    double result = dateService.timeIntervalOfTwoDates(today, publishedDate, "6month");
                    if (result != 0) {
                        filteredList.add(article);
                    }
                }
                return filteredList;
            }
            case "lastUpdate" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = (Article) list.getObjects(i);
                    Date lastUpdate = new Date(article.getLastUpdateDate().substring(0, 10));
                    double result = dateService.timeIntervalOfTwoDates(today, lastUpdate, "6month");
                    if (result != 0) {
                        filteredList.add(article);
                    }
                }
                return filteredList;
            }
            case "created" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = (Article) list.getObjects(i);
                    Date creatDate = new Date(article.getCreateDate().substring(0, 10));
                    double result = dateService.timeIntervalOfTwoDates(today, creatDate, "6month");
                    if (result != 0) {
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
                    Article article = (Article) list.getObjects(i);
                    Date publishedDate = new Date(article.getPublishDate().substring(0, 10));
                    double result = dateService.timeIntervalOfTwoDates(today, publishedDate, "1month");
                    if (result != 0) {
                        filteredList.add(article);
                    }
                }
                return filteredList;
            }
            case "lastUpdate" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = (Article) list.getObjects(i);
                    Date lastUpdate = new Date(article.getLastUpdateDate().substring(0, 10));
                    double result = dateService.timeIntervalOfTwoDates(today, lastUpdate, "1month");
                    if (result != 0) {
                        filteredList.add(article);
                    }
                }
                return filteredList;
            }
            case "created" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = (Article) list.getObjects(i);
                    Date creatDate = new Date(article.getCreateDate().substring(0, 10));
                    double result = dateService.timeIntervalOfTwoDates(today, creatDate, "1month");
                    if (result != 0) {
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
                    Article article = (Article) list.getObjects(i);
                    Date publishedDate = new Date(article.getPublishDate().substring(0, 10));
                    double result = dateService.timeIntervalOfTwoDates(today, publishedDate, "1week");
                    if (result != 0) {
                        filteredList.add(article);
                    }
                }
                return filteredList;
            }
            case "lastUpdate" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = (Article) list.getObjects(i);
                    Date lastUpdate = new Date(article.getLastUpdateDate().substring(0, 10));
                    double result = dateService.timeIntervalOfTwoDates(today, lastUpdate, "1week");
                    if (result != 0) {
                        filteredList.add(article);
                    }
                }
                return filteredList;
            }
            case "created" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = (Article) list.getObjects(i);
                    Date creatDate = new Date(article.getCreateDate().substring(0, 10));
                    double result = dateService.timeIntervalOfTwoDates(today, creatDate, "1week");
                    if (result != 0) {
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
                    Article article = (Article) list.getObjects(i);
                    Date publishedDate = new Date(article.getPublishDate().substring(0, 10));
                    double result = dateService.timeIntervalOfTwoDates(today, publishedDate, "24hour");
                    if (result != 0) {
                        filteredList.add(article);
                    }
                }
                return filteredList;
            }
            case "lastUpdate" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = (Article) list.getObjects(i);
                    Date lastUpdate = new Date(article.getLastUpdateDate().substring(0, 10));
                    double result = dateService.timeIntervalOfTwoDates(today, lastUpdate, "24hour");
                    if (result != 0) {
                        filteredList.add(article);
                    }
                }
                return filteredList;
            }
            case "created" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = (Article) list.getObjects(i);
                    Date creatDate = new Date(article.getCreateDate().substring(0, 10));
                    double result = dateService.timeIntervalOfTwoDates(today, creatDate, "24hour");
                    if (result != 0) {
                        filteredList.add(article);
                    }
                }
                return filteredList;
            }
        }
        return null;
    }
}
