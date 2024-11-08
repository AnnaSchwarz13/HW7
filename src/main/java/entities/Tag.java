package entities;

import lombok.Getter;

@Getter
public class Tag {
    private final String title;

    public Tag(long id,String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
