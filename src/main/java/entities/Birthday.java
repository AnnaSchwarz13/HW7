package entities;

import java.sql.Date;
import java.time.LocalDate;


public class Birthday extends Date {
    Date today = Date.valueOf(LocalDate.now());
    boolean isBirthdayValid;
    public Birthday(String date) {
        super(Integer.parseInt(date.substring(0, 4)),Integer.parseInt(date.substring(5, 7)) , Integer.parseInt(date.substring(8, 10)));
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));

        Date birthday = new Date(year, month, day);
        //for Gregorian calender

        if (!birthday.before(today)) {
            System.out.println("Birthday : " + year + "-" + month + "-" + day);
        } else {
            System.out.println("Invalid birthday");
            isBirthdayValid = false;
        }
    }

    public boolean isBirthdayValid() {
        return isBirthdayValid;
    }

}
