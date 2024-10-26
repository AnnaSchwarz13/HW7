package service;

import database.DataBase;
import entities.*;
import entities.enums.ArticleStatus;

import java.time.Clock;
import java.time.ZoneId;
import java.util.Random;
import java.util.Scanner;

public class ArticleService {
    private String title;
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    public void addArticle() {
        //todo:use a single constructor
        Article article = new Article();
        Category articleCategory = this.chooseCategory();
        article.setCategory(articleCategory);
        System.out.println("Enter title: ");
        this.title = sc.nextLine();
        System.out.println("Enter article text: ");
        String articleText = sc.nextLine();
        article.setTitle(this.title);
        article.setContent(articleText);
        article.setCreateDate(todaysDateAsString());
        article.setLastUpdateDate(todaysDateAsString());
        article.setBrief(setArticleTags());
        article.setId(rand.nextDouble());
        article.setPublished(false);
        article.setStatus(ArticleStatus.NOT_PUBLISHED);
        DataBase.articlesList.add(article);
    }

    public void showArticle(List articles) {
        if (articles.getIndex() == 0) {
            System.out.println("there is no article");
        } else {
            System.out.println("Please enter the title of the article's list \n for see more details: ");

            for (int i = 0; i < articles.getIndex(); ++i) {
                Article tempArticle = new Article();
                if (articles.getObjects(i) instanceof Article) {
                    tempArticle = (Article) articles.getObjects(i);
                } else if (articles.getObjects(i) instanceof AuthorArticle ) {
                   tempArticle = ((AuthorArticle) articles.getObjects(i)).getArticle();
                }
                System.out.println(tempArticle.getTitle());
            }

            this.title = this.sc.nextLine();
            Article userChoice = this.findArticleByTitle(this.title, articles);
            if (userChoice != null) {
                System.out.println(userChoice);
                if (userChoice.isPublished()) {
                    System.out.println("publishedDate: " + userChoice.getPublishDate());
                    Author author = getAuthorOfArticle(userChoice, DataBase.publishedArticles).getAuthor();
                    System.out.print("Author: ");
                    System.out.println(author.getFirstName() + "  " + author.getLastName());
                }
            }
        }

    }

    private Category chooseCategory() {
        boolean isCategorySelected = true;
        while (isCategorySelected) {
            if (DataBase.categoryList.getIndex() == 0) {
                System.out.println("there is no category");
            } else {
                System.out.println("Please Enter one of the following Categories:");
                for (int i = 0; i < DataBase.categoryList.getIndex(); i++) {
                    System.out.println(((Category) DataBase.categoryList.getObjects(i)).getTitle());
                }
            }

            System.out.println("\nenter -1 to add a new category");
            String categoryName = sc.nextLine();
            if (categoryName.equals("-1")) {
                System.out.println("Please enter category name");
                categoryName = sc.nextLine() + sc.nextLine();
                if (findCategoryByTitle(categoryName) != null) {
                    System.out.println("Category already exists");
                } else {
                    System.out.println("Please enter category description");
                    String categoryDescription = sc.nextLine() + sc.nextLine();
                    Category category = new Category(categoryName, categoryDescription);
                    DataBase.categoryList.add(category);
                    isCategorySelected = false;
                    return category;
                }
            }
            if (findCategoryByTitle(categoryName) != null) {
                isCategorySelected = false;
                return findCategoryByTitle(categoryName);
            }
            System.out.println("That category does not exist");
        }
        return null;


    }

    public Article findArticleByTitle(String title, List articles) {
        for (int i = 0; i < articles.getIndex(); ++i) {
            Article tempArticle = new Article();
            if (articles.getObjects(i) instanceof Article) {
                tempArticle = (Article) articles.getObjects(i);
            } else if (articles.getObjects(i) instanceof AuthorArticle ) {
                tempArticle = ((AuthorArticle) articles.getObjects(i)).getArticle();
            }
            if (tempArticle.getTitle().equals(title)) {
                return tempArticle;
            }
        }
        System.out.println("That article does not exist");
        return null;
    }

    public AuthorArticle findAuthorArticleByTitle(String title, List authorArticles) {
        for (int i = 0; i < authorArticles.getIndex(); i++) {
            AuthorArticle tempArticle = (AuthorArticle) authorArticles.getObjects(i);
            if (tempArticle.getArticle().getTitle().equals(title)) {
                return tempArticle;
            }
        }
        System.out.println("That article does not exist");
        return null;
    }

