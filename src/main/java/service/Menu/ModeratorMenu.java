package service.Menu;

import entities.Article;
import entities.enums.ArticleStatus;
import entities.enums.Role;
import repository.Imp.ArticleRepositoryImp;
import service.ArticleService;
import service.UserService;

import java.util.List;
import java.util.Scanner;

import static service.DateService.todaysDateAsString;
import static service.UserService.loggedInUser;

public class ModeratorMenu {
    Scanner scanner = new Scanner(System.in);
    UserService userService = new UserService();
    ArticleService articleService = new ArticleService();
   static List<Article> publishedArticles = ArticleRepositoryImp.allPublished();
   static List<Article> pendingArticles = ArticleRepositoryImp.allPending();

    public ModeratorMenu() {
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
            if (pendingArticles.getIndex() > 0) {
                System.out.println("There is new articles to check for publish!!");
            }
            System.out.println("\n\nDear Moderator please choose a option from the menu : ");
            System.out.println("1.See articles submitted for approval.");
            System.out.println("2.logout");
            int option = scanner.nextInt();
            moderatorMenu(option);
        }
    }


    private void moderatorMenu(int option) {
        if (option == 1) {
            if (pendingArticles.getIndex() > 0) {
                while (true) {
                    articleService.showAnArticleList(pendingArticles);
                    System.out.println("If you dont wanna see more please enter -1 else 1");
                    int toEnd = scanner.nextInt();
                    if (toEnd == -1) {
                        break;
                    }
                }
                System.out.println("Enter an article name to remove or get publish :");

                for (int i = 0; i < pendingArticles.getIndex(); i++) {
                    Article tempArticle =(Article)pendingArticles.getObjects(i);
                    System.out.println(tempArticle.getTitle());
                }

                String title = scanner.nextLine() +scanner.nextLine();
                if (articleService.findArticleByTitle(title, pendingArticles) != null) {
                    Article chosenArticle = articleService.findArticleByTitle(title, pendingArticles);
                    int index = pendingArticles.getIndexOfObject(chosenArticle);
                    List articleList = chosenArticle.getAuthor().getThisUserArticlesList();

                    System.out.println("1. Accept and publish");
                    System.out.println("2. Reject and remove");
                    int option1 = scanner.nextInt();
                    if (option1 == 1) {
                        pendingArticles.removeObject(index);
                        publishedArticles.add(chosenArticle);
                        articleService.findArticleByTitle(title, articleList).setPublished(true);
                        articleService.findArticleByTitle(title, articleList).setPublishDate(todaysDateAsString());
                        articleService.findArticleByTitle(title, articleList).setStatus(ArticleStatus.PUBLISHED);
                    } else if (option1 == 2) {
                        pendingArticles.removeObject(index);
                        articleService.findArticleByTitle(title, articleList).setPublished(false);
                        articleService.findArticleByTitle(title, articleList).setStatus(ArticleStatus.NOT_PUBLISHED);
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
