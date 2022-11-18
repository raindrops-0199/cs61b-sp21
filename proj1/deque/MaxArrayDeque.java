package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    /** creates a MaxArrayDeque with the given Comparator. */
    public MaxArrayDeque(Comparator<T> c) {
        super();
        comparator = c;
    }

    /** returns the maximum element in the deque as governed by the previously given Comparator.
     * If the MaxArrayDeque is empty, simply return null.*/
    public T max() {
        if (size() == 0) {
            return null;
        }
        T max = get(0);
        for (T cur : this) {
            if (comparator.compare(cur, max) > 0) {
                max = cur;
            }
        }
        return max;
    }

    /** returns the maximum element in the deque as governed by the parameter Comparator c.
     * If the MaxArrayDeque is empty, simply return null.*/
    public T max(Comparator<T> c) {
        if (size() == 0) {
            return null;
        }
        T max = get(0);
        for (T cur : this) {
            if (c.compare(cur, max) > 0) {
                max = cur;
            }
        }
        return max;
    }
}
