package Entities;

import java.time.LocalDate;

public class Birthday {
    //todo:fix bug in wrong birthday
    //baray taghvim Miladi
    private int year;
    private int month;
    private int day;
    LocalDate today = LocalDate.now();

    public Birthday(String date) {
        String[] arrayOfBirth = getBirthdays(date);
        if(arrayOfBirth !=null) {
            this.year = Integer.parseInt(arrayOfBirth[0]);
            this.month = Integer.parseInt(arrayOfBirth[1]);
            this.day = Integer.parseInt(arrayOfBirth[2]);
            if (checkBirthdayLvl1() && checkBirthdayLvl2()) {
                System.out.println("Birthday = " + year + "/" + month + "/" + day);
                System.out.println("\n is correct? \n 1.Yes! \n 2.Edit");

            }
        }else {
            System.out.println("Invalid birthday");
            this.year = 0;
            this.month = 0;
            this.day = 0;
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

    private String[] getBirthdays(String date) {
        if (date.split("/").length != 3) {
            System.out.println("Invalid birthday");
            return null;
        }
        return date.split("/");
    }

    public int getYear() {
        return year;
    }

}
