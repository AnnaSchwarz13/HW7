import entities.Category;
import entities.Moderator;
import entities.Tag;
import service.Menu.AuthorMenu;
import service.Menu.ModeratorMenu;
import service.UserService;

import java.util.Scanner;

import static database.DataBase.*;

static Scanner scanner = new Scanner(System.in);
static final UserService userService = new UserService();
public static void main() {

    Moderator moderator1 = new Moderator("admin", "admin");
    userList.add(moderator1);
//    Category c1 = new Category("History", "its about Historical events");
//    Category c2 = new Category("Fiction", "its about fictional events");
//    Category c3 = new Category("NoneFiction", "its about non-fictional events");
//    Category c4 = new Category("Novel", "its about stories of novels");
//    Category c5 = new Category("Biography", "its about persons life");
//    Category c6 = new Category("Science", "its about science");

//    categoryList.add(c1);
//    categoryList.add(c2);
//    categoryList.add(c3);
//    categoryList.add(c4);
//    categoryList.add(c5);
//    categoryList.add(c6);


//    Tag t1 = new Tag("first_article");
//    Tag t2 = new Tag("hot_news");
//    Tag t3 = new Tag("Tramp");
//
//    tagList.add(t1);
//    tagList.add(t2);
//    tagList.add(t3);

    System.out.println("Welcome to Articles Site...\n\n");

    int userRole;
    while (true) {
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

