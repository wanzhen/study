package com.glz.study.redis.dao;

import com.glz.study.redis.vo.User;

public interface IUserDao {
	
	boolean add(User user);

	boolean delete(String key);

	boolean update(User user);
	
	String get(String name);
	
    public Long incr(String key, long liveTime);
    
}
