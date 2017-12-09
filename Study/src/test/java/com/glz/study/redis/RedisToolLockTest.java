package com.glz.study.redis;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.glz.study.redis.dao.impl.UserDaoImp;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:application-context.xml")
public class RedisToolLockTest {
	
 	RedisToolLock redisToolLock = new RedisToolLock();
	
    @Autowired
    private JedisPool jedisPool;//注入JedisPool
	
	@Test
	public void testTryGetDistributedLock() {
		//fail("Not yet implemented");
	}

	@Test
	public void testReleaseDistributedLock() {
	    Jedis jedis = jedisPool.getResource();
	    String lockKey = "xxx";
	    String requestId=  UUID.randomUUID().toString();
	    int expireTime = 100000;
	    Boolean a = redisToolLock.tryGetDistributedLock(jedis, lockKey, requestId, expireTime);
		System.out.println(a);
	  //  redisToolLock.releaseDistributedLock(jedis, lockKey,requestId );
		jedis.close();
		 
	}

}
