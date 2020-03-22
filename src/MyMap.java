import java.util.*;

public class MyMap<K, V> extends AbstractMap<K, V> implements Map<K,V> {

    private K[] keys;
    private V[] values;
    int size;

    public static class SimpleEntry<K, V> implements Entry<K,V> {

        private SimpleEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        K key;
        V value;

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V pastValue = this.value;
            this.value = value;
            return pastValue;
        }
    }

    public MyMap() {
        size = 0;
        keys = (K[]) new Object[1000];
        values = (V[]) new Object[1000];
    }

    public MyMap(K key, V value) {
        size = 1;
        keys = (K[]) new Object[1000];
        values = (V[]) new Object[1000];
        keys[0] = key;
        values[0] = value;
    }

    @Override
    public boolean containsKey(Object key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < size; i++) {
            if (values.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MyMap<?, ?> myMap = (MyMap<?, ?>) o;
        return size == myMap.size &&
                Arrays.equals(keys, myMap.keys) &&
                Arrays.equals(values, myMap.values);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), size);
        result = 31 * result + Arrays.hashCode(keys);
        result = 31 * result + Arrays.hashCode(values);
        return result;
    }

    @Override
    public boolean isEmpty() {
        return size > 0;
    }

    @Override
    public V put(K key, V value) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return value;
            }
        }
        keys[size] = key;
        values[size++] = value;
        return value;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (int i = 0; i < size; i++) {
            if (!m.containsKey(keys[i])) {
                values[size] = m.get(keys[size - 1]);
                size++;

            }
        }
    }

    @Override
    public boolean remove(Object key, Object value) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                keys[i] = keys[size - 1];
                keys[size - 1] = null;
                values[i] = values[size - 1];
                values[size--] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < size; i++) {
            str =str.concat(keys[i] + " = " + values[i] + ", ");
        }
        return str;
    }

    @Override
    public Collection<V> values() {
        return new LinkedList<>(Arrays.asList(values).subList(0, size));
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new LinkedHashSet<>();
        for (int i = 0; i < size; i++) {
            set.add(new SimpleEntry<>(keys[i], values[i]));
        }
        return set;
    }

    @Override
    public Set<K> keySet() {
        return new HashSet<>(Arrays.asList(this.keys).subList(0, size));
    }

    @Override
    public V get(Object key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return null;
    }

    @Override
    public void clear() {
        keys = null;
        values = null;
        size = 0;
    }
}

