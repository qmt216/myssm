package com.yoosal.zqmh.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CacheUtil {
    private static CacheManager cacheManager;

    /**
     * 需要在Init Servlet中加载一次该方法
     * @param file
     */
    public static void loadCacheConfig(String file) {
        if (cacheManager == null) {
            synchronized (CacheUtil.class) {
                if (cacheManager == null) {
                    cacheManager = CacheManager.create(file);
                }
            }
        }
    }

    public static void loadCacheConfig(InputStream inputStream) {
        if (cacheManager == null) {
            synchronized (CacheUtil.class) {
                if (cacheManager == null) {
                    cacheManager = CacheManager.create(inputStream);
                }
            }
        }
    }

    /**
     * 获取到ehcache的manager
     *
     * @return
     */
    public static CacheManager getCacheManager() {
        return cacheManager;
    }

    /**
     * 线程安全的方法。
     * 获取缓存中的一个key对应的值，注意的是，如果过期的话，则返回null。
     *
     * @param group
     * @param key
     * @param suffix key的后缀，用于group内再分group
     * @return
     */
    public static Object get(String group, String key, String suffix) {
        final Cache cache = cacheManager.getCache(group);
        if (cache == null)
            return null;

        key = getKey(key);
        if (suffix != null) {
            key = key + "_" + suffix;
        }

        final Element e = cache.get(key);
        if (e != null) {
            if (!e.isExpired()) {
                return e.getObjectValue();
            }
        }
        return null;
    }

    public static Object get(String group, String key) {
        return get(group, key, null);
    }

    /**
     * 往缓存中添加一个记录
     *
     * @param group
     * @param key
     * @param obj
     */
    public static void put(String group, String key, Serializable obj, String suffix) {
        if (obj == null) return;

        final Cache cache = cacheManager.getCache(group);
        if (cache == null) return;

        key = getKey(key);
        if (suffix != null) {
            key = key + "_" + suffix;
        }

        Element e = new Element(key, obj);
        /**
         * 是否每个元素都要给延长一个随机过期时间，避免都在某个时间段集中过期。
         */
        CacheConfiguration cacheConfig = cache.getCacheConfiguration();

        int ttl = (int) cacheConfig.getTimeToLiveSeconds() + RAND.nextInt(MAX_DElAY_SECOND);
        e.setTimeToLive(ttl);
        cache.put(e);
    }

    public static void put(String group, String key, Serializable obj) {
        put(group, key, obj, null);
    }

    public static void delete(String group, String key, String suffix) {
        final Cache cache = cacheManager.getCache(group);
        if (cache == null) return;
        key = getKey(key);
        if (suffix != null) {
            key = key + "_" + suffix;
        }

        cache.remove(key);
    }

    public static void delete(String group, String key) {
        delete(group, key, null);
    }

    public static Random RAND = new Random(System.currentTimeMillis());
    public static int MAX_DElAY_SECOND = 120; //2分钟

    /**
     * 注意：只清除一级缓存中的内容，且为延时清理。
     * 二级缓存中的内容由后台负责只清理一次。使用的方式为先从各个服务器中收集key，然后都清理掉
     *
     * @param group
     */
    public static void flushGroup(String group, String suffix, int maxDelaySeconds) {
        final Cache cache = cacheManager.getCache(group);
        if (cache == null) return;

        List<Object> keys = cache.getKeys();
        for (Object key : keys) {
            if (suffix != null) {
                if (!((String) key).endsWith(suffix)) {
                    continue;
                }
            }
            Element e = cache.get(key);
            if (e != null) {
                e.setTimeToLive(RAND.nextInt(maxDelaySeconds));
            }
        }
    }

    public static void flushGroup(String group, int maxDelaySeconds) {
        flushGroup(group, null, maxDelaySeconds);
    }

    public static void flushGroup(String group, String suffix) {
        flushGroup(group, suffix, MAX_DElAY_SECOND);
    }

    public static void flushGroup(String group) {
        flushGroup(group, MAX_DElAY_SECOND);
    }


    /**
     * 获得一个缓存组所有的key
     *
     * @param group
     * @return
     */
    public static List<String> getKeys(String group, String suffix) {
        final Cache cache = cacheManager.getCache(group);
        if (cache == null) {
            return null;
        }
        if (suffix == null) {
            return (List<String>) cache.getKeys();
        }

        List<Object> keys = cache.getKeys();
        List<String> list = new ArrayList<String>();
        for (Object key : keys) {
            if (!((String) key).endsWith(suffix)) {
                continue;
            }
            list.add((String) key);
        }
        return list;
    }

    public static List<String> getKeys(String group) {
        return getKeys(group, null);
    }

    /**
     * 如果是整数，则不做md5计算直接返回整数作为其key
     *
     * @param key
     * @return
     */
    public static final int MAX_KEY_LENGTH = 100;

    public static final String getKey(String key) {
        try {
            Long.parseLong(key);
            return key;
        } catch (Exception e) {
        }
        if (key.length() > MAX_KEY_LENGTH) {
            return MD5.getMD5ofString(key);
        }
        return key;
    }

    public static void main(String args[]) {

    }
}
