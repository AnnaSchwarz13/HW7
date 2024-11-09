package service;


import entities.Article;
import entities.Category;
import entities.Tag;
import entities.enums.ArticleStatus;
import repository.Imp.ArticleRepositoryImp;
import repository.Imp.AuthorRepositoryImp;
import repository.Imp.TagRepositoryImp;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static service.AuthenticationService.loggedInUser;
import static service.DateService.todaysDateAsString;


public class ArticleService {
    CategoryService categoryService = new CategoryService();
    ArticleRepositoryImp articleRepository = new ArticleRepositoryImp();
    TagService tagService = new TagService();
    Scanner sc = new Scanner(System.in);

    public ArticleService() {
    }

    public void addArticle() throws SQLException {
        Category articleCategory = categoryService.chooseCategory();
        System.out.println("Enter title: ");
        String title = sc.nextLine();
        System.out.println("Enter article text: ");
        String articleText = sc.nextLine();
        List<Tag> brief = tagService.setArticleTags();
        String date = todaysDateAsString();
        Article article = new Article(AuthorRepositoryImp.findByUserId(loggedInUser.getId()), title, articleCategory, articleText);
        article = articleRepository.create(article);
        System.out.println(article.getId());
        TagRepositoryImp.setArticlesTag(brief, article);
    }

    public void showAnArticleList(List<Article> articles) throws SQLException {
        if (articles.isEmpty()) {
            System.out.println("there is no article");
        } else {
            System.out.println("Please enter the title of the article's list \n for see more details: ");

            for (Article tempArticle : articles) {
                System.out.println(tempArticle.getTitle());
            }

            String title = this.sc.nextLine();
            Article userChoice = ArticleRepositoryImp.findArticleByTile(title);
            if (userChoice != null) {
                displayArticle(userChoice);
            }
        }

    }

    public void changeArticleStatus(Article choosenArticle) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("The selected article status is " + choosenArticle.getStatus());
        System.out.println("If you dont want to change the status of this article, please enter -1");
        System.out.println("Please enter 1 to following change: ");
        int choose;
        if (choosenArticle.getStatus() == ArticleStatus.NOT_PUBLISHED) {
            System.out.println("Send request to get published article");
            choose = scanner.nextInt();
            if (choose == 1) {
                ArticleRepositoryImp.updateStatusPending(choosenArticle);
            }
        } else if (choosenArticle.getStatus() == ArticleStatus.PUBLISHED) {
            Article ExsistedAuthorArticle = ArticleRepositoryImp.findArticleByTile(choosenArticle.getTitle());
            System.out.println("Remove article from published articles");
            choose = scanner.nextInt();
            if (choose == 1) {
                ArticleRepositoryImp.updateStatusNotPublished(choosenArticle);
            }
        } else if (choosenArticle.getStatus() == ArticleStatus.PENDING) {
            Article ExsistedAuthorArticle = ArticleRepositoryImp.findArticleByTile(choosenArticle.getTitle());

            System.out.println("Cancel request to get published articles");
            choose = scanner.nextInt();
            if (choose == 1) {
                ArticleRepositoryImp.updateStatusNotPublished(choosenArticle);
            }
        }
        System.out.println("The selected article status is " + ArticleRepositoryImp.read(choosenArticle.getId()).getStatus());
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
            ArticleRepositoryImp.updateTitle(choosenArticle, newTitle);
            System.out.println("successful!");

        } else if (choose == 2) {
            Category newCategory = categoryService.chooseCategory();
            ArticleRepositoryImp.updateCategory(choosenArticle, newCategory);
            System.out.println("successful!");

        } else if (choose == 3) {
            System.out.println("Please enter the new content:");
            String newText = sc.nextLine() + sc.nextLine();
            ArticleRepositoryImp.updateText(choosenArticle, newText);
            System.out.println("successful!");

        } else if (choose == 4) {
            TagRepositoryImp tagRepositoryImp = new TagRepositoryImp();
            System.out.println("Your articles tag are there");
            for (Tag tag : choosenArticle.getBrief()) {
                System.out.println(tag.getTitle());
            }
            List<Tag> newTags = choosenArticle.getBrief();
            while (true) {
                System.out.println("for add more enter 1 \n remove one tag enter 2 \n and at the end -1");
                int choose2 = sc.nextInt();
                if (choose2 == 1) {
                    List<Tag> newTagsToAdd = tagService.setArticleTags();
                    newTags.addAll(newTagsToAdd);
                }
                if (choose2 == 2) {
                    System.out.println("Please enter a tag name to remove");
                    String tagName = sc.nextLine() + sc.nextLine();
                    if (TagRepositoryImp.findTagByTile(tagName) == null) {
                        System.out.println("That tag does not exist");
                    } else {
                        newTags.remove(TagRepositoryImp.findTagByTile(tagName));
                    }
                }
                if (choose2 == -1) {
                    tagRepositoryImp.delete(choosenArticle.getId());
                    TagRepositoryImp.setArticlesTag(newTags, choosenArticle);
                    ArticleRepositoryImp.setLastUpdateDate(choosenArticle);
                    System.out.println("Tag list updated successfully");
                    break;
                }
            }
        }

    }

    public void displayArticle(Article choosenArticle) {
        System.out.println(choosenArticle.getTitle());
        System.out.println("category : " + choosenArticle.getCategory().getTitle());
        System.out.println("Status : " + choosenArticle.getStatus());
        System.out.println("created date : " + choosenArticle.getCreateDate());
        System.out.println("last update date : " + choosenArticle.getLastUpdateDate());
        if (choosenArticle.getStatus() == ArticleStatus.PUBLISHED) {
            System.out.println("published date : " + choosenArticle.getPublishDate());
        }
        System.out.println("\n" + choosenArticle.getContent());
        if (choosenArticle.getBrief() != null) {
            System.out.println("\n brief: " + choosenArticle.getBrief());
        }
        System.out.println(choosenArticle.getAuthor().getUsername() + " " + choosenArticle.getAuthor().getLastName());
    }

}
