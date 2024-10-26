package entities.Lists;

import java.util.Arrays;

public class List {
    private Object[] objects = new Object[10];
    private int index = 0;

    public void add(Object object) {
        this.objects[this.index] = object;
        this.index++;
        if (this.index >= this.objects.length) {
            this.objects = Arrays.copyOf(this.objects, this.objects.length * 2);
        }
    }
    public int getIndex() {
        return index;
    }

    public Object getObjects(int index) {
        return this.objects[index];
    }
}