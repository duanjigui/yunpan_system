package com.yunpan.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yunpan.front.bean.ResourseBean;

import tk.mybatis.mapper.common.Mapper;

public interface ResourseManagerDao extends Mapper<ResourseBean>{

	 public List<ResourseBean> findResourceByExample(ResourseBean resourseBean);
	
	 public List<ResourseBean> fetchResourceByName(@Param(value="name")String name,@Param(value="creater")String creater);
}
