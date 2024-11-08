package entities;

import lombok.Getter;

import java.util.Random;

public class Tag {
    @Getter
    private final String title;
    private final long id;

    public Tag(long id,String title) {
        this.title = title;
        this.id = id;
    }

    @Override
    public String toString() {
        return title;
    }
}
