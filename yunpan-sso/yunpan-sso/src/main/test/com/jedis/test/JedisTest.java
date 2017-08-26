package com.jedis.test;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yunpan.sso.service.RedisService;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class JedisTest {
	/**
	 * 
	 *@author duanjigui
	 *@Description 测试jedis连接池是否连接成功
	 *@date 2017年1月10日上午10:58:39
	 */
	@Test
	public void testJedisPool(){
		ApplicationContext context=new ClassPathXmlApplicationContext("classpath:/config/applicationContext.xml");
		ShardedJedisPool pool= (ShardedJedisPool) context.getBean("shardedJedisPool");
		ShardedJedis shardedJedis = pool.getResource();
		shardedJedis.set("k2", "20");
		Assert.assertEquals("20",shardedJedis.get("k2"));
		pool.returnResourceObject(shardedJedis);
	}
	/**
	 *@author duanjigui
	 *@Description 测试设置指定时间内的key值是否成功 
	 *@date 2017年1月10日下午4:25:46
	 */
	@Test
	public void testJedisServiceSet(){
		ApplicationContext context=new ClassPathXmlApplicationContext("classpath:/config/applicationContext.xml");
		RedisService redisService=	(RedisService) context.getBean("redisService");
		redisService.set("k11", "10", 20);
		Assert.assertEquals("10",redisService.get("k11"));
	}
	/**
	 * 
	 *@author duanjigui
	 *@Description 测试获取指定时间内的key值是否成功
	 *@date 2017年1月10日下午4:28:27
	 */
	@Test
	public void testJedisServiceGet(){
		ApplicationContext context=new ClassPathXmlApplicationContext("classpath:/config/applicationContext.xml");
		RedisService redisService=	(RedisService) context.getBean("redisService");
		String val = redisService.get("k11");
		System.out.println(val);
	}
	
}
