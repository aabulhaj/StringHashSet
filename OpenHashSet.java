import java.util.ListIterator;

public class OpenHashSet extends SimpleHashSet {
    private LinkedListWrapper[] hashSet;


    public OpenHashSet() {
        this(DEFAULT_UPPER_LOAD_FACTOR, DEFAULT_LOWER_LOAD_FACTOR);
    }

    public OpenHashSet(String[] data) {
        this();

        for (String value : data) {
            add(value);
        }
    }

    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
        hashSet = new LinkedListWrapper[capacity];
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
    }

    @Override
    public boolean add(String newValue) {
        if (!contains(newValue) && add(newValue, hashSet)) {
            size += 1;
            increaseCapacityIfNecessary();
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(String searchVal) {
        return hashSet[valueIndex(searchVal)] != null && hashSet[valueIndex(searchVal)].contains(searchVal);
    }

    @Override
    public boolean delete(String toDelete) {
        int index = valueIndex(toDelete);
        if (hashSet[index] == null || !hashSet[index].contains(toDelete)) {
            return false;
        } else {
            hashSet[index].remove(toDelete);
        }
        size -= 1;
        decreaseCapacityIfNecessary();
        return true;
    }

    @Override
    protected void changeCapacity(int newCapacity) {
        capacity = newCapacity;
        LinkedListWrapper[] newHashSet = new LinkedListWrapper[capacity];
        for (int i = 0; i < hashSet.length; i++) {
            if (hashSet[i] != null) {
                ListIterator<String> listIterator = hashSet[i].listIterator();
                while (listIterator.hasNext()) {
                    add(listIterator.next(), newHashSet);
                }
            }
        }
        hashSet = newHashSet;
    }

    private int valueIndex(String value) {
        return hash(value) & (capacity - 1);
    }

    private boolean add(String newValue, LinkedListWrapper[] currHashSet) {
        if (newValue == null) {
            return false;
        }

        int index = valueIndex(newValue);
        if (currHashSet[index] == null) {
            currHashSet[index] = new LinkedListWrapper();
        } else if (currHashSet[index].contains(newValue)) {
            return false;
        }

        currHashSet[index].add(newValue);
        return true;
    }
}
