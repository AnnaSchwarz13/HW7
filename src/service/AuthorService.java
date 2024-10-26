package service;

import database.DataBase;
import entities.Author;
import entities.Birthday;
import entities.Lists.List;

public class AuthorService {


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
            Author checkingUser = DataBase.authorList.getUsers(i);
            if (checkingUser.getUsername().equals(username)) {
                if (checkingUser.getPassword().equals(password)) {
                    AuthenticationService.setLoggedAuthor(checkingUser);
                    System.out.println("User logged in successfully...");
                    setPreviousData();
                    return;
                }
                break;
            }
        }
        System.out.println("Username or password is wrong!");
    }


    public void authorLogout() {
        saveUsersArticlesList();
        DataBase.loggedInAuthor = null;
    }

    private void saveUsersArticlesList() {
        DataBase.loggedInAuthor.clearThisUserArticlesList();
        DataBase.loggedInAuthor.setThisUserArticlesList(DataBase.articlesList);
        DataBase.articlesList = new List();
    }

    public void setPreviousData() {
        if (DataBase.loggedInAuthor.getThisUserArticlesList() != null) {
            DataBase.articlesList = DataBase.loggedInAuthor.getThisUserArticlesList();
        }
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