package com.yunpan.manager.service;

import java.util.Date;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunpan.manager.bean.LoggerBean;
import com.yunpan.manager.bean.User;
import com.yunpan.manager.dao.UserDao;
@Service
public class LoggerService extends BaseService<LoggerBean>{
	@Autowired
	private UserDao userDao;
	
	public void saveloginLoggerInfo(User user){
		LoggerBean loggerBean=new LoggerBean();
		Md5Hash hash=new Md5Hash(user.getUser_pwd(),"", 1);
		user.setUser_pwd(hash.toString());
		User temp = userDao.selectOne(user);
		loggerBean.setCreater(temp.getUser_id());
		loggerBean.setCreater_name(temp.getUser_name());
		loggerBean.setLog_leavel("1");
		loggerBean.setLog_content_type("登录");
		loggerBean.setLog_content(user.getUser_email()+"登录后台管理系统");
		loggerBean.setCreate_time(new Date());
		loggerBean.setUpdate_time(loggerBean.getCreate_time());
		super.mapper.insert(loggerBean);
	}
	
	
}
