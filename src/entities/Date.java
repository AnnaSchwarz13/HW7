package entities;

import java.time.LocalDate;
import java.util.Scanner;

public class Date {
    Scanner scanner = new Scanner(System.in);
    //for Gregorian calender
    private int year;
    private int month;
    private int day;
    private boolean isDateValid = false;
    private final LocalDate today = LocalDate.now();

    public Date(String date) {
            if (isInputValid(date)) {
                String[] arrayOfBirth = date.split("/");
                this.year = Integer.parseInt(arrayOfBirth[0]);
                this.month = Integer.parseInt(arrayOfBirth[1]);
                this.day = Integer.parseInt(arrayOfBirth[2]);
                if (checkBirthdayLvl1() && checkBirthdayLvl2()) {
                   isDateValid=true;
                } else{
                    System.out.println("Invalid date");
                }
            }
        }


    private boolean checkBirthdayLvl1() {
        //check is birthday is before now and is now out bound
        if (year < today.getYear()) {
            if ((month > 0) && (month <= 12)) {
                return ((day > 0) && (day <= 31));
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
            return day != 30;
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

    public boolean isDateValid() {
        return isDateValid;
    }
}

