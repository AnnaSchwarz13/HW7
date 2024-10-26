package service;

import database.DataBase;
import entities.Author;
import entities.Birthday;

public class AuthorService extends UserService{


    public void userSignup(String firstName, String lastName, String userName,
                           String password, String nationalCode, Birthday birthday) {
        //todo: use a single constructor
        Author signingAuthor = new Author(userName, password);
        signingAuthor.setFirstName(firstName);
        signingAuthor.setLastName(lastName);
        signingAuthor.setUsername(userName);
        signingAuthor.setPassword(password);
        signingAuthor.setNationalCode(nationalCode);
        signingAuthor.setBirthday(birthday);
        DataBase.userList.add(signingAuthor);
        System.out.println("Author signed up successfully");
    }


    public void changePassword(String oldPassword, String newPassword) {
        //todo: use Database userList unless loggedInUser
        if (DataBase.loggedInAuthor.getPassword().equals(oldPassword)) {
            DataBase.loggedInAuthor.setPassword(newPassword);
            return;
        }
        System.out.println("Wrong password");
    }

}