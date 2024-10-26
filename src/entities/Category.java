package entities;

import java.util.Random;

public class Category {
    private double id;
    private String title;
    private String description;
    Random rand = new Random();
    public Category( String title, String description) {
        this.title = title;
        this.description = description;
        id = rand.nextDouble();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
