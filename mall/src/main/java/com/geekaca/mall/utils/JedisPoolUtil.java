package com.geekaca.mall.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 工具类
 */
@Component
public class JedisPoolUtil {
    private static JedisPool jedisPool;


    //能够自动注入 方法需要的对象
    @Autowired
    public JedisPoolUtil(com.geekaca.mall.utils.JedisPoolProperties poolProperties) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(poolProperties.getMaxTotal());
        poolConfig.setMaxIdle(poolProperties.getMaxIdle());
        poolConfig.setMinIdle(poolProperties.getMinIdle());

        // 从application.properties文件中读取Redis服务器地址和端口号

        //连接超时 时间 单位毫秒
        jedisPool = new JedisPool(poolConfig, poolProperties.getRedisHost(), poolProperties.getRedisPort(), 10*1000, poolProperties.getRedisPass());

    }

    // 获取Jedis实例
    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

    // 归还Jedis实例到连接池
    public static void returnJedis(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    public static void del(String key){
        try(Jedis jedis = getJedis();){
            jedis.del(key);
        }

    }
}
