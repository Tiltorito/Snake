import java.util.ArrayList;

/**
 * Created by mpampis on 17/2/2017.
 */
public class Queue<E> {
    private ArrayList<E> list;

    public Queue() {
        list = new ArrayList<E>();
    }

    public E remove() {
        return list.remove(0);
    }

    public void add(E element) {
        list.add(element);
    }

    public E peek() {
        return list.get(0);
    }

    public int getSize() {
        return list.size();
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }
}
