import entities.Moderator;
import service.Menu.AuthorMenu;
import service.Menu.ModeratorMenu;

import java.util.Scanner;

import static database.DataBase.userList;

static Scanner scanner = new Scanner(System.in);
public static void main() {

    Moderator moderator1 = new Moderator("admin", "admin");
    userList.add(moderator1);



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

