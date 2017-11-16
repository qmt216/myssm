package com.yoosal.zqmh.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Properties;

/**
 * Created by 秦明涛 on 2016/1/4.
 * Desc redis客户端使用工具
 */
public class RedisUtil {
    private static JedisPool pool;

    //初始化
    public static void init(Properties bundle) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(Integer.valueOf(bundle.getProperty("redis.pool.maxIdle")));
        config.setTestOnBorrow(Boolean.valueOf(bundle.getProperty("redis.pool.testOnBorrow")));
        config.setTestOnReturn(Boolean.valueOf(bundle.getProperty("redis.pool.testOnReturn")));
        pool = new JedisPool(config, bundle.getProperty("redis.ip"), Integer.valueOf(bundle.getProperty("redis.port")));
    }

    /**
     * 保存字符串的值
     *
     * @param key   key
     * @param value 值
     * @return 状态码
     */
    public static String setValue(String key, String value) {
        Jedis jedis = pool.getResource();
        if (jedis == null) {
            return null;
        }
        try {
            return jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return null;
    }

    /**
     * 获取保存的字符串的值
     * @param key key
     * @return 获取到的值
     */
    public static String getValue(String key) {
        if (ValidUtils.isEmpty(key)) {
            return null;
        }
        Jedis jedis = pool.getResource();
        if (jedis == null) {
            return null;
        }
        try {
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return null;
    }

    /**
     *
     * @return
     */
//    public static String setList(){
//
//    }

}
