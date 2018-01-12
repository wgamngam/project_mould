package com.zb.project.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.net.URL;

/**
 * Created by Chengxs on 2017/7/4.
 */
public class EhcacheUtil {

    private static final String path = "/ehcache.xml";
    private static final String userCache = "userCache";
    private URL url;

    private CacheManager manager;

    private static EhcacheUtil ehCache;

    private EhcacheUtil(String path) {
        url = getClass().getResource(path);
        manager = CacheManager.create(url);
    }

    public static EhcacheUtil getInstance() {
        if (ehCache == null) {
            ehCache = new EhcacheUtil(path);
        }
        return ehCache;
    }

    public void put(String key, Object value) {
        Cache cache = manager.getCache(userCache);
        Element element = new Element(key, value);
        cache.put(element);
    }

    public Object get(String key) {
        Cache cache = manager.getCache(userCache);
        Element element = cache.get(key);
        return element == null ? null : element.getObjectValue();
    }

    public Cache get() {
        return manager.getCache(userCache);
    }

    public void remove(String key) {
        Cache cache = manager.getCache(userCache);
        cache.remove(key);
    }
}
