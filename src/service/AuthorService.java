package service;

import database.DataBase;
import entities.Author;
import entities.Birthday;
import entities.List;

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
        DataBase.authorList.add(signingAuthor);
        System.out.println("Author signed up successfully");
    }

    public void authorLogin(String username, String password) {
        for (int i = 0; i < DataBase.authorList.getIndex(); i++) {
            Author checkingUser = (Author) DataBase.authorList.getObjects(i);
            if (checkingUser.getUsername().equals(username)) {
                if (checkingUser.getPassword().equals(password)) {
                    AuthenticationService.setLoggedAuthor(checkingUser);
                    System.out.println("User logged in successfully...");
                    return;
                }
                break;
            }
        }
        System.out.println("Username or password is wrong!");
    }


    public void authorLogout() {

        DataBase.loggedInAuthor = null;
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