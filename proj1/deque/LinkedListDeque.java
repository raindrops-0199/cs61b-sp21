package deque;

import java.util.Iterator;

/** Implement deque with linked list.*/
public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    private class Node {
        private Node prev = null;
        private Node next = null;
        private T content;

        Node(T con) {
            content = con;
        }

        public T getRcursive(int index) {
            if (index == 0) {
                return content;
            } else {
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
            if (!hasNext()) {
                return null;
            }
            T res = get(pos);
            pos += 1;
            return res;
        }
    }

    private int size;
    private Node sentinel;

    public LinkedListDeque() {
        sentinel = new Node(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node n = new Node(item);
        n.next = sentinel.next;
        sentinel.next.prev = n;
        sentinel.next = n;
        n.prev = sentinel;
        size += 1;
    }

    public void addLast(T item) {
        Node n = new Node(item);
        sentinel.prev.next = n;
        n.prev = sentinel.prev;
        n.next = sentinel;
        sentinel.prev = n;
        size += 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node current = sentinel.next;
        while (current != sentinel) {
            System.out.print(current.content);
            System.out.print(" ");
            current = current.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node tmp = sentinel.next;
        sentinel.next = tmp.next;
        tmp.next.prev = sentinel;
        size -= 1;
        return tmp.content;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node tmp = sentinel.prev;
        sentinel.prev = tmp.prev;
        tmp.prev.next = sentinel;
        size -= 1;
        return tmp.content;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node current = sentinel.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.content;
    }

    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return sentinel.next.getRcursive(index);
    }
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
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

