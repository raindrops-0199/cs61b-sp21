package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
    private Comparator<T> comparator;

    /** creates a MaxArrayDeque with the given Comparator. */
    public MaxArrayDeque(Comparator<T> c){
        super();
        comparator = c;
    }

    /** returns the maximum element in the deque as governed by the previously given Comparator.
     * If the MaxArrayDeque is empty, simply return null.*/
    public T max(){
        if (size == 0){
            return null;
        }
        T max = get(0);
        Iterator<T> iter = iterator();
        while (iter.hasNext()){
            T cur = iter.next();
            if (comparator.compare(cur, max) > 0){
                max = cur;
            }
        }
        return max;
    }

    /** returns the maximum element in the deque as governed by the parameter Comparator c.
     * If the MaxArrayDeque is empty, simply return null.*/
    public T max(Comparator<T> c){
        if (size == 0){
            return null;
        }
        T max = get(0);
        Iterator<T> iter = iterator();
        while (iter.hasNext()){
            T cur = iter.next();
            if (c.compare(cur, max) > 0){
                max = cur;
            }
        }
        return max;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof ArrayDeque){
            ArrayDeque<T> target = (MaxArrayDeque<T>) o;
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
