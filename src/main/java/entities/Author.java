package entities;

import java.util.ArrayList;
import java.util.List;
import entities.enums.Role;
import lombok.Getter;
import service.UserService;

import java.util.Date;

@Getter
public class Author extends User{
    private final String firstName;
    private final String lastName;
    private final List<Article> thisUserArticlesList;
    private final String nationalCode;
    private final Date birthDate;
    private long id;

    public Author(String firstName, String lastName,String username, String password,
                  String nationalCode, Date birthday) {
        super(username, password, Role.AUTHOR);
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.birthDate = birthday;
        this.thisUserArticlesList = new ArrayList<>();
    }

}
