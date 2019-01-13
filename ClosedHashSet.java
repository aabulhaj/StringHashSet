public class ClosedHashSet extends SimpleHashSet {
    private static String EMPTY_STRING = "EMPTY_STRING";
    private String[] hashSet;


    public ClosedHashSet() {
        this(DEFAULT_UPPER_LOAD_FACTOR, DEFAULT_LOWER_LOAD_FACTOR);
    }

    public ClosedHashSet(String[] data) {
        this();

        for (String value : data) {
            add(value);
        }
    }

    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor) {
        hashSet = new String[capacity];
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
        return getValueIndex(searchVal) != -1;
    }

    @Override
    public boolean delete(String toDelete) {
        int index = getValueIndex(toDelete);
        if (index == -1) {
            return false;
        }

        hashSet[index] = EMPTY_STRING;
        size -= 1;
        decreaseCapacityIfNecessary();
        return true;
    }

    @Override
    protected void changeCapacity(int newCapacity) {
        capacity = newCapacity;
        String[] newHashSet = new String[capacity];
        for (String value : hashSet) {
            if (value != null && value != EMPTY_STRING) {
                add(value, newHashSet);
            }
        }
        hashSet = newHashSet;
    }

    /**
     * Assumes that the given value is not in the given hashSet.
     */
    private boolean add(String newValue, String[] hashSet) {
        int i = 0;
        while (true) {
            int hashIndex = getHashIndex(newValue, i);
            if (hashSet[hashIndex] == null || hashSet[hashIndex] == EMPTY_STRING) {
                hashSet[hashIndex] = newValue;
                return true;
            } else if (hashSet[hashIndex].equals(newValue) && hashSet[hashIndex] != EMPTY_STRING) {
                return false;
            }
            i += 1;
        }
    }

    private int getValueIndex(String value) {
        int i = 0;
        while (true) {
            int hashIndex = getHashIndex(value, i);
            if (hashSet[hashIndex] == null) {
                return -1;
            } else if (hashSet[hashIndex].equals(value) && hashSet[hashIndex] != EMPTY_STRING) {
                return hashIndex;
            }
            i += 1;
        }
    }

    private int getHashIndex(String value, int i) {
        return (hash(value) + (i + (i * i)) / 2) & (capacity - 1);
    }
}
