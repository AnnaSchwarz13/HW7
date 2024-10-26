package database;

import entities.Author;
import entities.Lists.*;
import entities.Moderator;

public class DataBase {
    public static final AuthorList authorList = new AuthorList();
    public static final List moderatorList = new List();
    //todo: have a single user list and loggedInUser
    public static Moderator moderator;
    public static Author loggedInAuthor;
    //todo:don't use following line
    public static List articlesList = new List();
    public static final CategoryList categoryList = new CategoryList();
    public static final TagList tagList = new TagList();
    public static final AuthorArticleList publishedArticles = new AuthorArticleList();
    public static final AuthorArticleList articlesToCheckForPublish = new AuthorArticleList();


}