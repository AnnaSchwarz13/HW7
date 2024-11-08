package service;
import entities.*;
import entities.enums.ArticleStatus;
import repository.Imp.ArticleRepositoryImp;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static service.DateService.todaysDateAsString;


public class ArticleService {
    CategoryService categoryService = new CategoryService();
    private String title;
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();
    Author loggedInAuthor = (Author) UserService.loggedInUser;
    List<Article> publishedArticles = ArticleRepositoryImp.allPublished();
    List<Article> pendingArticles = ArticleRepositoryImp.allPending();

    public void addArticle() throws SQLException {
        Category articleCategory = categoryService.chooseCategory();
        System.out.println("Enter title: ");
        this.title = sc.nextLine();
        System.out.println("Enter article text: ");
        String articleText = sc.nextLine();
        List brief = setArticleTags();
        String date = todaysDateAsString();
        Article article = new Article();

        (loggedInAuthor.getThisUserArticlesList()).add(article);
    }

    public void showAnArticleList(List articles) {
        if (articles.getIndex() == 0) {
            System.out.println("there is no article");
        } else {
            System.out.println("Please enter the title of the article's list \n for see more details: ");

            for (int i = 0; i < articles.getIndex(); ++i) {
                Article tempArticle = (Article) articles.getObjects(i);

                System.out.println(tempArticle.getTitle());
            }

            this.title = this.sc.nextLine();
            Article userChoice = this.findArticleByTitle(this.title, articles);
            if (userChoice != null) {
                System.out.println(userChoice);
                System.out.print("Author: ");
                System.out.println(userChoice.getAuthor().getFirstName() + "  " + userChoice.getAuthor().getLastName());
                if (userChoice.isPublished()) {
                    System.out.println("publishedDate: " + userChoice.getPublishDate());

                }
            }
        }

    }

    public Article findArticleByTitle(String title, List articles) {
        for (int i = 0; i < articles.getIndex(); ++i) {
            Article tempArticle = (Article) articles.getObjects(i);

            if (tempArticle.getTitle().equals(title)) {
                return tempArticle;
            }
        }
        System.out.println("That article does not exist");
        return null;
    }


    public void changeArticleStatus(Article choosenArticle) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("The selected article status is " + choosenArticle.getStatus());
        System.out.println("If you dont want to change the status of this article, please enter -1");
        System.out.println("Please enter 1 to following change: ");
        int choose;
        if (choosenArticle.getStatus() == ArticleStatus.NOT_PUBLISHED) {
            System.out.println("Send request to get published article");
            choose = scanner.nextInt();
            if (choose == 1) {
                choosenArticle.setStatus(ArticleStatus.PENDING);
                pendingArticles.add(choosenArticle);
                choosenArticle.setLastUpdateDate(todaysDateAsString());
            }
        } else if (choosenArticle.getStatus() == ArticleStatus.PUBLISHED) {
            Article ExsistedAuthorArticle = findArticleByTitle(choosenArticle.getTitle(), publishedArticles);
            System.out.println("Remove article from published articles");
            choose = scanner.nextInt();
            if (choose == 1) {
                choosenArticle.setStatus(ArticleStatus.NOT_PUBLISHED);
                publishedArticles.removeObject(index);
                choosenArticle.setLastUpdateDate(todaysDateAsString());
                choosenArticle.setPublished(false);
            }
        } else if (choosenArticle.getStatus() == ArticleStatus.PENDING) {
            Article ExsistedAuthorArticle = findArticleByTitle(choosenArticle.getTitle(), pendingArticles);

            System.out.println("Cancel request to get published articles");
            choose = scanner.nextInt();
            if (choose == 1) {
                choosenArticle.setStatus(ArticleStatus.NOT_PUBLISHED);
                pendingArticles.removeObject(index);
                choosenArticle.setLastUpdateDate(todaysDateAsString());
            }
        }
        System.out.println("The selected article status is " + choosenArticle.getStatus());
    }

    public void changeDetailsOfArticle(Article choosenArticle) throws SQLException {
        System.out.println("Which do you want to edit?");
        System.out.println("""
                        1.Edit title
                        2.Edit category
                        3.Edit content
                        4.Edit TagList\s
                """
        );
        int choose = sc.nextInt();
        if (choose == 1) {
            System.out.println("Please enter the new title:");
            String newTitle = sc.nextLine() + sc.nextLine();
            choosenArticle.setTitle(newTitle);
            choosenArticle.setLastUpdateDate(todaysDateAsString());
        } else if (choose == 2) {
            Category newCategory = categoryService.chooseCategory();
            choosenArticle.setCategory(newCategory);
            choosenArticle.setLastUpdateDate(todaysDateAsString());
        } else if (choose == 3) {
            System.out.println("Please enter the new content:");
            String newText = sc.nextLine() + sc.nextLine();
            choosenArticle.setContent(newText);
            choosenArticle.setLastUpdateDate(todaysDateAsString());
        } else if (choose == 4) {
            System.out.println("Your articles tag are there");
            for (int i = 0; i < choosenArticle.getBrief().getIndex(); i++) {
                System.out.println(((Tag) choosenArticle.getBrief().getObjects(i)).getTitle());
            }
            System.out.println("for add more enter 1 \n for remove one enter -1");
            int choose2 = sc.nextInt();
            if (choose2 == 1) {
                List newTagsToAdd = setArticleTags();
                for (int i = 0; i < newTagsToAdd.getIndex(); i++) {
                    choosenArticle.getBrief().add(newTagsToAdd.getObjects(i));
                    choosenArticle.setLastUpdateDate(todaysDateAsString());
                }
            }
            if (choose2 == -1) {
                System.out.println("Please enter a tag name to remove");
                String tagName = sc.nextLine() + sc.nextLine();
                if (findTagByTitle(tagName) == null) {
                    System.out.println("That tag does not exist");
                } else {
                    int index = choosenArticle.getBrief().getIndex();
                    choosenArticle.getBrief().removeObject(index);
                    choosenArticle.setLastUpdateDate(todaysDateAsString());
                }

            }
        }

    }

}
