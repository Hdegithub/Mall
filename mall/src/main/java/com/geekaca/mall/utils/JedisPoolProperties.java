package com.geekaca.mall.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
//自动把Properties属性值注入进来到  类的属性
@ConfigurationProperties(prefix = "jedis.pool")
public class JedisPoolProperties {
    private int maxTotal;
    private int maxIdle;
    private int minIdle;

    private int redisPort;
    private String redisHost;
    private String redisPass;
    // 省略 getter 和 setter 方法

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getRedisPort() {
        return redisPort;
    }

    public void setRedisPort(int redisPort) {
        this.redisPort = redisPort;
    }

    public String getRedisHost() {
        return redisHost;
    }

    public void setRedisHost(String redisHost) {
        this.redisHost = redisHost;
    }

    public String getRedisPass() {
        return redisPass;
    }

    public void setRedisPass(String redisPass) {
        this.redisPass = redisPass;
    }
}
