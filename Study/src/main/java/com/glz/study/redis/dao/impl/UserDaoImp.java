package com.glz.study.redis.dao.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import com.glz.study.redis.dao.IUserDao;
import com.glz.study.redis.vo.User;

@Component
public class UserDaoImp implements IUserDao {
	@Autowired
	StringRedisTemplate redisTemplate;

 	public boolean add(User user) {
		// 使用最基本的方式
		/*
		 * redisTemplate.execute(new RedisCallback<Object>() {
		 * 
		 * @Override public Object doInRedis(RedisConnection connection) throws
		 * DataAccessException {
		 * connection.set(redisTemplate.getStringSerializer().serialize(user.
		 * getUsername()),
		 * redisTemplate.getStringSerializer().serialize(user.getPassword()));
		 * return null; } });
		 */
		// 利用了StringRdisTemplate的特性
		// redisTemplate.opsForValue().set(user.getUsername(),
		// user.getPassword());

		// 利用了StringRdisTemplate的特性 通过绑定的方式
		BoundValueOperations<String, String> bound = redisTemplate.boundValueOps(user.getUsername());
		bound.set(user.getPassword());
		// bound.append(user.getPassword());//追加，和StringBuilder的append一样功能
		return true;
	}

 	public boolean delete(String key) {
		try {
			redisTemplate.delete(key);
			System.out.println(key + " 删除成功。");
		} catch (Exception e) {
			return false;
		}
		return true;
	}

 	public boolean update(User user) {
		ValueOperations<String, String> ops = redisTemplate.opsForValue();
		if (ops.get(user.getUsername()) != null) {
			ops.set(user.getUsername(), user.getUsername());
			return true;
		}
		System.out.println("没有此用户！" + user.getUsername());
		return false;
	}

	public String get(String name) {
		return redisTemplate.opsForValue().get(name);
	}
	
    public Long incr(String key, long liveTime) {
       
    	RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
    	Long increment = entityIdCounter.getAndIncrement();

        if ((null == increment || increment.longValue() == 0) && liveTime > 0) {//初始设置过期时间
            entityIdCounter.expire(liveTime, TimeUnit.SECONDS);
        }

        return increment;
    }
}