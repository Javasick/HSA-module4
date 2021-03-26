package com.projector.probabilisticcache.cache;

import com.projector.probabilisticcache.model.IdEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruCache extends LinkedHashMap<Integer, IdEntity> {

    private static final Logger log = LoggerFactory.getLogger(LruCache.class);
    private static final int LIMIT = 1000;
    private int maxSize;
    private int counter = 0;

    public LruCache(int capacity) {
        super(capacity, 0.75f, true);
        this.maxSize = capacity;
    }

    @Override
    public IdEntity get(Object key) {
        counter++;
        double val = -counter * Math.log(Math.random());
        log.info("Counter {}, Formula {}", counter, val);
        if (val > LIMIT) {
            return null;
        }
        return super.get(key);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, IdEntity> eldest) {
        return this.size() > maxSize;
    }
}
