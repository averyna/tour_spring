package edu.olya.tour.utils.cache.impl;

import edu.olya.tour.utils.cache.Cache;

import java.util.HashMap;
import java.util.Map;

public class CacheImpl implements Cache {

    private Map<String, Object> cacheMap = new HashMap();
//    private Map<String, ExpirationValue> cacheMap = new HashMap();


    @Override
    public void put(String key, Object result) {
        cacheMap.put(key,result);
    }

    @Override
    public <T> T get(String key) {
        return (T) cacheMap.get(key);
    }

    public Object getValue(String key){
        return cacheMap.get(key);
    }
//    public Object getValue(String key){
//        return cacheMap.get(key).value;
//    }

//    private static class ExpirationValue {
//        private final long expiredAt;
//
//        public Object value;
//
//        ExpirationValue(long expirationTime, Object value) {
//            this.expiredAt = expirationTime;
//            this.value = value;
//        }
//    }
}
