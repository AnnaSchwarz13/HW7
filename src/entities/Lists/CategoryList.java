package entities.Lists;

import entities.Category;

import java.util.Arrays;

public class CategoryList  {
    private Category[] categories = new Category[10];
    private int index = 0;

    public void add(Category category) {
        this.categories[this.index] = category;
        ++this.index;
        if (this.index >= this.categories.length) {
            this.categories = Arrays.copyOf(this.categories, this.categories.length * 2);
        }
    }
    public int getIndex() {
        return index;
    }
    public Category getCategory(int index) {
        return this.categories[index];
    }
}
