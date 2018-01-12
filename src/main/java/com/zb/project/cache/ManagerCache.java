package com.zb.project.cache;


import com.zb.project.util.JsonUtil;
import com.zb.project.util.TimeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * Created by Chengxs on 2017/6/19.
 */
@Service
public class ManagerCache extends BaseCache {
    private static Logger logger = LoggerFactory.getLogger(ManagerCache.class);



    public void setLoginCache(final String sessionId, final Manager manager) {
        runInJedisPool(new JedisRunnable<Object>() {
            @Override
            public Object runWithJedis(Jedis jedis) {
                String k = getKey(sessionId);
                jedis.set(k, JsonUtil.parse(manager));
                jedis.expire(k, TimeService.DAY_IN_SECOND);
                return null;
            }
        });
    }

    private String getKey(String sessionId) {
        return "ManagerCache:" + sessionId;
    }

    public String getManager(final String sessionId) {
        runInJedisPool(new JedisRunnable<Object>() {
            @Override
            public Object runWithJedis(Jedis jedis) {
                String k = getKey(sessionId);
                String json = jedis.get(k);
                return json;
            }
        });
        return "";
    }


    public void delLoginCache(final String sessionId) {
        runInJedisPool(new JedisRunnable<Object>() {
            @Override
            public Object runWithJedis(Jedis jedis) {
                jedis.del(getKey(sessionId));
                return null;
            }
        });
    }
}
