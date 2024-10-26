package Entities;

import Entities.Lists.ArticleList;
import Entities.enums.Role;

public class Author extends User{
    private String firstName;
    private String lastName;
    private String nationalCode;
    private Birthday birthday;
    private ArticleList thisUserArticlesList;

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

    public ArticleList getThisUserArticlesList() {
        return this.thisUserArticlesList;
    }

    public void setThisUserArticlesList(ArticleList thisUserArticlesList) {
        this.thisUserArticlesList = thisUserArticlesList;
    }

    public void clearThisUserArticlesList() {
        this.thisUserArticlesList = new ArticleList();
    }
}
