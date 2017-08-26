package com.yunpan.manager.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yunpan.manager.bean.CatelogeType;
@Service
public class CatelogeTypeService extends BaseService<CatelogeType>{
	@Value(value="${SERVER_URL}")
	private String SERVER_URL;
	
	public boolean deleteCatelogTypeByIds(String[] ids) {
		List<Integer> list=new ArrayList<>();
		for (String id : ids) {
			CatelogeType catelogeType=new CatelogeType();
			catelogeType.setCatelog_type_id(Integer.parseInt(id));
			List<CatelogeType> catelogeTypes = super.mapper.select(catelogeType);
			for (CatelogeType temp : catelogeTypes) {
				list.add(temp.getCatelog_type_id());
			}
		}
		for (Integer i : list) {
			CatelogeType catelogeType=new CatelogeType();
			catelogeType.setCatelog_type_id(i);
			catelogeType.setCatelog_type_name("空");
			catelogeType.setParent_id(0);
			catelogeType.setUpdate_time(new Date());
			super.mapper.updateByPrimaryKeySelective(catelogeType);
		}
		return super.deleteByIds(ids, CatelogeType.class);
	}

	public boolean savecatelogeType(CatelogeType catelogeType, String token) {
		catelogeType.setStatus(0);//默认不启用
		if (catelogeType.getParent_id() ==null) {
			catelogeType.setParent_id(0);
			catelogeType.setIs_parenty(0);
			catelogeType.setParent_name("空");
		}else{
			catelogeType.setIs_parenty(1);
		}
	
		return super.saveInfo(catelogeType, token, SERVER_URL);
	}

	public boolean updatecatelogeType(CatelogeType catelogeType) {
		catelogeType.setUpdate_time(new Date());
		return super.updateInfo(catelogeType);
	}

}
