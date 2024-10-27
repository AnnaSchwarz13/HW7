package entities;

import java.util.Random;

public class Tag {
    private final String title;
    Random rand = new Random();

    public Tag(String title) {
        double id = rand.nextDouble();
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
