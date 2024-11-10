package service.Imp;
import java.sql.Date;

import java.time.Clock;
import java.time.ZoneId;

public class DateServiceImp {
    //for filter 1 year 6month 1 month and 1 week
    public boolean timeIntervalOfTwoDates(Date today, Date date, String chosenDomain) {
        //TODO:don't use string -> int
        switch (chosenDomain) {
            case "1year" -> {
                return ((today.getYear() - date.getYear()) * 12 + today.getMonth() - date.getMonth()) <= 12;//mean for last year
            }
            case "6month" -> {
                return ((today.getYear() - date.getYear()) * 12 + today.getMonth() - date.getMonth()) <= 6;//mean for last 6 month
            }
            case "1month" -> {
                return ((today.getYear() - date.getYear()) * 12 + today.getMonth() - date.getMonth()) <= 1;//mean for last month
            }
            case "1week" -> {
                if ((today.getYear() - date.getYear() <= 1)) {
                    return (((today.getYear() - date.getYear()) * 12 + today.getMonth() - date.getMonth()) * 30
                            - date.getDay() + today.getDay()) <= 7;//mean for last week
                }
            }
            case "24hour" -> {
                if ((today.getYear() - date.getYear() <= 1)) {
                    return ((((-date.getYear() + today.getYear()) * 12) + today.getMonth() - date.getMonth()) * 30
                            - date.getDay() + today.getDay()) <= 1;//mean for last 24hour
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
