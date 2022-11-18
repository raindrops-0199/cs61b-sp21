package deque;

import java.util.Iterator;

/** Implement deque with linked list.*/
public class LinkedListDeque<T> implements Deque<T>{

    public class Node{
        public Node prev = null;
        public Node next = null;
        public T content;

        public Node(T con){
            content = con;
        }

        public T getRcursive(int index){
            if (index == 0){
                return content;
            } else{
                assert next != null;
                return next.getRcursive(index - 1);
            }
        }
    }

    private class LinkedListDequeIterator implements Iterator<T> {
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

    /** Same as get, but uses recursion. */
    public T getRecursive(int index){
        if (index >= size){
            return null;
        }
        return sentinel.next.getRcursive(index);
    }
    public Iterator<T> iterator(){
        return new LinkedListDequeIterator();
    }

    public boolean equals(Object o){
        if (o instanceof LinkedListDeque){
            LinkedListDeque<T> target = (LinkedListDeque<T>) o;
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

