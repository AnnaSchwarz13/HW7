package service.Menu;

import entities.Article;
import entities.Author;
import entities.Birthday;
import entities.List;
import entities.enums.Role;
import service.ArticleService;
import service.AuthenticationService;
import service.AuthorService;
import service.Filtering;

import java.util.Scanner;

import static database.DataBase.loggedInUser;
import static database.DataBase.publishedArticles;

public class AuthorMenu {
    Scanner scanner = new Scanner(System.in);


    public AuthorMenu() {
        int userInput;
        while (loggedInUser == null) {
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

        if (loggedInUser != null) {
            System.out.println("Good Day Dear " + ((Author) loggedInUser).getFirstName() + "!");
        }
        while (loggedInUser != null) {


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
        ArticleService articleService = new ArticleService();
        if (option == 1) {
            System.out.println("Enter first name:");
            String firstName = scanner.next();
            System.out.println("Enter last name:");
            String lastName = scanner.next();

            while (true) {
                System.out.println("Enter username:");
                String username = scanner.next();
                if (AuthenticationService.isUserNameNew(username,Role.AUTHOR)) {
                    System.out.println("Enter your national code: ");
                    String nationalCode = scanner.next();
                    boolean forGetACorrectBirth = false;
                    while (!forGetACorrectBirth) {
                        System.out.println("Enter your birthday like example:\nexample: 1995-12-3 ");
                        String date = scanner.next();
                        Birthday birthdaySet = new Birthday(date);
                        forGetACorrectBirth = birthdaySet.isBirthdayValid();
                        if (birthdaySet.isBirthdayValid()) {
                            authorService.userSignup(firstName, lastName, username, nationalCode, nationalCode, birthdaySet);
                            break;
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
            authorService.userLogin(username, password, Role.AUTHOR);

        } else if (option == 3) {
            System.out.println("\nWhat do you want to do?");
            System.out.println("1.See all articles without any filter");
            System.out.println("2.Select a filter");
            int filterOption = scanner.nextInt();
            if (filterOption == 1) {
                articleService.showAnArticleList(publishedArticles);
            } else if (filterOption == 2) {
                Filtering filtering = new Filtering();
                System.out.println("\nplease enter one of the following filters: ");
                System.out.println("1.created date");
                System.out.println("2.last updated date");
                System.out.println("3.published date");
                int filter1choose = scanner.nextInt();
                System.out.println("1.last year");
                System.out.println("2.last 6 month");
                System.out.println("3.last month");
                System.out.println("4.last week");
                System.out.println("5.last 24 hour");
                int filter2choose = scanner.nextInt();
                if (filter1choose == 1) {
                    if (filter2choose == 1) {
                        articleService.showAnArticleList(filtering.filterTo1Year(publishedArticles,"created"));
                    } else if (filter2choose == 2) {
                        articleService.showAnArticleList(filtering.filterTo6Month(publishedArticles,"created"));
                    } else if (filter2choose == 3) {
                        articleService.showAnArticleList(filtering.filterTo1Month(publishedArticles,"created"));

                    } else if (filter2choose == 4) {
                        articleService.showAnArticleList(filtering.filterTo1Week(publishedArticles,"created"));
                    } else if (filter2choose == 5) {
                        articleService.showAnArticleList(filtering.filterTo24Hour(publishedArticles,"created"));
                    }
                } else if (filter1choose == 2) {
                    if (filter2choose == 1) {
                        articleService.showAnArticleList(filtering.filterTo1Year(publishedArticles,"lastUpdate"));
                    } else if (filter2choose == 2) {
                        articleService.showAnArticleList(filtering.filterTo6Month(publishedArticles,"lastUpdate"));
                    } else if (filter2choose == 3) {
                        articleService.showAnArticleList(filtering.filterTo1Month(publishedArticles,"lastUpdate"));

                    } else if (filter2choose == 4) {
                        articleService.showAnArticleList(filtering.filterTo1Week(publishedArticles,"lastUpdate"));
                    } else if (filter2choose == 5) {
                        articleService.showAnArticleList(filtering.filterTo24Hour(publishedArticles,"lastUpdate"));
                    }
                } else if (filter1choose == 3) {
                    if (filter2choose == 1) {
                        articleService.showAnArticleList(filtering.filterTo1Year(publishedArticles,"published"));
                    } else if (filter2choose == 2) {
                        articleService.showAnArticleList(filtering.filterTo6Month(publishedArticles,"published"));
                    } else if (filter2choose == 3) {
                        articleService.showAnArticleList(filtering.filterTo1Month(publishedArticles,"published"));

                    } else if (filter2choose == 4) {
                        articleService.showAnArticleList(filtering.filterTo1Week(publishedArticles,"published"));
                    } else if (filter2choose == 5) {
                        articleService.showAnArticleList(filtering.filterTo24Hour(publishedArticles,"published"));
                    }
                }

            }
        }

    }

    public static void articleSiteMenu(int option) {
        Author loggedInAuthor = (Author) loggedInUser;
        List articlesList = loggedInAuthor.getThisUserArticlesList();
        Scanner scanner = new Scanner(System.in);
        ArticleService articleService = new ArticleService();
        AuthorService authorService = new AuthorService();
        if (option == 1) {
            articleService.addArticle();
        } else if (option == 2) {
            articleService.showAnArticleList(articlesList);
        } else if (option == 3) {
            if (articlesList.getIndex() == 0) {
                System.out.println("there is no article");
            } else {
                System.out.println("Please enter the title of the article's list \n for see more details: ");

                for (int i = 0; i < articlesList.getIndex(); ++i) {
                    Article tempArticle = (Article) articlesList.getObjects(i);
                    System.out.println(tempArticle.getTitle());
                }
                String title = scanner.nextLine();
                Article choosenArticle = articleService.findArticleByTitle(title, articlesList);
                System.out.println("What do you want to do?");
                System.out.println("1.Publish or Unpublished the article");
                System.out.println("2.Edit the details");
                int userInput = scanner.nextInt();

                if (userInput == 1) {
                    articleService.changeArticleStatus(choosenArticle);

                } else if (userInput == 2) {
                    articleService.changeDetailsOfArticle(choosenArticle);

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

            System.out.println("Goodbye Dear " + ((Author) loggedInUser).getFirstName() + "!");
            authorService.userLogout();

        }

    }
}

