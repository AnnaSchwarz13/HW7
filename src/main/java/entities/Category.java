package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Category {
    private final String title;
    private final String description;
    private  long id;

    public Category(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
