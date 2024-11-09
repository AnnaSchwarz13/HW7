package entities;

import lombok.Getter;

@Getter
public class Tag {
    private final String title;
    private long id;

    public Tag(long id, String title) {
        this.title = title;
        this.id = id;
    }

    public Tag(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
