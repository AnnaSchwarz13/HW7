package service;

import entities.Author;
import entities.Moderator;
import entities.User;
import database.DataBase;

public class AuthenticationService {
    public AuthenticationService() {
    }

    public static void setLoggedModerator(Moderator moderator) {
            DataBase.moderator = moderator;
    }
    public static void setLoggedAuthor(Author author) {
            DataBase.loggedInAuthor = author;
    }

    public static boolean isUserNameNew(String username) {
        for(int i = 0; i< DataBase.authorList.getIndex(); i++) {
            User checkingAuthor =(User) DataBase.authorList.getObjects(i);
            if (checkingAuthor.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

}
