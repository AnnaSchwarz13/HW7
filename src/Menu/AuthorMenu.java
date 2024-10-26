package Menu;

import Entities.Article;
import Entities.Birthday;
import service.ArticleActions;
import service.AuthenticationService;
import service.AuthorService;

import java.util.Scanner;

import static database.DataBase.*;

public class AuthorMenu {

    Scanner scanner = new Scanner(System.in);

    public AuthorMenu() {
        int userInput;
        while (loggedInAuthor == null) {
            System.out.println("\n\nDear user please choose a option from the menu : ");
            System.out.println("1.Signup");
            System.out.println("2.login");
            System.out.println("3.See published articles");
            System.out.println("enter -1 to back previous menu");
            userInput = scanner.nextInt();
            if (userInput == -1) {
                break;
            }
            userLoginMenu(userInput);
        }

        if (loggedInAuthor != null) {
            System.out.println("Good Day Dear " + loggedInAuthor.getFirstName() + "!");
        }
        while (loggedInAuthor != null) {


            System.out.println("\nWhat do you want to do?");
            System.out.println("1.Creat a new article");
            System.out.println("2.See all of your articles");
            System.out.println("3.Edit an existing article");
            System.out.println("4.Change password");
            System.out.println("5.Log out");
            userInput = scanner.nextInt();
            articleSiteMenu(userInput);
        }

    }

    public static void userLoginMenu(int option) {
        Scanner scanner = new Scanner(System.in);
        AuthorService authorService = new AuthorService();
        ArticleActions articleActions = new ArticleActions();
        if (option == 1) {
            System.out.println("Enter first name:");
            String firstName = scanner.next();
            System.out.println("Enter last name:");
            String lastName = scanner.next();

            while (true) {
                System.out.println("Enter username:");
                String username = scanner.next();
                if (AuthenticationService.isUserNameNew(username)) {
                    System.out.println("Enter your national code: ");
                    String nationalCode = scanner.next();
                    while (true) {
                        System.out.println("Enter your birthday like example:\nexample: 1995/12/3 ");
                        String birthday = scanner.next();
                        Birthday birthdaySet = new Birthday(birthday);
                        if (birthdaySet.getYear() != 0) {
                            int toSetBirthday = scanner.nextInt();
                            if (toSetBirthday == 1) {
                                authorService.userSignup(firstName, lastName, username, nationalCode, nationalCode, birthdaySet);
                                break;
                            }
                        }
                    }
                    break;
                }
                System.out.println("this username are already taken!");
            }
        } else if (option == 2) {
            String username;
            String password;
            System.out.println("Enter username:");
            username = scanner.next();
            System.out.println("Enter password:");
            password = scanner.next();
            authorService.authorLogin(username, password);

        } else if (option == 3) {
            articleActions.showArticle(publishedArticles.getArticlesOfAuthor());
        }

    }

    public static void articleSiteMenu(int option) {
        Scanner scanner = new Scanner(System.in);
        ArticleActions articleActions = new ArticleActions();
        AuthorService authorService = new AuthorService();
        if (option == 1) {
            articleActions.addArticle();
        } else if (option == 2) {
            articleActions.showArticle(articlesList);
        } else if (option == 3) {
            if (articlesList.getIndex() == 0) {
                System.out.println("there is no article");
            } else {
                System.out.println("Please enter the title of the article's list \n for see more details: ");

                for (int i = 0; i < articlesList.getIndex(); ++i) {
                    Article tempArticle = articlesList.getArticles(i);
                    System.out.println(tempArticle.getTitle());
                }
                String title = scanner.nextLine();
                Article choosenArticle = articleActions.findArticleByTitle(title, articlesList);
                System.out.println("What do you want to do?");
                System.out.println("1.Publish or Unpublished the article");
                System.out.println("2.Edit the details");
                int userInput = scanner.nextInt();

                if (userInput == 1) {
                    articleActions.changeArticleStatus(choosenArticle);

                } else if (userInput == 2) {
                    articleActions.changeDetailsOfArticle(choosenArticle);

                }
            }
        } else if (option == 4) {
            String oldPassword;
            String newPassword;
            System.out.println("Enter old password:");
            oldPassword = scanner.nextLine();
            System.out.println("Enter new password:");
            newPassword = scanner.nextLine();
            authorService.changePassword(oldPassword, newPassword);

        } else if (option == 5) {

            System.out.println("Goodbye Dear " + loggedInAuthor.getFirstName() + "!");
            authorService.authorLogout();

        }

    }
}

