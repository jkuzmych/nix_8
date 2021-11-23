package ua.com.alevel.dynamicarray;

public class DynamicArray {
    private final int CAPACITY = 20;
    //add, delete, resize, getbyindex
    private int size;
    private int capacity;
    private Object[] array;

    public DynamicArray() {
        array = new Object[CAPACITY];
        capacity = CAPACITY;
        size = 0;
    }

    public int Size() {
        return size;
    }

    public void add(Object user) {
        if (size == capacity) resize();
        array[size] = user;
        size++;
    }

    public void delete(int index) {
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    public void resize() {
        Object bufferarray[] = new Object[capacity * 2];
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                bufferarray[i] = array[i];
            }
        }
        array = bufferarray;
        capacity = capacity * 2;
    }

    public Object getbyindex(int index) {
        return array[index];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(array[i] + "\n");
        }
        return stringBuilder.toString();
    }
}