package service;

import entities.Article;

import java.sql.Date;
import java.time.LocalDate;

import java.util.LinkedList;
import java.util.List;

public class Filtering {
    DateService dateService = new DateService();
    Date today = Date.valueOf(LocalDate.now());
    List filteredList = new LinkedList();

    public List filter(List list, String whichDate, int distance) {
        switch (whichDate) {
            case "published" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = ((Article) list.getObjects(i));
                    Date publishedDate = (Date) article.getPublishDate();
                    checkDistance(distance, article, publishedDate);

                }
                return filteredList;
            }
            case "lastUpdate" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = ((Article) list.getObjects(i));
                    Date lastUpdate = (Date) article.getLastUpdateDate();
                    checkDistance(distance, article, lastUpdate);

                }
                return filteredList;
            }
            case "created" -> {
                for (int i = 0; i < list.getIndex(); i++) {
                    Article article = ((Article) list.getObjects(i));
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
            if (dateService.timeIntervalOfTwoDates(today, date, "1year")) {
                filteredList.add(article);
            }
        } else if (distance == 180) {
            if (dateService.timeIntervalOfTwoDates(today, date, "6month")) {
                filteredList.add(article);
            }
        } else if (distance == 30) {
            if (dateService.timeIntervalOfTwoDates(today, date, "1month")) {
                filteredList.add(article);
            }
        } else if (distance == 7) {
            if (dateService.timeIntervalOfTwoDates(today, date, "1week")) {
                filteredList.add(article);
            }
        } else if (distance == 1) {
            if (dateService.timeIntervalOfTwoDates(today, date, "24hour")) {
                filteredList.add(article);
            }
        }
    }
}