package entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;


public class Birthday extends Date {
    Scanner scanner = new Scanner(System.in);
    Date today = Date.valueOf(LocalDate.now());
    boolean isBirthdayValid;
    public Birthday(String date) {

        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));
        super(year, month, day);
        Date birthday = new Date(year, month, day);
        //for Gregorian calender

        if (!birthday.before(today)) {
            System.out.println("Birthday = " + year + "-" + month + "-" + day);
            System.out.println("\n is correct? \n 1.Yes! \n 2.Edit");
            int toSetBirthday = scanner.nextInt();
            if (toSetBirthday == 1) {
                isBirthdayValid = true;
            } else if (toSetBirthday == 2) {
                isBirthdayValid = false;
            }
        } else {
            System.out.println("Invalid birthday");
            isBirthdayValid = false;
        }
    }

    public boolean isBirthdayValid() {
        return isBirthdayValid;
    }

}
