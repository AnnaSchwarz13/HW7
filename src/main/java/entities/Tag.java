package entities;

import lombok.Getter;


public class Tag {
    @Getter
    private final String title;

    public Tag(long id,String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
