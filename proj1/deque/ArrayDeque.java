package deque;

import java.util.Iterator;

/** Array is treated as circular */
public class ArrayDeque<T> implements Deque<T> {

    private class ArrayDequeIterator implements Iterator<T>{
        private int pos = 0;
        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public T next() {
            if (!hasNext()){
                return null;
            }
            T res = get(pos);
            pos += 1;
            return res;
        }
    }
    protected int  size;
    protected T[] items;
    protected int first;
    protected int last;
    protected double usageFactor;

    public ArrayDeque(){
        size = 0;
        items = (T[]) new Object[8];
        usageFactor = 0.5;
        first = 0;
        last = 0;
    }

    /** Resize the array. Make sure it meets the usage factor. */
    private void resize(int newSize){
        T[] newArray = (T[]) new Object[newSize];
        int current = 0;
        for (int i = 0; i < size; i++){
            newArray[current] = items[(first + i) % items.length];
            current += 1;
        }
        items = newArray;
        first = 0;
        last = size == 0 ? 0 : current - 1;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item){
        if (size == items.length){
            resize(size * 2);
        }
        if (size != 0){
            first = (first + items.length - 1) % items.length;
        }
        items[first] = item;
        size += 1;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item){
        if (size == items.length){
            resize(size * 2);
        }
        if (size != 0){
            last = (last + 1) % items.length;
        }
        items[last] = item;
        size += 1;
    }

    /** Returns the number of items in the deque. */
    public int size(){
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     *  Once all the items have been printed, print out a new line.*/
    public void printDeque(){
        for (int i = 0; i < size; i++){
            System.out.print(items[(first + i) % items.length]);
            System.out.print(" ");
        }
        System.out.println();
    }

    /**Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.*/
    public T removeFirst(){
        if (size == 0){
            return null;
        }
        T res = items[first];
        items[first] = null;
        if (size != 1) {
            first = (first + 1) % items.length;
        }
        size -= 1;
        if (items.length > 8 && (double)size / items.length < usageFactor){
            resize(items.length / 2);
        }
        return res;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.*/
    public T removeLast(){
        if (size == 0){
            return null;
        }
        T res = items[last];
        items[last] = null;
        if (size != 1) {
            last = (last + items.length - 1) % items.length;
        }
        size -= 1;
        if (items.length > 8 && (double)size / items.length < usageFactor){
            resize(items.length / 2);
        }
        return res;
    }

    /** Gets the item at the given index
     *  If no such item exists, returns null. */
    public T get(int index){
        if (index >= size){
            return null;
        }
        return items[(first + index) % items.length];
    }

    public Iterator<T> iterator(){
        return new ArrayDequeIterator();
    }

    public boolean equals(Object o){
        if (o instanceof ArrayDeque){
            ArrayDeque<T> target = (ArrayDeque<T>) o;
            if (target.size() != size){
                return false;
            }
            for (int i = 0; i < size; i++){
                if (!target.get(i).equals(this.get(i))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
