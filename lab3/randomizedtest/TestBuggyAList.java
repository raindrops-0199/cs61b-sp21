package randomizedtest;

import com.google.common.collect.Streams;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {

    @Test
    public void easyTest1(){
        AListNoResizing<Integer> correctList = new AListNoResizing<Integer>();
        BuggyAList<Integer> brokenList = new BuggyAList<Integer>();
        correctList.addLast(3);
        correctList.addLast(4);
        correctList.addLast(5);

        brokenList.addLast(3);
        brokenList.addLast(4);
        brokenList.addLast(5);

        assertEquals(correctList.size(), brokenList.size());

        assertEquals(correctList.removeLast(), brokenList.removeLast());
        assertEquals(correctList.removeLast(), brokenList.removeLast());
        assertEquals(correctList.removeLast(), brokenList.removeLast());
    }

    @Test
    public void exampleTest(){
        AListNoResizing<Integer> correctList = new AListNoResizing<>();
        BuggyAList<Integer> brokenList = new BuggyAList<Integer>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast the same value to both lists
                int randVal = StdRandom.uniform(0, 100);
                correctList.addLast(randVal);
                brokenList.addLast(randVal);

            } else if (operationNumber == 1) {
                // assert if size are equal
                assertEquals(correctList.size(), brokenList.size());

            } else if (operationNumber == 2) {
                // assert if getLast is equal
                if (correctList.size() > 0 && brokenList.size() > 0){
                    assertEquals(correctList.getLast(), brokenList.getLast());
                }

            } else if (operationNumber == 3) {
                // assert if removeLast is equal
                if (correctList.size() > 0 && brokenList.size() > 0){
                    assertEquals(correctList.removeLast(), brokenList.removeLast());
                }
            }
        }

    }
}
