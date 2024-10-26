package service.Menu;

import database.DataBase;
import entities.Article;
import entities.AuthorArticle;
import entities.List;
import entities.enums.ArticleStatus;
import entities.enums.Role;
import service.ArticleService;
import service.UserService;

import java.util.Scanner;

import static database.DataBase.*;

public class ModeratorMenu {
    Scanner scanner = new Scanner(System.in);
    UserService userService = new UserService();
    ArticleService articleService = new ArticleService();

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
            if (DataBase.articlesToCheckForPublish.getIndex() > 0) {
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
            if (DataBase.articlesToCheckForPublish.getIndex() > 0) {
                while (true) {
                    articleService.showArticle(DataBase.articlesToCheckForPublish);
                    System.out.println("If you dont wanna see more please enter -1 else 1");
                    int toEnd = scanner.nextInt();
                    if (toEnd == -1) {
                        break;
                    }
                }
                System.out.println("Enter an article name to remove or get publish :");

                for (int i = 0; i < articlesToCheckForPublish.getIndex(); i++) {
                    Article tempArticle =((AuthorArticle)articlesToCheckForPublish.getObjects(i)).getArticle();
                    System.out.println(tempArticle.getTitle());
                }

                String title = scanner.nextLine() +scanner.nextLine();
                if (articleService.findAuthorArticleByTitle(title, articlesToCheckForPublish) != null) {
                    AuthorArticle chosenArticle = articleService.findAuthorArticleByTitle(title, articlesToCheckForPublish);
                    int index = articlesToCheckForPublish.getIndexOfObject(chosenArticle);
                    List articleList = chosenArticle.getAuthor().getThisUserArticlesList();

                    System.out.println("1. Accept and publish");
                    System.out.println("2. Reject and remove");
                    int option1 = scanner.nextInt();
                    if (option1 == 1) {
                        articlesToCheckForPublish.removeObject(index);
                        publishedArticles.add(chosenArticle);
                        articleService.findArticleByTitle(title, articleList).setPublished(true);
                        articleService.findArticleByTitle(title, articleList).setPublishDate(articleService.todaysDateAsString());
                        articleService.findArticleByTitle(title, articleList).setStatus(ArticleStatus.PUBLISHED);
                    } else if (option1 == 2) {
                        articlesToCheckForPublish.removeObject(index);
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