    private Category findCategoryByTitle(String title) {
        for (int i = 0; i < DataBase.categoryList.getIndex(); i++) {
            Category tempCategory = (Category) DataBase.categoryList.getObjects(i);
            if (tempCategory.getTitle().equals(title)) {
                return tempCategory;
            }
        }
        return null;
    }

    public String todaysDateAsString() {
        Clock clock = Clock.system(ZoneId.of("Asia/Tehran"));
        String time = clock.instant().toString().substring(0, 10) + " " + clock.instant().toString().substring(10, 16);
        return time;
    }

    private List setArticleTags() {
        List tagList = new List();
        System.out.println("Please enter the tags of the article: \n at the end enter -1");
        for (int i = 0; i < DataBase.tagList.getIndex(); i++) {
            Tag tag = (Tag) DataBase.tagList.getObjects(i);
            System.out.println(tag.getTitle());
        }
        System.out.println("For add a tag enter 1");
        while (true) {
            String tagName = this.sc.nextLine();
            if (tagName.equals("-1")) {
                break;
            }
            if (tagName.equals("1")) {
                System.out.println("Please enter your tag name");
                String newTagName = this.sc.nextLine();
                if (findTagByTitle(newTagName) != null) {
                    System.out.println("Tag already exists");
                } else {
                    Tag newTag = new Tag(newTagName);
                    DataBase.tagList.add(newTag);
                    System.out.println("New tags are there please choose a tag: \n at the end enter -1");
                    for (int j = 0; j < DataBase.tagList.getIndex(); j++) {
                        Tag tag = (Tag) DataBase.tagList.getObjects(j);
                        System.out.println(tag.getTitle());
                    }
                    System.out.println("For add a tag enter 1");
                }

            } else {
                Tag newTag = findTagByTitle(tagName);
                if (newTag != null) {
                    tagList.add(newTag);
                }
            }
        }
        return tagList;
    }

    private Tag findTagByTitle(String title) {
        for (int i = 0; i < DataBase.tagList.getIndex(); ++i) {
            Tag tempTag = (Tag) DataBase.tagList.getObjects(i);
            if (tempTag.getTitle().equals(title)) {
                return tempTag;
            }
        }
        System.out.println("That tag does not exist");
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
                AuthorArticle authorArticle = new AuthorArticle(choosenArticle, DataBase.loggedInAuthor);
                choosenArticle.setStatus(ArticleStatus.PENDING);
                DataBase.articlesToCheckForPublish.add(authorArticle);
                choosenArticle.setLastUpdateDate(todaysDateAsString());
            }
        } else if (choosenArticle.getStatus() == ArticleStatus.PUBLISHED) {
            AuthorArticle ExsistedAuthorArticle = findAuthorArticleByTitle(choosenArticle.getTitle(), DataBase.publishedArticles);
            int index = DataBase.publishedArticles.getIndexOfObject(ExsistedAuthorArticle);
            System.out.println("Remove article from published articles");
            choose = scanner.nextInt();
            if (choose == 1) {
                choosenArticle.setStatus(ArticleStatus.NOT_PUBLISHED);
                DataBase.publishedArticles.removeObject(index);
                choosenArticle.setLastUpdateDate(todaysDateAsString());
                choosenArticle.setPublished(false);
            }
        } else if (choosenArticle.getStatus() == ArticleStatus.PENDING) {
            AuthorArticle ExsistedAuthorArticle = findAuthorArticleByTitle(choosenArticle.getTitle(), DataBase.articlesToCheckForPublish);
            int index = DataBase.articlesToCheckForPublish.getIndexOfObject(ExsistedAuthorArticle);

            System.out.println("Cancel request to get published articles");
            choose = scanner.nextInt();
            if (choose == 1) {
                choosenArticle.setStatus(ArticleStatus.NOT_PUBLISHED);
                DataBase.articlesToCheckForPublish.removeObject(index);
                choosenArticle.setLastUpdateDate(todaysDateAsString());
            }
        }
        System.out.println("The selected article status is " + choosenArticle.getStatus());
    }

    public void changeDetailsOfArticle(Article choosenArticle) {
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
            Category newCategory = chooseCategory();
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

    public AuthorArticle getAuthorOfArticle(Article article, List authorArticleList) {
        for (int i = 0; i < authorArticleList.getIndex(); i++) {
            if (((AuthorArticle) authorArticleList.getObjects(i)).getArticle().equals(article)) {
                return (AuthorArticle) authorArticleList.getObjects(i);
            }
        }
        return null;
    }
}
