package deque;

import org.junit.Test;
import java.util.Comparator;
import static org.junit.Assert.*;
public class MaxArrayDequeTest {

    public static class strLenCom implements Comparator<String>{
        public int compare(String str1, String str2){
            return str1.length() - str2.length();
        }
    }

    public static class letterCom implements Comparator<String>{
        public int compare(String str1, String str2){
            return str1.compareTo(str2);
        }
    }

    @Test
    public void maxTest(){
        Comparator<String> strLen = new strLenCom();
        MaxArrayDeque<String> mad1 = new MaxArrayDeque<>(strLen);

        mad1.addLast("a");
        mad1.addLast("abc");
        mad1.addFirst("adog");

        assertEquals("adog", mad1.max());

    }

    @Test
    public void max2Test(){
        Comparator<String> strLen = new strLenCom();
        MaxArrayDeque<String> mad1 = new MaxArrayDeque<>(strLen);

        mad1.addLast("f");
        mad1.addLast("bc");
        mad1.addFirst("dog");

        Comparator<String> letterC = new letterCom();

        assertEquals("f", mad1.max(letterC));
    }
}
