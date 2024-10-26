package database;

import entities.Author;
import entities.List;
import entities.Moderator;

public class DataBase {
    public  static final List userList = new List();
    public static final List authorList = new List();
    public static final List moderatorList = new List();
    //todo: have a single user list and loggedInUser
    public static Moderator moderator;
    public static Author loggedInAuthor;
    public static final List categoryList = new List();
    public static final List tagList = new List();
    public static final List publishedArticles = new List();
    public static final List articlesToCheckForPublish = new List();


}