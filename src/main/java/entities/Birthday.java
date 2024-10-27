package entities;

import java.time.LocalDate;
import java.util.Scanner;

public class Birthday extends Date {
    Scanner scanner = new Scanner(System.in);
    //for Gregorian calender
    private boolean isBirthdayValid;
    LocalDate today = LocalDate.now();

    public Birthday(String date) {
        super(date);
            if (super.isDateValid) {
                if (checkBirthdayLvl3()) {
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

    }

    private boolean checkBirthdayLvl3() {
        //birthday is not in future
        if (year == today.getYear()) {
            if (month <= today.getMonthValue()) {
                return day < today.getDayOfMonth();
            }
        }
        return true;
    }


    public boolean isBirthdayValid() {
        return isBirthdayValid;
    }
}
