package Menu;

import entities.Article;
import entities.enums.ArticleStatus;
import entities.enums.Role;
import repository.ArticleRepository;
import repository.Imp.ArticleRepositoryImp;
import service.Imp.ArticleServiceImp;
import service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

import static service.AuthenticationService.loggedInUser;

public class ModeratorMenu {
    Scanner scanner = new Scanner(System.in);
    UserService userService = new UserService();
    ArticleServiceImp articleServiceImp = new ArticleServiceImp();
    ArticleRepository articleRepositoryImp = new ArticleRepositoryImp();

    public ModeratorMenu() throws SQLException {
        if (loggedInUser == null) {
            String username;
            String password;
            System.out.println("Enter username:");
            username = scanner.next();
            System.out.println("Enter password:");
            password = scanner.next();
            userService.userLogin(username, password, Role.MODERATOR);
        }
        while (loggedInUser != null) {
            if (!articleRepositoryImp.allPending().isEmpty()) {
                System.out.println("There is new articles to check for publish!!");
            }
            System.out.println("\n\nDear Moderator please choose a option from the menu : ");
            System.out.println("1.See articles submitted for approval.");
            System.out.println("2.logout");
            int option = scanner.nextInt();
            moderatorMenu(option);
        }
    }


    private void moderatorMenu(int option) throws SQLException {
        if (option == 1) {
            if (!articleRepositoryImp.allPending().isEmpty()) {
                while (true) {
                    articleServiceImp.showAnArticleList(articleRepositoryImp.allPending());
                    System.out.println("If you dont wanna see more please enter -1 else 1");
                    int toEnd = scanner.nextInt();
                    if (toEnd == -1) {
                        break;
                    }
                }
                System.out.println("Enter an article name to remove or get publish :");

                for (Article tempArticle : articleRepositoryImp.allPending()) {
                    System.out.println(tempArticle.getTitle());
                }

                String title = scanner.nextLine() + scanner.nextLine();
                if (articleRepositoryImp.findArticleByTile(title) != null
                        && articleRepositoryImp.findArticleByTile(title).getStatus() == ArticleStatus.PENDING) {
                    Article chosenArticle = articleRepositoryImp.findArticleByTile(title);

                    System.out.println("1. Accept and publish");
                    System.out.println("2. Reject and remove");
                    int option1 = scanner.nextInt();
                    if (option1 == 1) {
                        articleRepositoryImp.updateStatusPublished(chosenArticle);
                    } else if (option1 == 2) {
                        articleRepositoryImp.updateStatusNotPublished(chosenArticle);
                    }
                }
            } else {
                System.out.println("There is no new articles to check for publish");
            }
        } else if (option == 2) {
            userService.userLogout();
        }
    }
}
