package com.yunpan.manager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yunpan.manager.bean.Auth;

import tk.mybatis.mapper.common.Mapper;

public interface AuthManagerDao extends Mapper<Auth>{

	public List<Integer> findChildAuthId(@Param(value="id")int id); 
	
	public List<Auth> findchildAuthInfo(@Param(value="id")int id); 
	
}
