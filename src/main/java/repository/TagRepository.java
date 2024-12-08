package repository;

import entities.Article;
import entities.Tag;

import java.sql.SQLException;
import java.util.List;

public interface TagRepository {
    Tag create(Tag tag) throws SQLException;

    // Tag read(int id) throws SQLException;
    void delete(long id) throws SQLException;

    int findCount() throws SQLException;

    List<Tag> all();

    Tag findTagByTile(String title) throws SQLException;

    List<Tag> getTags(Article article);

    void setArticlesTag(List<Tag> tags, Article article);
}
