public abstract class SimpleHashSet implements SimpleSet {
    protected static final float DEFAULT_UPPER_LOAD_FACTOR = 0.75f;
    protected static final float DEFAULT_LOWER_LOAD_FACTOR = 0.25f;

    protected float upperLoadFactor;
    protected float lowerLoadFactor;

    protected int size = 0;
    protected int capacity = 16;

    public int size() {
        return size;
    }

    protected int hash(String value) {
        return value != null ? value.hashCode() : 0;
    }

    protected double getCurrentLoadFactor() {
        return (float) size() / capacity;
    }

    protected void increaseCapacityIfNecessary() {
        if (getCurrentLoadFactor() > upperLoadFactor) {
            changeCapacity(capacity * 2);
        }
    }

    protected void decreaseCapacityIfNecessary() {
        if (getCurrentLoadFactor() < lowerLoadFactor) {
            changeCapacity(Math.max(1, (int) (capacity * 0.5)));
        }
    }

    protected abstract void changeCapacity(int newCapacity);
}
