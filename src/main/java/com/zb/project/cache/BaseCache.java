package com.zb.project.cache;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Created by chengxs on 2017/06/16.
 */
public class BaseCache {
    //重试次数
    private static int MAX_RETRY = 5;
    @Autowired
    protected JedisPool jedisPool;

    public <T> T runInJedisPool(JedisRunnable<T> jedisRunnable) {
        Jedis jedis = null;

        for (int i = 0; i < MAX_RETRY; i++) {
            try {
                jedis = jedisPool.getResource();
                return jedisRunnable.runWithJedis(jedis);
            } catch (JedisConnectionException e ) {
                e.printStackTrace();
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        }
        return null;
    }

    public interface JedisRunnable<T> {
        T runWithJedis(Jedis jedis);
    }
}
