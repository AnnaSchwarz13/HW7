package database;
import entities.List;
import entities.User;

public class DataBase {
    public  static final List userList = new List();
    public static User loggedInUser;
    public static final List categoryList = new List();
    public static final List tagList = new List();
    public static final List publishedArticles = new List();
    public static final List articlesToCheckForPublish = new List();


}