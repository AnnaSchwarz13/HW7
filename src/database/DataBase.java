package database;

import entities.Author;
import entities.List;
import entities.Moderator;
import entities.User;

public class DataBase {
    public  static final List userList = new List();
    public static Author loggedInAuthor;
    public static User loggedInUser;
    public static final List categoryList = new List();
    public static final List tagList = new List();
    public static final List publishedArticles = new List();
    public static final List articlesToCheckForPublish = new List();


}