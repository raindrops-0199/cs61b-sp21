package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/** ArrayDeque test. */
public class ArrayDequeTest {

    /** Test add functionality*/
    @Test
    public void addTest(){
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        assertTrue(ad1.isEmpty());

        ad1.addLast(3);
        assertFalse(ad1.isEmpty());

        ad1.addFirst(4);
        assertFalse(ad1.isEmpty());
        assertEquals(2, ad1.size());

        ad1.printDeque();
    }


    @Test
    public void addRemoveTest(){
        ArrayDeque<String> ad1 = new ArrayDeque<String>();
        assertTrue(ad1.isEmpty());

        ad1.addFirst("is");
        ad1.addFirst("this");
        ad1.addLast("a");
        ad1.addLast("dog");

        assertEquals(4, ad1.size());
        ad1.printDeque();

        assertEquals("dog", ad1.removeLast());
        assertEquals("this", ad1.removeFirst());

        assertEquals(2, ad1.size());

        ad1.removeLast();
        ad1.removeLast();

        assertNull(ad1.removeLast());
        assertNull(ad1.removeFirst());
    }

    @Test
    public void getTest(){
        ArrayDeque<String> ad1 = new ArrayDeque<String>();

        ad1.addLast("this");
        ad1.addLast("is");
        ad1.addLast("a");
        ad1.addLast("dog");

        assertEquals("this", ad1.get(0));
        assertEquals("dog", ad1.get(3));

        assertNull(ad1.get(4));
    }

    @Test
    public void resizeTest(){
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        ArrayDeque<Integer> ad2 = new ArrayDeque<Integer>();

        int targetSize = 1000;
        for (int i = 0; i < targetSize; i++){
            ad1.addLast(i);
        }
        for (int i = 0; i < targetSize; i++){
            ad2.addFirst(i);
        }
        assertEquals(targetSize, ad1.size());
        assertEquals(targetSize, ad2.size());

        int getItem1 = ad1.get(78);
        assertEquals(78, getItem1);

        int getItem2 = ad2.get(0);
        assertEquals(targetSize - 1, getItem2);

        assertNull(ad1.get(targetSize));

    }

    @Test
    public void iteratorTest(){
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i < 1000; i++){
            ad1.addLast(i);
        }

        Iterator<Integer> iter = ad1.iterator();
        int exp = 0;
        while (iter.hasNext()){
            int act = iter.next();
            assertEquals(exp, act);
            exp += 1;
        }
    }

    @Test
    public void equalTest(){
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        ArrayDeque<Integer> ad2 = new ArrayDeque<Integer>();

        int targetSize = 1000;
        for (int i = 0; i < targetSize; i++){
            ad1.addLast(i);
            ad2.addLast(i);
        }
        assertTrue(ad1.equals(ad2));

        assertFalse(ad1.equals(30));
        
        ArrayDeque<String> ad3 = new ArrayDeque<>();
        ad3.addLast("aaa");
        assertFalse(ad1.equals(ad3));
    }

    @Test
    public void randomTest(){
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        LinkedListDeque<Integer> lld = new LinkedListDeque<Integer>();

        int N = 50000;
        for (int i = 0 ; i < N; i++){
            int operationNumber = StdRandom.uniform(0, 6);

            switch (operationNumber){
                case 0:
                    // addLast the same value to both lists
                    int randVal1 = StdRandom.uniform(0, 1000);
                    ad.addLast(randVal1);
                    lld.addLast(randVal1);

                case 1:
                    // addFirst the same value to both lists
                    int randVal2 = StdRandom.uniform(0, 100);
                    ad.addFirst(randVal2);
                    lld.addFirst(randVal2);

                case 2:
                    // assert if size are equal
                    assertEquals(lld.size(), ad.size());

                case 3:
                    // assert if get random index value is equal
                    if (ad.size() > 0 && lld.size() > 0) {
                        int randIndex = StdRandom.uniform(0, ad.size());
                        assertEquals(lld.get(randIndex), ad.get(randIndex));
                    }

                case 4:
                    // assert if removeLast is equal
                    assertEquals(lld.removeLast(), ad.removeLast());

                case 5:
                    // assert if removeFirst is equal
                    assertEquals(lld.removeFirst(), ad.removeFirst());
            }
        }
    }
}
