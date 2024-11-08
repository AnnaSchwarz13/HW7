package service;

import entities.Author;
import entities.User;
import entities.enums.Role;
import repository.Imp.AuthorRepositoryImp;
import repository.Imp.UserRepositoryImp;

import java.sql.Date;
import java.sql.SQLException;

public class AuthorService extends UserService{
AuthorRepositoryImp authorRepositoryImp = new AuthorRepositoryImp();
UserRepositoryImp userRepositoryImp = new UserRepositoryImp();
    public void userSignup(String firstName, String lastName, String username,
                           String password, String nationalCode, Date birthday) throws SQLException {
        
        Author signingAuthor = new Author(firstName, lastName, username, password, nationalCode, birthday);
        User user = new User(username,password, Role.AUTHOR);
        userRepositoryImp.create(user);
        authorRepositoryImp.create(signingAuthor);
        System.out.println("Author signed up successfully");
    }


    public void changePassword(String oldPassword, String newPassword) {
        if (AuthenticationService.getLoggedUser().getPassword().equals(oldPassword)) {
            AuthenticationService.getLoggedUser().setPassword(newPassword);
            return;
        }
        System.out.println("Wrong password");
    }

}