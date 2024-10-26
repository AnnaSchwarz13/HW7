package entities.Lists;

import entities.Moderator;

import java.util.Arrays;

public class ModeratorList {
    private Moderator[] moderators = new Moderator[10];
    private int index = 0;

    public void add(Moderator moderator) {
        this.moderators[this.index] = moderator;
        ++this.index;
        if (this.index >= this.moderators.length) {
            this.moderators = Arrays.copyOf(this.moderators, this.moderators.length * 2);
        }
    }
    public int getIndex() {
        return index;
    }

    public Moderator getModerators(int index) {
       return this.moderators[index];
    }
}

