package deque;

import java.util.Iterator;

/** Array is treated as circular */
public class ArrayDeque<T> {
    private int  size;
    private T[] items;
    private int first;
    private int last;
    private double usageFactor;

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

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty(){
        return size == 0;
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
        first = (first + 1) % items.length;
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
        last = (last + items.length - 1) % items.length;
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
        return null;
    }

    public boolean equals(Object o){
        return false;
    }

}
