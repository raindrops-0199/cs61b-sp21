package deque;

import java.util.Iterator;

/** Implement deque with linked list.*/
public class LinkedListDeque<T> {

    public class Node{
        public Node prev;
        public Node next;
        public T content;

        public Node(T con){
            content = con;
        }
    }

    private int size;
    private Node sentinel;

    public LinkedListDeque(){
        sentinel = new Node(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item){
        Node n = new Node(item);
        n.next = sentinel.next;
        sentinel.next.prev = n;
        sentinel.next = n;
        n.prev = sentinel;
        size += 1;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item){
        Node n = new Node(item);
        sentinel.prev.next = n;
        n.prev = sentinel.prev;
        n.next = sentinel;
        sentinel.prev = n;
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
        Node current = sentinel.next;
        while (current != sentinel){
            System.out.print(current.content);
            System.out.print(" ");
            current = current.next;
        }
        System.out.println();
    }

    /**Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.*/
    public T removeFirst(){
        if (size == 0){
            return null;
        }
        Node tmp = sentinel.next;
        sentinel.next = tmp.next;
        tmp.next.prev = sentinel;
        size -= 1;
        return tmp.content;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.*/
    public T removeLast(){
        if (size == 0){
            return null;
        }
        Node tmp = sentinel.prev;
        sentinel.prev = tmp.prev;
        tmp.prev.next = sentinel;
        size -= 1;
        return tmp.content;
    }

    /** Gets the item at the given index
     *  If no such item exists, returns null. */
    public T get(int index){
        if (index >= size){
            return null;
        }
        Node current = sentinel.next;
        for (int i = 0; i < index; i++){
            current = current.next;
        }
        return current.content;
    }

    public Iterator<T> iterator(){
        return null;
    }

    public boolean equals(Object o){
        return false;
    }

}

