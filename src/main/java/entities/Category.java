package entities;

import lombok.Getter;

import java.util.Random;

public class Category {
    @Getter
    private final String title;
    @Getter
    private final String description;
    Random rand = new Random();
    public Category( String title, String description) {
        this.title = title;
        this.description = description;
        double id = rand.nextDouble();
    }

}
