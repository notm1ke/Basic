package co.m1ke.basic.utils.container.triplet;

public class ImmutableTriplet<K, V, H> {

    private K k;
    private V v;
    private H h;

    public ImmutableTriplet(K k, V v, H h) {
        this.k = k;
        this.v = v;
        this.h = h;
    }

    public static <K, V, H> Triplet<K, V, H> construct(K k, V v, H h) {
        return new Triplet<>(k, v, h);
    }

    public K getK() {
        return k;
    }

    public V getV() {
        return v;
    }

    public H getH() {
        return h;
    }
}
