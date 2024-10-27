package entities;

import entities.enums.Role;
import lombok.Getter;

@Getter
public class Author extends User{
    private final String firstName;
    private final String lastName;
    private final List thisUserArticlesList;

    public Author(String firstName, String lastName,String username, String password,
                  String nationalCode, Birthday birthday) {
        super(username, password, Role.AUTHOR);
        this.firstName = firstName;
        this.lastName = lastName;
        this.thisUserArticlesList = new List();
    }

}
