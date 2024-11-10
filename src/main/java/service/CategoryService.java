package service;

import entities.Category;

import java.sql.SQLException;

public interface CategoryService {
    Category chooseCategory() throws SQLException;
}
