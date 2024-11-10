package service;

import entities.Article;
import service.Imp.DateServiceImp;

import java.sql.Date;
import java.time.LocalDate;

import java.util.LinkedList;
import java.util.List;

public class Filtering {
    DateServiceImp dateServiceImp = new DateServiceImp();
    Date today = Date.valueOf(LocalDate.now());
    List<Article> filteredList = new LinkedList<>();

    public List<Article> filter(List<Article> list, String whichDate, int distance) {
        switch (whichDate) {
            case "published" -> {
                for (Article article : list) {
                    Date publishedDate = (Date) article.getPublishDate();
                    checkDistance(distance, article, publishedDate);

                }
                return filteredList;
            }
            case "lastUpdate" -> {
                for (Article article : list) {
                    Date lastUpdate = (Date) article.getLastUpdateDate();
                    checkDistance(distance, article, lastUpdate);

                }
                return filteredList;
            }
            case "created" -> {
                for (Article article : list) {
                    Date creatDate = (Date) article.getCreateDate();
                    checkDistance(distance, article, creatDate);

                }
                return filteredList;
            }
        }
        return null;
    }


    private void checkDistance(int distance, Article article, Date date) {
        if (distance == 365) {
            if (dateServiceImp.timeIntervalOfTwoDates(today, date, "1year")) {
                filteredList.add(article);
            }
        } else if (distance == 180) {
            if (dateServiceImp.timeIntervalOfTwoDates(today, date, "6month")) {
                filteredList.add(article);
            }
        } else if (distance == 30) {
            if (dateServiceImp.timeIntervalOfTwoDates(today, date, "1month")) {
                filteredList.add(article);
            }
        } else if (distance == 7) {
            if (dateServiceImp.timeIntervalOfTwoDates(today, date, "1week")) {
                filteredList.add(article);
            }
        } else if (distance == 1) {
            if (dateServiceImp.timeIntervalOfTwoDates(today, date, "24hour")) {
                filteredList.add(article);
            }
        }
    }
}