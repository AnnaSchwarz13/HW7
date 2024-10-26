package service;

import database.DataBase;
import entities.Author;
import entities.Birthday;
import service.AuthenticationService;

public class AuthorService extends UserService{


    public void userSignup(String firstName, String lastName, String userName,
                           String password, String nationalCode, Birthday birthday) {
        Author signingAuthor = new Author(firstName, lastName, userName, password, nationalCode, birthday);
        DataBase.userList.add(signingAuthor);
        System.out.println("Author signed up successfully");
    }


    public void changePassword(String oldPassword, String newPassword) {
        //todo: use Database userList unless loggedInUser
        if (AuthenticationService.getLoggedUser().getPassword().equals(oldPassword)) {
            DataBase.loggedInUser.setPassword(newPassword);
            return;
        }
        System.out.println("Wrong password");
    }

}