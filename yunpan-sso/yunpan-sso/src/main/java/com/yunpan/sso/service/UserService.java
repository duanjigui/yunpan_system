package com.yunpan.sso.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunpan.sso.dao.UserInfoDao;
import com.yunpan.sso.pojo.RegisterUserInfo;
import com.yunpan.sso.pojo.User;

@Service(value="userService")
public class UserService {
	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private RedisService redisService;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	@Value("${EXPIRE_SECOND}")
	private int EXPIRE_SECOND;
	
	public Boolean fetchUserEmaillHasExist(String email){
		User user=new User();
		user.setUser_email(email);
		List<User> list = userInfoDao.select(user);
		if (list!=null&&list.size()==1) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 
	 *@author duanjigui
	 *@Description 
	 *		token值的生成规则：MD5（用户名+密码+当前时间+100以内的随机数）
	 *@date 2017年1月12日上午11:21:49
	 *@param email
	 *@param password
	 *@return
	 * @throws JsonProcessingException 
	 */
	public String updateEmailAndPassToLogin(String email, String password,String key) throws JsonProcessingException {
		
		User user=new User();
		user.setUser_email(email);
		//对密码进行md5加密
		Md5Hash hash=new Md5Hash(password, "", 1);
		user.setUser_pwd(hash.toString());
		User user_info = userInfoDao.selectOne(user);
		
		String token=null;
		
		if (user_info==null||user_info.getUser_id()==null) {
			return null;
		}else{
			User user2=new User();
			user2.setUser_id(user_info.getUser_id());
			user2.setLast_login_time(new Date());
			userInfoDao.updateByPrimaryKeySelective(user2);
			if (key!=null&&redisService.hasKey(key, "TOKEN_USER_", "")) {//当key值不为null，说明redis中存在数据
				return key;
			}else{//否则的话说明redis中不存在该用户的信息，重新进行set值
				Md5Hash hash2=new Md5Hash(email+password+System.currentTimeMillis()+Math.round(Math.random()*100), "", 1);
				token=hash2.toString();
				//使用md5进行加密token信息
				redisService.set("TOKEN_USER_"+token, objectMapper.writeValueAsString(user_info),EXPIRE_SECOND );
				return token;
			}
			
		}
		
	}
	
	public String fetchUserInfoByToken(String token) {
		if (!redisService.hasKey(token, "TOKEN_USER_", "")) {
			return null;
		}else {
			redisService.setExpire(token, EXPIRE_SECOND);//每当查询信息，则将session的值向后拖延
			return redisService.get("TOKEN_USER_"+token);
		}
		
	}
	public boolean saveRegiester(RegisterUserInfo userInfo) {
		User user=new User();
		user.setUser_id(UUID.randomUUID().toString().replaceAll("-", ""));
		user.setUser_email(userInfo.getEmaill());
		Md5Hash hash=new Md5Hash(userInfo.getPassword(), "", 1);
		user.setUser_pwd(hash.toString());
		user.setCreate_time(new Date());
		user.setUpdate_time(user.getCreate_time());
		user.setUser_phone(userInfo.getPhone());
		user.setUser_name(userInfo.getNickName());
		user.setIs_delete(0);
		int i = userInfoDao.insert(user);
		if (i==1) {
			return true;
		}else {
			return false;
		}
	}
	public boolean logout(String cookievalue) {
		if (redisService.hasKey(cookievalue, "TOKEN_USER_", "")) {
			redisService.deletekey("TOKEN_USER_"+cookievalue);
			return true;
		}
		return false;
	}
	
	
}
