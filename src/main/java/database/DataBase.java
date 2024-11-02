package database;
import entities.*;
import entities.enums.ArticleStatus;

public class DataBase {
    public  static final List userList = new List();
    public static User loggedInUser;
    public static final List categoryList = new List();
    public static final List tagList = new List();
    public static final List publishedArticles = new List();
    public static final List articlesToCheckForPublish = new List();

    public DataBase() {
        Moderator moderator1 = new Moderator("admin", "admin");
        userList.add(moderator1);

        Category c1 = new Category("History", "its about Historical events");
        Category c2 = new Category("Fiction", "its about fictional events");
        Category c3 = new Category("NoneFiction", "its about non-fictional events");
        Category c4 = new Category("Novel", "its about stories of novels");
        Category c5 = new Category("Biography", "its about persons life");
        Category c6 = new Category("Science", "its about science");

        categoryList.add(c1);
        categoryList.add(c2);
        categoryList.add(c3);
        categoryList.add(c4);
        categoryList.add(c5);
        categoryList.add(c6);

        Tag t1 = new Tag("first_article");
        Tag t2 = new Tag("hot_news");
        Tag t3 = new Tag("Tramp");

        tagList.add(t1);
        tagList.add(t2);
        tagList.add(t3);

        Birthday birthday1 = new Birthday("2000-02-02");
        Author author1 = new Author("anna", "schwarz", "a", "a", "a", birthday1);
        userList.add(author1);

        Article article1 = new Article("first",c1,"1",1,tagList,
                "2024-05-02",true,"2023-11-02", ArticleStatus.PUBLISHED,author1);
        Article article2 = new Article("sec",c2,"1",1,tagList,
                "2020-09-30",true,"2024-02-02", ArticleStatus.PUBLISHED,author1);
        Article article3 = new Article("thi",c3,"1",1,tagList,
                "2024-10-22",true,"2024-10-26", ArticleStatus.PUBLISHED,author1);

        article1.setPublishDate("2024-05-02");
        article2.setPublishDate("2024-09-30");
        article3.setPublishDate("2023-10-30");

        publishedArticles.add(article1);
        publishedArticles.add(article2);
        publishedArticles.add(article3);

    }

    }