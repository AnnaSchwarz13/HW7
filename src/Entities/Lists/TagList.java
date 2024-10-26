package Entities.Lists;

import Entities.Tag;

import java.util.Arrays;

public class TagList {
    private Tag[] tags = new Tag[10];
    private int index = 0;

    public void add(Tag article) {
        this.tags[this.index] = article;
        ++this.index;
        if (this.index >= this.tags.length) {
            this.tags = Arrays.copyOf(this.tags, this.tags.length * 2);
        }

    }
    public int getIndex() {
        return index;
    }
    public void remove(int tempIndex) {
        tags[tempIndex] = null;
        for (int i = tempIndex; i < index - 1; i++) {
            tags[i] = tags[i + 1];
        }
        index--;
        tags[index] = null;
    }
    public Tag getTags(int index) {
        return this.tags[index];
    }

    public String toStringTags() {
        String tags = "";
        for (int i = 0; i < index; i++) {
            tags =  this.tags[i].getTitle()+ " , " + tags;
        }
        return tags;
    }
}
