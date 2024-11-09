package repository;

import entities.Tag;

import java.sql.SQLException;

public interface TagRepository {
    Tag create(Tag tag) throws SQLException;
   // Tag read(int id) throws SQLException;
    void delete(long id) throws SQLException;
}
