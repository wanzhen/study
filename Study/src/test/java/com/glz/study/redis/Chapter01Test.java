package com.glz.study.redis;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class Chapter01Test {

	@Autowired
	private JedisPool jedisPool;// 注入JedisPool
	private static final int ONE_WEEK_IN_SECONDS = 7 * 86400;
	private static final int VOTE_SCORE = 432;
	private static final int ARTICLES_PER_PAGE = 25;

	@Test
	public void test() {
		Jedis conn = jedisPool.getResource();

		conn.select(15);

		String articleId = postArticle(conn, "username", "A title", "http://www.google.com");

        System.out.println("We posted a new article with id: " + articleId);
        
        Map<String,String> articleData = conn.hgetAll("article:"+ articleId);
        	
        
        for (Map.Entry<String, String> entry : articleData.entrySet()) {
			System.out.println("  " + entry.getKey() + ": " + entry.getValue());
		}
  
        
        
        
	}

	public String postArticle(Jedis conn, String user, String title, String link) {

		String articleId = String.valueOf(conn.incr("article:"));

		String voted = "voted:" + articleId;

		conn.sadd(voted, user);
		
		conn.expire(voted, ONE_WEEK_IN_SECONDS);

	    long now = System.currentTimeMillis() / 1000;
       
	    String article = "article:" + articleId;
          
	    HashMap<String,String> articleData = new HashMap<String,String>();
        
	    articleData.put("title", title);
        articleData.put("link", link);
        articleData.put("user", user);
        articleData.put("now", String.valueOf(now));
        articleData.put("votes", "1");
        
        conn.hmset(article, articleData);
        
        conn.zadd("score:", now + VOTE_SCORE, article);
        
        conn.zadd("time:", now, article);
	    
		
		return articleId;
	}
	
	public List<Map<String,String>> getArticles(Jedis conn,int page){
		
		int start = (page -1 ) * ARTICLES_PER_PAGE;
		
		int end = start + ARTICLES_PER_PAGE -1 ;
        
		List<Map<String,String>> articles = new ArrayList<Map<String,String>>();

		Set<String> ids = conn.zrevrange("score:", start, end);

		for (String id : ids) {
			Map<String,String> articleData =   conn.hgetAll(id);
			articleData.put("id", id);
			articles.add(articleData);
		}
		return articles;
		
	}
	
	public void articleVote(Jedis conn, String user, String article){
		
		long cutoff = (System.currentTimeMillis() / 1000) - ONE_WEEK_IN_SECONDS;;
		
		if(conn.zscore("time:", article) < cutoff){
			return;
		}
		
		String articleId = article.substring(article.indexOf(":")+1);
		
		if (conn.sadd("voted:"+articleId, user) == 1 ) {
			conn.zincrby("score", VOTE_SCORE, article);
			conn.hincrBy(article, "votes", 1);
		}
	}

}
