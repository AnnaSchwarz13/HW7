package service;
import entities.Date;

public class DateService {
    //for filter 1 year 6month 1 month and 1 week
    public double timeIntervalOfTwoDates(Date date, Date today, String chosenDomain) {
        switch (chosenDomain) {
            case "1year" -> {
                if (((today.getYear() - date.getYear()) * 12 + today.getMonth() - date.getMonth()) <= 12) {
                    return 8760;//mean for last year
                }
            }
            case "6month" -> {
                if (((date.getYear() - today.getYear()) * 12 - date.getMonth() + today.getMonth()) <= 6) {
                    return 4320;//mean for last 6 month

                }
            }
            case "1month" -> {
                if (((date.getYear() - today.getYear()) * 12 - date.getMonth() + today.getMonth()) <= 1) {
                    return 720;//mean for last month
                }
            }
            case "1week" -> {
                if ((today.getYear() - date.getYear() <= 1)) {
                    if (((((date.getYear() - today.getYear()) * 12) + today.getMonth() - date.getMonth()) * 30
                            - date.getDay() + today.getDay()) <= 7) {
                        return 168;//mean for last week
                    }
                }
            }
            case "24hour" -> {
                if ((today.getYear() - date.getYear() <= 1)) {
                    if (((((date.getYear() - today.getYear()) * 12) + today.getMonth() - date.getMonth()) * 30
                            - date.getDay() + today.getDay()) <= 1) {
                        return 24;//mean for last 24hour
                    }
                }
            }
        }
        return 0;
    }

}
