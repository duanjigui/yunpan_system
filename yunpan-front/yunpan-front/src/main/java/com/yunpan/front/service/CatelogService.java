package com.yunpan.front.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yunpan.front.bean.Cateloge;

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

	public List<Cateloge> fetchIndexFootData(int i) {
		Cateloge cateloge=new Cateloge();
		cateloge.setCatelog_type_id(i);
		cateloge.setIs_delete(1);//这个标志位比较特殊，1表示启用。0表示禁用
		return this.mapper.select(cateloge);
	}

}
