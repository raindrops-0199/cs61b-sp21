package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdRandom;

/** ArrayDeque test. */
public class ArrayDequeTest {

    /** Test add functionality*/
    @Test
    public void addTest(){
        ArrayDeque<Integer> ald1 = new ArrayDeque<Integer>();
        assertTrue(ald1.isEmpty());

        ald1.addLast(3);
        assertFalse(ald1.isEmpty());

        ald1.addFirst(4);
        assertFalse(ald1.isEmpty());
        assertEquals(2, ald1.size());

        ald1.printDeque();
    }


    @Test
    public void addRemoveTest(){
        ArrayDeque<String> ald1 = new ArrayDeque<String>();
        assertTrue(ald1.isEmpty());

        ald1.addFirst("is");
        ald1.addFirst("this");
        ald1.addLast("a");
        ald1.addLast("dog");

        assertEquals(4, ald1.size());
        ald1.printDeque();

        assertEquals("dog", ald1.removeLast());
        assertEquals("this", ald1.removeFirst());

        assertEquals(2, ald1.size());

        ald1.removeLast();
        ald1.removeLast();

        assertNull(ald1.removeLast());
        assertNull(ald1.removeFirst());
    }

    @Test
    public void getTest(){
        ArrayDeque<String> ald1 = new ArrayDeque<String>();

        ald1.addLast("this");
        ald1.addLast("is");
        ald1.addLast("a");
        ald1.addLast("dog");

        assertEquals("this", ald1.get(0));
        assertEquals("dog", ald1.get(3));

        assertNull(ald1.get(4));
    }

    @Test
    public void resizeTest(){
        ArrayDeque<Integer> ald1 = new ArrayDeque<Integer>();
        ArrayDeque<Integer> ald2 = new ArrayDeque<Integer>();

        int targetSize = 1000;
        for (int i = 0; i < targetSize; i++){
            ald1.addLast(i);
        }
        for (int i = 0; i < targetSize; i++){
            ald2.addFirst(i);
        }
        assertEquals(targetSize, ald1.size());
        assertEquals(targetSize, ald2.size());

        int getItem1 = ald1.get(78);
        assertEquals(78, getItem1);

        int getItem2 = ald2.get(0);
        assertEquals(targetSize - 1, getItem2);

        assertNull(ald1.get(targetSize));

    }

    @Test
    public void randomTest(){
        ArrayDeque<Integer> ald = new ArrayDeque<Integer>();
        LinkedListDeque<Integer> lld = new LinkedListDeque<Integer>();

        int N = 50000;
        for (int i = 0 ; i < N; i++){
            int operationNumber = StdRandom.uniform(0, 6);

            switch (operationNumber){
                case 0:
                    // addLast the same value to both lists
                    int randVal1 = StdRandom.uniform(0, 1000);
                    ald.addLast(randVal1);
                    lld.addLast(randVal1);

                case 1:
                    // addFirst the same value to both lists
                    int randVal2 = StdRandom.uniform(0, 100);
                    ald.addFirst(randVal2);
                    lld.addFirst(randVal2);

                case 2:
                    // assert if size are equal
                    assertEquals(lld.size(), ald.size());

                case 3:
                    // assert if get random index value is equal
                    if (ald.size() > 0 && lld.size() > 0) {
                        int randIndex = StdRandom.uniform(0, ald.size());
                        assertEquals(lld.get(randIndex), ald.get(randIndex));
                    }

                case 4:
                    // assert if removeLast is equal
                    assertEquals(lld.removeLast(), ald.removeLast());

                case 5:
                    // assert if removeFirst is equal
                    assertEquals(lld.removeFirst(), ald.removeFirst());
            }
        }
    }
}
