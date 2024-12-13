package Menu;

import entities.Article;
import entities.Author;
import entities.enums.Role;
import repository.Imp.ArticleRepositoryImp;
import repository.Imp.AuthorRepositoryImp;
import service.Filtering;
import service.Imp.ArticleServiceImp;
import service.Imp.AuthenticationServiceImp;
import service.Imp.AuthorServiceImp;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class AuthorMenu {
    static ArticleRepositoryImp articleRepositoryImp = new ArticleRepositoryImp();
    static AuthorRepositoryImp authorRepositoryImp = new AuthorRepositoryImp();
    static AuthenticationServiceImp authenticationServiceImp = new AuthenticationServiceImp();
    Scanner scanner = new Scanner(System.in);

    public AuthorMenu() throws SQLException {
        int userInput;
        while (authenticationServiceImp.getLoggedUser() == null) {
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

        if (authenticationServiceImp.getLoggedUser()!= null) {
            System.out.println("Good Day Dear " + (authenticationServiceImp.getLoggedUser()).getUsername() + "!");
        }
        while (authenticationServiceImp.getLoggedUser() != null) {


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


    public static void userLoginMenu(int option) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        AuthorServiceImp authorServiceImp = new AuthorServiceImp();
        ArticleServiceImp articleServiceImp = new ArticleServiceImp();
        if (option == 1) {
            System.out.println("Enter first name:");
            String firstName = scanner.next();
            System.out.println("Enter last name:");
            String lastName = scanner.next();

            while (true) {
                System.out.println("Enter username:");
                String username = scanner.next();
                if (authenticationServiceImp.isUserNameNew(username, Role.AUTHOR)) {
                    System.out.println("Enter your national code: ");
                    String nationalCode = scanner.next();
                    while (true) {
                        System.out.println("Enter your birthday like example:\nexample: 1995-12-3 ");
                        Date date = Date.valueOf(scanner.next());
                        if (date.before(Date.valueOf(LocalDate.now()))) {
                            authorServiceImp.userSignup(firstName, lastName, username, nationalCode, nationalCode, date);
                            System.out.println("You have successfully singUp!");
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
            authorServiceImp.userLogin(username, password, Role.AUTHOR);

        } else if (option == 3) {
            System.out.println("\nWhat do you want to do?");
            System.out.println("1.See all articles without any filter");
            System.out.println("2.Select a filter");
            int filterOption = scanner.nextInt();
            if (filterOption == 1) {
                articleServiceImp.showAnArticleList(articleRepositoryImp.allPublished());
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
                        articleServiceImp.showAnArticleList(filtering.filter(articleRepositoryImp.allPublished(), "created", 365));
                    } else if (filter2choose == 2) {
                        articleServiceImp.showAnArticleList(filtering.filter(articleRepositoryImp.allPublished(), "created", 180));
                    } else if (filter2choose == 3) {
                        articleServiceImp.showAnArticleList(filtering.filter(articleRepositoryImp.allPublished(), "created", 30));

                    } else if (filter2choose == 4) {
                        articleServiceImp.showAnArticleList(filtering.filter(articleRepositoryImp.allPublished(), "created", 7));
                    } else if (filter2choose == 5) {
                        articleServiceImp.showAnArticleList(filtering.filter(articleRepositoryImp.allPublished(), "created", 1));
                    }
                } else if (filter1choose == 2) {
                    if (filter2choose == 1) {
                        articleServiceImp.showAnArticleList(filtering.filter(articleRepositoryImp.allPublished(), "lastUpdate", 365));
                    } else if (filter2choose == 2) {
                        articleServiceImp.showAnArticleList(filtering.filter(articleRepositoryImp.allPublished(), "lastUpdate", 180));
                    } else if (filter2choose == 3) {
                        articleServiceImp.showAnArticleList(filtering.filter(articleRepositoryImp.allPublished(), "lastUpdate", 30));

                    } else if (filter2choose == 4) {
                        articleServiceImp.showAnArticleList(filtering.filter(articleRepositoryImp.allPublished(), "lastUpdate", 7));
                    } else if (filter2choose == 5) {
                        articleServiceImp.showAnArticleList(filtering.filter(articleRepositoryImp.allPublished(), "lastUpdate", 1));
                    }
                } else if (filter1choose == 3) {
                    if (filter2choose == 1) {
                        articleServiceImp.showAnArticleList(filtering.filter(articleRepositoryImp.allPublished(), "published", 365));
                    } else if (filter2choose == 2) {
                        articleServiceImp.showAnArticleList(filtering.filter(articleRepositoryImp.allPublished(), "published", 180));
                    } else if (filter2choose == 3) {
                        articleServiceImp.showAnArticleList(filtering.filter(articleRepositoryImp.allPublished(), "published", 30));

                    } else if (filter2choose == 4) {
                        articleServiceImp.showAnArticleList(filtering.filter(articleRepositoryImp.allPublished(), "published", 7));
                    } else if (filter2choose == 5) {
                        articleServiceImp.showAnArticleList(filtering.filter(articleRepositoryImp.allPublished(), "published", 1));
                    }
                }

            }
        }

    }

    public static void articleSiteMenu(int option) throws SQLException {
        Author loggedInAuthor = authorRepositoryImp.findByUserId(authenticationServiceImp.getLoggedUser().getId());
        Scanner scanner = new Scanner(System.in);
        ArticleServiceImp articleServiceImp = new ArticleServiceImp();
        AuthorServiceImp authorServiceImp = new AuthorServiceImp();
        if (option == 1) {
            articleServiceImp.addArticle();
        } else if (option == 2) {
            articleServiceImp.showAnArticleList(articleRepositoryImp.getArticlesOfAnAuthor(loggedInAuthor));
        } else if (option == 3) {
            if (articleRepositoryImp.getArticlesOfAnAuthor(loggedInAuthor).isEmpty()) {
                System.out.println("there is no article");
            } else {
                System.out.println("Please enter the title of the article's list \n for see more details: ");

                for (Article tempArticle : articleRepositoryImp.getArticlesOfAnAuthor(loggedInAuthor)) {
                    System.out.println(tempArticle.getTitle());
                }
                String title = scanner.nextLine();
                Article choosenArticle = articleRepositoryImp.findArticleByTile(title);
                if (choosenArticle != null) {
                    System.out.println("What do you want to do?");
                    System.out.println("1.Publish or Unpublished the article");
                    System.out.println("2.Edit the details");
                    int userInput = scanner.nextInt();

                    if (userInput == 1) {
                        articleServiceImp.changeArticleStatus(choosenArticle);

                    } else if (userInput == 2) {
                        articleServiceImp.changeDetailsOfArticle(choosenArticle);

                    }
                }
            }
        } else if (option == 4) {
            String oldPassword;
            String newPassword;
            System.out.println("Enter old password:");
            oldPassword = scanner.nextLine();
            System.out.println("Enter new password:");
            newPassword = scanner.nextLine();
            authorServiceImp.changePassword(oldPassword, newPassword);

        } else if (option == 5) {

            System.out.println("Goodbye Dear " + (authenticationServiceImp.getLoggedUser()).getUsername() + "!");
            authorServiceImp.userLogout();

        }

    }
}

