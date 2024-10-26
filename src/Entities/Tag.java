package Entities;

import java.util.Random;

public class Tag {
    private String title;
    private double id;
Random rand = new Random();
    public Tag( String title) {
        this.id = rand.nextDouble();
        this.title = title;
    }

    public String getTitle() {
        return title;
    }


}
