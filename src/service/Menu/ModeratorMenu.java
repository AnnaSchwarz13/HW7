package service.Menu;

import entities.Article;
import entities.AuthorArticle;
import entities.Lists.ArticleList;
import entities.Lists.List;
import entities.enums.ArticleStatus;
import database.DataBase;
import service.ArticleActions;
import service.ModeratorService;

import java.util.Scanner;

import static database.DataBase.articlesToCheckForPublish;
import static database.DataBase.publishedArticles;

public class ModeratorMenu {
    Scanner scanner = new Scanner(System.in);
    ModeratorService moderatorService = new ModeratorService();
    ArticleActions articleActions = new ArticleActions();

    public ModeratorMenu() {
        if (DataBase.articlesToCheckForPublish.getIndex() > 0) {
            System.out.println("There is new articles to check for publish!!");
        }
        System.out.println("\n\nDear Moderator please choose a option from the menu : ");
        System.out.println("1.See articles submitted for approval.");
        System.out.println("2.logout");
        int option = scanner.nextInt();
        moderatorMenu(option);
    }


    private void moderatorMenu(int option) {
        if (option == 1) {
            if (DataBase.articlesToCheckForPublish.getIndex() > 0) {
                while (true) {
                    articleActions.showArticle(DataBase.articlesToCheckForPublish.getArticlesOfAuthor());
                    System.out.println("If you dont wanna see more please enter -1 else 1");
                    int toEnd = scanner.nextInt();
                    if (toEnd == -1) {
                        break;
                    }
                }
                System.out.println("Enter an article name to remove or get publish :");

                for (int i = 0; i < articlesToCheckForPublish.getIndex(); i++) {
                    Article tempArticle = articlesToCheckForPublish.getArticlesOfAuthor().getArticles(i);
                    System.out.println(tempArticle.getTitle());
                }

                String title = scanner.nextLine() +scanner.nextLine();
                if (articleActions.findAuthorArticleByTitle(title, articlesToCheckForPublish) != null) {
                    AuthorArticle chosenArticle = articleActions.findAuthorArticleByTitle(title, articlesToCheckForPublish);
                    int index = articlesToCheckForPublish.findIndexByAuthorArticle(chosenArticle);
                    List articleList = chosenArticle.getAuthor().getThisUserArticlesList();

                    System.out.println("1. Accept and publish");
                    System.out.println("2. Reject and remove");
                    int option1 = scanner.nextInt();
                    if (option1 == 1) {
                        articlesToCheckForPublish.remove(index);
                        publishedArticles.add(chosenArticle);
                        articleActions.findArticleByTitle(title, articleList).setPublished(true);
                        articleActions.findArticleByTitle(title, articleList).setPublishDate(articleActions.todaysDateAsString());
                        articleActions.findArticleByTitle(title, articleList).setStatus(ArticleStatus.PUBLISHED);
                    } else if (option1 == 2) {
                        articlesToCheckForPublish.remove(index);
                        articleActions.findArticleByTitle(title, articleList).setPublished(false);
                        articleActions.findArticleByTitle(title, articleList).setStatus(ArticleStatus.NOT_PUBLISHED);
                    }
                }
            } else {
                System.out.println("There is no new articles to check for publish");
            }
        } else if (option == 2) {
            moderatorService.moderatorLogout();
        }
    }
}
