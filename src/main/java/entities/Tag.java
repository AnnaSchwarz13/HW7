package entities;

import lombok.Getter;

import java.util.Random;

public class Tag {
    @Getter
    private final String title;
    Random rand = new Random();

    public Tag(String title) {
        double id = rand.nextDouble();
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
