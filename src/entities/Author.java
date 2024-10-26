package entities;

import entities.enums.Role;

public class Author extends User{
    private String firstName;
    private String lastName;
    private String nationalCode;
    private Birthday birthday;
    private List thisUserArticlesList;

    public Author(String username, String password ) {
        super(username, password, Role.AUTHOR);

    }

    public void setBirthday(Birthday birthday) {
        this.birthday = birthday;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public List getThisUserArticlesList() {
        return this.thisUserArticlesList;
    }

    public void setThisUserArticlesList(List thisUserArticlesList) {
        this.thisUserArticlesList = thisUserArticlesList;
    }

    public void clearThisUserArticlesList() {
        this.thisUserArticlesList = new List();
    }
}
