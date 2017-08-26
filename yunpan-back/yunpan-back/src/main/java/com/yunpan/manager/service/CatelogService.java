package com.yunpan.manager.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yunpan.manager.bean.Cateloge;
@Service
public class CatelogService extends BaseService<Cateloge>{
	@Value(value="${SERVER_URL}")
	private  String SERVER_URL;
	
	public boolean deleteCatelogByIds(String[] ids) {
		return super.deleteByIds(ids, Cateloge.class);
	}

	public boolean saveContent(Cateloge cateloge, String token) {
		return super.saveInfo(cateloge, token, SERVER_URL);
	}

	public boolean updateContent(Cateloge cateloge) {
		cateloge.setUpdate_time(new Date());
		return super.updateInfo(cateloge);
	}

}
