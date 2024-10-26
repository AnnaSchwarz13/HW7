package entities;

import java.time.LocalDate;
import java.util.Scanner;

public class Birthday {
    Scanner scanner = new Scanner(System.in);
    //for Gregorian calender
    private int year;
    private int month;
    private int day;
    private boolean isBirthdayValid;
    LocalDate today = LocalDate.now();

    public Birthday() {
        isBirthdayValid = false;
        while (!isBirthdayValid) {
            System.out.println("Enter your birthday like example:\nexample: 1995/12/3 ");
            String date = scanner.next();
            while (isInputValid(date)) {
                String[] arrayOfBirth = date.split("/");
                this.year = Integer.parseInt(arrayOfBirth[0]);
                this.month = Integer.parseInt(arrayOfBirth[1]);
                this.day = Integer.parseInt(arrayOfBirth[2]);
                if (checkBirthdayLvl1() && checkBirthdayLvl2()) {
                    System.out.println("Birthday = " + year + "/" + month + "/" + day);
                    System.out.println("\n is correct? \n 1.Yes! \n 2.Edit");
                    int toSetBirthday = scanner.nextInt();
                    if (toSetBirthday == 1) {
                        isBirthdayValid = true;
                        return;
                    } else if (toSetBirthday == 2) {
                        isBirthdayValid = false;
                        break;
                    }
                } else{
                    System.out.println("Invalid birthday");
                    break;
            }
            }
        }
    }

    private boolean checkBirthdayLvl1() {
        //check is birthday is before now and is now out bound
        if (year <= today.getYear()) {
            if (month <= today.getMonthValue() && ((month > 0) && (month <= 12))) {
                if (day < today.getDayOfMonth() && ((day > 0) && (day <= 31))) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkBirthdayLvl2() {
        if (month == 11 || month == 9 || month == 6 || month == 4 || month == 2) {
            if (day == 31) {
                return false;
            }
        }
        if (month == 2) {
            if (day == 30) {
                return false;
            }
        }
        return true;
    }

    private boolean isInputValid(String date) {
        if (date.split("/").length != 3) {
            System.out.println("Invalid birthday");
            return false;
        }
        return true;
    }

    public boolean isBirthdayValid() {
        return isBirthdayValid;
    }
}
