package com.lagouedu.redishm;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import java.util.HashSet;
import java.util.Set;

public class ClusteraApp {
    private  static JedisCluster jedisCluster;

    public static void main(String[] args) {

            // 添加集群的服务节点Set集合
            Set<HostAndPort> hostPortsSet = new HashSet<HostAndPort>();
            // 添加节点
            hostPortsSet.add(new HostAndPort("192.168.139.151", 7001));
            hostPortsSet.add(new HostAndPort("192.168.139.151", 7002));
            hostPortsSet.add(new HostAndPort("192.168.139.151", 7003));
            hostPortsSet.add(new HostAndPort("192.168.139.151", 7004));
            hostPortsSet.add(new HostAndPort("192.168.139.151", 7005));
            hostPortsSet.add(new HostAndPort("192.168.139.151", 7006));

            // Jedis连接池配置
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            // 最大空闲连接数, 默认8个
            jedisPoolConfig.setMaxIdle(10);
            // 最大连接数, 默认8个
            jedisPoolConfig.setMaxTotal(50);
            //最小空闲连接数, 默认0
            jedisPoolConfig.setMinIdle(0);
            // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
            jedisPoolConfig.setMaxWaitMillis(3000); // 设置3秒
            //对拿到的connection进行validateObject校验
            jedisPoolConfig.setTestOnBorrow(true);
            jedisCluster = new JedisCluster(hostPortsSet, jedisPoolConfig);


        /**
         * 测试数据的插入与读取
         */
        //插入
        jedisCluster.set("test:name:01","keepgoing, the man of working for life");
        jedisCluster.set("test:password:01","you can't see me");

        //读取
        System.out.println(jedisCluster.get("test:name:01"));
        System.out.println(jedisCluster.get("test:password:01"));

        }

    }



