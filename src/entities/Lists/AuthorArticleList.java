package entities.Lists;

import entities.AuthorArticle;
import entities.List;

import java.util.Arrays;

public class AuthorArticleList{
    private AuthorArticle[] authorArticles = new AuthorArticle[10];
    private int index = 0;

    public void add(AuthorArticle authorArticle) {
        this.authorArticles[this.index] = authorArticle;
        ++this.index;
        if (this.index >= this.authorArticles.length) {
            this.authorArticles = Arrays.copyOf(this.authorArticles, this.authorArticles.length * 2);
        }
    }

    public AuthorArticle getAuthorArticle(int index) {
        return this.authorArticles[index];
    }
    public int findIndexByAuthorArticle(AuthorArticle authorArticle) {
        for (int i = 0; i < this.index; ++i) {
            if (this.authorArticles[i].equals(authorArticle)) {
                return i;
            }
        }
        return -1;
    }

    public int getIndex() {
        return index;
    }

    public void remove(int tempIndex) {
            authorArticles[tempIndex] = null;
            for (int i = tempIndex; i < index - 1; i++) {
                authorArticles[i] = authorArticles[i + 1];
            }
            index--;
            authorArticles[index] = null;
        }


    public List getArticlesOfAuthor() {
        List a = new List();
        for (int i = 0; i < this.index; ++i) {
            a.add(this.authorArticles[i].getArticle());
        }
        return a;
    }
}
