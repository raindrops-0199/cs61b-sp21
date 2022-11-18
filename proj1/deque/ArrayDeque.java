package deque;

import java.util.Iterator;

/** Array is treated as circular */
public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private class ArrayDequeIterator implements Iterator<T> {
        private int pos = 0;
        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }
            T res = get(pos);
            pos += 1;
            return res;
        }
    }
    private int  size;
    private T[] items;
    private int first;
    private int last;
    private double usageFactor;

    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        usageFactor = 0.5;
        first = 0;
        last = 0;
    }

    /** Resize the array. Make sure it meets the usage factor. */
    private void resize(int newSize) {
        T[] newArray = (T[]) new Object[newSize];
        int current = 0;
        for (int i = 0; i < size; i++) {
            newArray[current] = items[(first + i) % items.length];
            current += 1;
        }
        items = newArray;
        first = 0;
        last = size == 0 ? 0 : current - 1;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        if (size != 0) {
            first = (first + items.length - 1) % items.length;
        }
        items[first] = item;
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        if (size != 0) {
            last = (last + 1) % items.length;
        }
        items[last] = item;
        size += 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[(first + i) % items.length]);
            System.out.print(" ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T res = items[first];
        items[first] = null;
        if (size != 1) {
            first = (first + 1) % items.length;
        }
        size -= 1;
        if (items.length > 8 && (double) size / items.length < usageFactor) {
            resize(items.length / 2);
        }
        return res;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T res = items[last];
        items[last] = null;
        if (size != 1) {
            last = (last + items.length - 1) % items.length;
        }
        size -= 1;
        if (items.length > 8 && (double) size / items.length < usageFactor) {
            resize(items.length / 2);
        }
        return res;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return items[(first + index) % items.length];
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    public boolean equals(Object o) {
        if (o instanceof Deque) {
            Deque<T> target = (Deque<T>) o;
            if (target.size() != size) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (!target.get(i).equals(this.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
