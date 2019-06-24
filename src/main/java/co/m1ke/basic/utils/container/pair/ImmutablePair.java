package co.m1ke.basic.utils.container.pair;

public class ImmutablePair<K, V> {

    private K k;
    private V v;

    public ImmutablePair(K k, V v) {
        this.k = k;
        this.v = v;
    }

    public static <T, F> Pair<T, F> construct(T t, F v) {
        return new Pair<>(t, v);
    }

    public K getK() {
        return k;
    }

    public V getV() {
        return v;
    }

}
