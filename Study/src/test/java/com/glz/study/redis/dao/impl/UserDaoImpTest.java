package com.glz.study.redis.dao.impl;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.glz.study.redis.vo.User;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:application-context.xml")
public class UserDaoImpTest {

	@Autowired
    UserDaoImp userDao;
    @Test
    public void redis(){
        //添加
        userDao.add(new User("hgs","123456"));
        //更新
        //userDao.update(new User("hgs","qwe"));
        //查询
        System.out.println(userDao.get("hgs"));
        //删除
        //userDao.delete("hgs");
    }

}
