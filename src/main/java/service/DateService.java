package service;
import entities.Date;

import java.time.Clock;
import java.time.ZoneId;

public class DateService {
    //for filter 1 year 6month 1 month and 1 week
    public boolean timeIntervalOfTwoDates(Date today, Date date, String chosenDomain) {
        switch (chosenDomain) {
            case "1year" -> {
                if (((today.getYear() - date.getYear()) * 12 + today.getMonth() - date.getMonth()) <= 12) {
                    return true;//mean for last year
                }
                else{
                    return false;
                }
            }
            case "6month" -> {
                if (((today.getYear() - date.getYear()) * 12 + today.getMonth() - date.getMonth()) <= 6) {
                    return true;//mean for last 6 month

                }
                else{
                    return false;
                }
            }
            case "1month" -> {
                if (((today.getYear() - date.getYear()) * 12 + today.getMonth() - date.getMonth()) <= 1) {
                    return true;//mean for last month
                }
                else{
                    return false;
                }
            }
            case "1week" -> {
                if ((today.getYear() - date.getYear() <= 1)) {
                    if ((((today.getYear() - date.getYear()) * 12 + today.getMonth() - date.getMonth()) * 30
                            - date.getDay() + today.getDay()) <= 7) {
                        return true;//mean for last week
                    }
                    else{
                        return false;
                    }
                }
            }
            case "24hour" -> {
                if ((today.getYear() - date.getYear() <= 1)) {
                    if ((((( - date.getYear() + today.getYear()) * 12) + today.getMonth() - date.getMonth()) * 30
                            - date.getDay() + today.getDay()) <= 1) {
                        return true;//mean for last 24hour
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        return false;
    }
    public static String todaysDateAsString() {
        Clock clock = Clock.system(ZoneId.of("Asia/Tehran"));
        return clock.instant().toString().substring(0, 10) + " " + clock.instant().toString().substring(10, 16);

    }
}
