package flik;

import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {
    @Test
    public void TestIntUnder100() {
        for (int a = 0, b = 0; a < 100; a++, b++) {
            assertTrue(Flik.isSameNumber(a, b));
        }
    }

    @Test
    public void TestIntUnder200() {
        for (int a = 101, b = 101; a < 200; a++, b++) {
            boolean res = Flik.isSameNumber(a, b);
            assertTrue("a is " + a + " b is " + b, res);
        }
    }

    @Test
    public void TestInteger(){
        Integer a = new Integer("128");
        Integer b = new Integer("128");
        assertTrue("a is " + a + " b is " + b, Flik.isSameNumber(a, b));

    }

    @Test
    public void TestBeyond128(){
        int a = 200;
        int b = 200;
        assertTrue(Flik.isSameNumber(a, b));
    }
}
