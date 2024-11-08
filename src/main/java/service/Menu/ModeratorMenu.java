package service.Menu;

import entities.Article;
import entities.enums.Role;
import repository.ArticleRepository;
import repository.Imp.ArticleRepositoryImp;
import service.ArticleService;
import service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static service.UserService.loggedInUser;

public class ModeratorMenu {
    Scanner scanner = new Scanner(System.in);
    UserService userService = new UserService();
    ArticleService articleService = new ArticleService();
    ArticleRepository articleRepository = new ArticleRepositoryImp();
   static List<Article> publishedArticles = ArticleRepositoryImp.allPublished();
   static List<Article> pendingArticles = ArticleRepositoryImp.allPending();

    public ModeratorMenu() throws SQLException {
        if (loggedInUser == null) {
            String username;
            String password;
            System.out.println("Enter username:");
            username = scanner.next();
            System.out.println("Enter password:");
            password = scanner.next();
            userService.userLogin(username, password , Role.MODERATOR);
        }
        while (loggedInUser!=null) {
            if (!pendingArticles.isEmpty()) {
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
            if (!pendingArticles.isEmpty()) {
                while (true) {
                    articleService.showAnArticleList(pendingArticles);
                    System.out.println("If you dont wanna see more please enter -1 else 1");
                    int toEnd = scanner.nextInt();
                    if (toEnd == -1) {
                        break;
                    }
                }
                System.out.println("Enter an article name to remove or get publish :");

                for (Article tempArticle :pendingArticles) {
                    System.out.println(tempArticle.getTitle());
                }

                String title = scanner.nextLine() +scanner.nextLine();
                if (articleService.findArticleByTitle(title, pendingArticles) != null) {
                    Article chosenArticle = articleService.findArticleByTitle(title, pendingArticles);
                    List<Article> articleList = chosenArticle.getAuthor().getThisUserArticlesList();

                    System.out.println("1. Accept and publish");
                    System.out.println("2. Reject and remove");
                    int option1 = scanner.nextInt();
                    if (option1 == 1) {
                        pendingArticles.remove(chosenArticle);
                        publishedArticles.add(chosenArticle);
                        ArticleRepositoryImp.updateStatusPublished(chosenArticle);
                    } else if (option1 == 2) {
                        pendingArticles.remove(chosenArticle);
                       ArticleRepositoryImp.updateStatusNotPublished(chosenArticle);
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
