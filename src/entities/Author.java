package entities;

import entities.enums.Role;

public class Author extends User{
    private String firstName;
    private String lastName;
    private String nationalCode;
    private Birthday birthday;
    private List thisUserArticlesList;

    public Author(String username, String password, String firstName, String lastName,
                  String nationalCode, Birthday birthday) {
        super(username, password, Role.AUTHOR);
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.birthday = birthday;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public List getThisUserArticlesList() {
        return this.thisUserArticlesList;
    }

}
