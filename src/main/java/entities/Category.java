package entities;

import lombok.Getter;

import java.util.Random;
@Getter
public class Category {
    private final String title;
    private final String description;
    private final Double id;
    Random rand = new Random();
    public Category( Double id,String title, String description) {
        this.title = title;
        this.description = description;
        this.id = id;
    }

}
