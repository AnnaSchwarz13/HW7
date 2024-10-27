import entities.*;
import entities.enums.ArticleStatus;
import service.Menu.AuthorMenu;
import service.Menu.ModeratorMenu;

import java.util.Scanner;

import static database.DataBase.*;

public static void main(String[] args) {

    Moderator moderator1 = new Moderator("admin", "admin");
    userList.add(moderator1);
    Birthday birthday1 = new Birthday("2000-02-02");
    Author author1 = new Author("anna", "schwarz", "a", "a", "a", birthday1);

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

    Article article1 = new Article("first",c1,"1",1,tagList,
            "2024-05-02",true,"2023-11-02", ArticleStatus.PUBLISHED);
    Article article2 = new Article("sec",c2,"1",1,tagList,
            "2024-09-30",true,"2024-02-02", ArticleStatus.PUBLISHED);
    Article article3 = new Article("thi",c3,"1",1,tagList,
            "2024-10-22",true,"2024-10-26", ArticleStatus.PUBLISHED);

    article1.setPublishDate("2024-05-02");
    article2.setPublishDate("2024-09-30");
    article3.setPublishDate("2023-10-30");

    AuthorArticle authorArticle1= new AuthorArticle(article1,author1);
    AuthorArticle authorArticle2= new AuthorArticle(article2,author1);
    AuthorArticle authorArticle3= new AuthorArticle(article3,author1);
    publishedArticles.add(authorArticle3);
    publishedArticles.add(authorArticle2);
    publishedArticles.add(authorArticle1);
    System.out.println("Welcome to Articles Site...\n\n");

    int userRole;
    while (true) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose your role: ");
        System.out.println("1. Moderator");
        System.out.println("2. User");
        userRole = scanner.nextInt();

        if (userRole == 1) {
            ModeratorMenu moderatorMenu = new ModeratorMenu();
        } else if (userRole == 2) {
            AuthorMenu authorMenu = new AuthorMenu();
        }

    }

}

