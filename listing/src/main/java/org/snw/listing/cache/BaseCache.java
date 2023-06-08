package org.snw.listing.cache;

public interface BaseCache<K, T> {

    T get(K key);

    void put(K key, T value);

    void remove(K key);

    void cleanup();
}
