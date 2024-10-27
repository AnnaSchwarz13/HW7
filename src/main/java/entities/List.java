package entities;

import lombok.Getter;

import java.util.Arrays;

public class List {
    private Object[] objects = new Object[10];
    @Getter
    private int index = 0;

    public void add(Object object) {
        this.objects[this.index] = object;
        this.index++;
        if (this.index >= this.objects.length) {
            this.objects = Arrays.copyOf(this.objects, this.objects.length * 2);
        }
    }

    public Object getObjects(int index) {
        return this.objects[index];
    }
    public void removeObject(int tempIndex) {
        objects[tempIndex] = null;
        for (int i = tempIndex; i < index - 1; i++) {
            objects[i] = objects[i + 1];
        }
        index--;
        objects[index] = null;
    }
    public int getIndexOfObject(Object object) {
        for (int i = 0; i < this.index; ++i) {
            if (this.objects[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder Objects = new StringBuilder();
        for (int i = 0; i < this.index; i++) {
            Objects.append(this.objects[i].toString()).append(", ");
        }
        return Objects.toString();
    }
}