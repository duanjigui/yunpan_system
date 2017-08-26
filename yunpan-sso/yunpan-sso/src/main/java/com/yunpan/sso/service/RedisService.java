package com.yunpan.sso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
/**
 * 
 * @ClassName: RedisService
 * @Description: 对于redis的一些常用操作
 * @author duanjigui
 * @date 2017年1月10日 下午3:52:56
 *
 */
@Service(value="redisService")
public class RedisService {
	@Autowired
	private ShardedJedisPool shardedJedisPool;
	
	public void set(String key,String value){
		ShardedJedis jedis = shardedJedisPool.getResource();
		jedis.set(key, value);
		shardedJedisPool.returnResourceObject(jedis);
	}
	
	public String get(String key){
		ShardedJedis jedis = shardedJedisPool.getResource();
		String value = jedis.get(key);
		shardedJedisPool.returnResourceObject(jedis);
		return value;
	}

	public void set(String key,String value,int seconds){
		ShardedJedis jedis = shardedJedisPool.getResource();
		jedis.set(key, value);
		jedis.expire(key,seconds);
		shardedJedisPool.returnResourceObject(jedis);
	}
	
	public boolean hasKey(String key,String prefix,String suffix){
		boolean is_exist=false;
		ShardedJedis jedis = shardedJedisPool.getResource();
		is_exist= jedis.exists(prefix+key+suffix);
		shardedJedisPool.returnResourceObject(jedis);
		return is_exist;
	}
	
	public void setExpire(String key,int seconds){
		ShardedJedis jedis = shardedJedisPool.getResource();
		jedis.expire(key,seconds);
		shardedJedisPool.returnResourceObject(jedis);
	}
	
	public void deletekey(String key){
		ShardedJedis jedis = shardedJedisPool.getResource();
		jedis.del(key);
		shardedJedisPool.returnResourceObject(jedis);
	}
}
