package com.yunpan.manager.service;

import org.springframework.stereotype.Service;

import com.yunpan.manager.bean.ResourseBean;

@Service
public class ResourseService extends BaseService<ResourseBean>{

	public boolean deleteResources(String[] ids) {
		try {
				int i=0;
				if (ids !=null && ids.length >0) {
					for (String id : ids) {
							ResourseBean resourseBean=new ResourseBean();
							resourseBean.setResource_id(Integer.parseInt(id));
							i+=super.mapper.delete(resourseBean) ;
					}
				}
				if (i==ids.length) {
					return true;
				}else {
					return false;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return false;
			}
			
	}

	
	
}
