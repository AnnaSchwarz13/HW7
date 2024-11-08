package service;

import database.DataBase;
import entities.Author;

import java.sql.Date;

public class AuthorService extends UserService{


    public void userSignup(String firstName, String lastName, String username,
                           String password, String nationalCode, Date birthday) {
        
        Author signingAuthor = new Author(firstName, lastName, username, password, nationalCode, birthday);
        DataBase.userList.add(signingAuthor);
        System.out.println("Author signed up successfully");
    }


    public void changePassword(String oldPassword, String newPassword) {
        if (AuthenticationService.getLoggedUser().getPassword().equals(oldPassword)) {
            AuthenticationService.getLoggedUser().setPassword(newPassword);
            return;
        }
        System.out.println("Wrong password");
    }

}