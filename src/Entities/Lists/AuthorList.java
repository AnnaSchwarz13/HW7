package Entities.Lists;

import Entities.Author;

import java.util.Arrays;

public class AuthorList {
    private Author[] authors = new Author[10];
    private int index = 0;

   public void add(Author author) {
        this.authors[this.index] = author;
        ++this.index;
        if (this.index >= this.authors.length) {
            this.authors =Arrays.copyOf(this.authors, this.authors.length * 2);
        }
    }
    public int getIndex() {
        return index;
    }

    public Author getUsers(int index) {
        return this.authors[index];
    }
}
