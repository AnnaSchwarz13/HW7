package service;

import database.DataBase;
import entities.Category;
import repository.Imp.CategoryRepositoryImp;

import java.sql.SQLException;
import java.util.Scanner;

public class CategoryService {
    Scanner sc = new Scanner(System.in);
    CategoryRepositoryImp categoryRepositoryImp = new CategoryRepositoryImp();
    private Category chooseCategory() throws SQLException {
        while (true) {
            if (CategoryRepositoryImp.findCount() == 0) {
                System.out.println("there is no category");
            } else {
                System.out.println("Please Enter one of the following Categories:");
                for (Category category:CategoryRepositoryImp.all()) {
                    System.out.println(category.getTitle());
                }
            }

            System.out.println("\nenter -1 to add a new category");
            String categoryName = sc.nextLine();
            if (categoryName.equals("-1")) {
                System.out.println("Please enter category name");
                categoryName = sc.nextLine() + sc.nextLine();
                if (categoryRepositoryImp.findCategoryByTile(categoryName) != null) {
                    System.out.println("Category already exists");
                } else {
                    System.out.println("Please enter category description");
                    String categoryDescription = sc.nextLine() + sc.nextLine();
                    Category category = new Category(0,categoryName, categoryDescription);
                    category = categoryRepositoryImp.create(category);

                    return category;
                }
            }
            if (categoryRepositoryImp.findCategoryByTile(categoryName) != null) {
                return categoryRepositoryImp.findCategoryByTile(categoryName);
            }
            System.out.println("That category does not exist");
        }
    }

}