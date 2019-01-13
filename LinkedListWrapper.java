import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListWrapper {
    private LinkedList<String> linkedList;

    public LinkedListWrapper() {
        linkedList = new LinkedList<>();
    }

    public boolean add(String value) {
        return linkedList.add(value);
    }

    public boolean contains(String value) {
        return linkedList.contains(value);
    }

    public boolean remove(String value) {
        return linkedList.remove(value);
    }

    public ListIterator<String> listIterator() {
        return linkedList.listIterator();
    }
}
