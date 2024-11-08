
import service.Menu.AuthorMenu;
import service.Menu.ModeratorMenu;

import java.sql.SQLException;
import java.util.Scanner;

public static void main() throws SQLException {

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

