package deque;

import java.util.Iterator;

public interface Deque<T> {

    /** Adds an item of type T to the front of the deque. */
    void addFirst(T item);

    /** Adds an item of type T to the back of the deque. */
    void addLast(T item);

    /** Returns the number of items in the deque. */
    int size();

    /** Prints the items in the deque from first to last, separated by a space.
     *  Once all the items have been printed, print out a new line.*/
    void printDeque();

    /**Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.*/
    T removeFirst();

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.*/
    T removeLast();

    /** Gets the item at the given index
     *  If no such item exists, returns null. */
    T get(int index);

    /** Returns true if deque is empty, false otherwise. */
    default boolean isEmpty() {
        return size() == 0;
    }
}
