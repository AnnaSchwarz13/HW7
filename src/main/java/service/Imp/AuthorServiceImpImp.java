package service.Imp;

import entities.Author;
import entities.User;
import entities.enums.Role;
import repository.Imp.AuthorRepositoryImp;
import repository.Imp.UserRepositoryImp;

import java.sql.Date;
import java.sql.SQLException;

public class AuthorServiceImpImp extends UserServiceImp {

    AuthorRepositoryImp authorRepositoryImp = new AuthorRepositoryImp();
    UserRepositoryImp userRepositoryImp = new UserRepositoryImp();
    AuthenticationServiceImp authenticationServiceImp = new AuthenticationServiceImp();

    public void userSignup(String firstName, String lastName, String username,
                           String password, String nationalCode, Date birthday) throws SQLException {

        Author signingAuthor = new Author(firstName, lastName, username, password, nationalCode, birthday);
        User user = new User(username, password, Role.AUTHOR);
        userRepositoryImp.create(user);
        authorRepositoryImp.create(signingAuthor);
        System.out.println("Author signed up successfully");
    }


    public void changePassword(String oldPassword, String newPassword) throws SQLException {
        if (authenticationServiceImp.getLoggedUser().getPassword().equals(oldPassword)) {
            authorRepositoryImp.setUpdatePassword(authorRepositoryImp.findByUserId(authenticationServiceImp.getLoggedUser().getId()), newPassword);
            System.out.println("Password changed successfully");
            return;
        }
        System.out.println("Wrong password");
    }

}