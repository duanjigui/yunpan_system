package com.yunpan.front.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yunpan.front.bean.FileMenusBean;

import tk.mybatis.mapper.common.Mapper;

public interface FileMenuManagerDao extends Mapper<FileMenusBean>{
	
	public List<FileMenusBean> findMenuByExample(FileMenusBean fileMenusBean);

	public List<FileMenusBean> fetchFileMenuByName(@Param(value="name")String name,@Param(value="creater")String creater);
 
}
