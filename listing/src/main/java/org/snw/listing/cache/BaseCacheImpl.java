package org.snw.listing.cache;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BaseCacheImpl<K, T> implements BaseCache<K, T> {

    private final long timeToLive;

    private final Map<K, CacheObject> cacheMap;

    protected class CacheObject {

        public long lastAccessed;

        public T value;

        protected CacheObject(T value) {
            this.value = value;
            this.lastAccessed = System.currentTimeMillis();
        }
    }


    public BaseCacheImpl(long timeToLive, long timeInterval) {
        this.timeToLive = timeToLive * 1000;
        cacheMap = new ConcurrentHashMap<>();
        if (timeToLive > 0 && timeInterval > 0) {
            Thread cacheLooper = new Thread(() -> { // Runnable Class
                while (true) {
                    try {
                        Thread.sleep(timeInterval * 1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    cleanup();
                }
            });

            cacheLooper.setDaemon(true);
            cacheLooper.start();
        }
    }

    @Override
    public T get(K key) {
        CacheObject cacheObject = (CacheObject) cacheMap.get(key);
        if (cacheObject == null)
            return null;
        else {
            cacheObject.lastAccessed = System.currentTimeMillis();
            return cacheObject.value;
        }
    }

    @Override
    public void put(K key, T value) {
        cacheMap.put(key, new CacheObject(value));
    }

    @Override
    public void remove(K key) {
        cacheMap.remove(key);
    }

    @Override
    public void cleanup() {
        long now = System.currentTimeMillis();
        CacheObject cachedObject = null;
        for (K key : cacheMap.keySet()) {
            cachedObject = cacheMap.get(key);
            if (cachedObject != null && (now > (timeToLive + cachedObject.lastAccessed))) {
                cacheMap.remove(key);
            }
        }
    }
}
