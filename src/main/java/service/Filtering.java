package service;

import entities.Article;

import java.sql.Date;
import java.time.LocalDate;

import java.util.LinkedList;
import java.util.List;

public class Filtering {
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
            if (timeIntervalOfTwoDates(today, date, 365)) {
                filteredList.add(article);
            }
        } else if (distance == 180) {
            if (timeIntervalOfTwoDates(today, date, 180)) {
                filteredList.add(article);
            }
        } else if (distance == 30) {
            if (timeIntervalOfTwoDates(today, date, 30)) {
                filteredList.add(article);
            }
        } else if (distance == 7) {
            if (timeIntervalOfTwoDates(today, date, 7)) {
                filteredList.add(article);
            }
        } else if (distance == 1) {
            if (timeIntervalOfTwoDates(today, date, 1)) {
                filteredList.add(article);
            }
        }
    }

    public boolean timeIntervalOfTwoDates(Date today, Date date, int chosenDomain) {
        switch (chosenDomain) {
            case 365 -> {
                return ((today.getYear() - date.getYear()) * 12 + today.getMonth() - date.getMonth()) <= 12;//mean for last year
            }
            case 180 -> {
                return ((today.getYear() - date.getYear()) * 12 + today.getMonth() - date.getMonth()) <= 6;//mean for last 6 month
            }
            case 30 -> {
                return ((today.getYear() - date.getYear()) * 12 + today.getMonth() - date.getMonth()) <= 1;//mean for last month
            }
            case 7 -> {
                if ((today.getYear() - date.getYear() <= 1)) {
                    return (((today.getYear() - date.getYear()) * 12 + today.getMonth() - date.getMonth()) * 30
                            - date.getDay() + today.getDay()) <= 7;//mean for last week
                }
            }
            case 1 -> {
                if ((today.getYear() - date.getYear() <= 1)) {
                    return ((((-date.getYear() + today.getYear()) * 12) + today.getMonth() - date.getMonth()) * 30
                            - date.getDay() + today.getDay()) <= 1;//mean for last 24hour
                }
            }
        }
        return false;
    }
}