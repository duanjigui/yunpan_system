package com.yunpan.manager.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yunpan.manager.bean.Orangezation;
import com.yunpan.manager.bean.ResponeTableData;
import com.yunpan.manager.bean.User;
import com.yunpan.manager.dao.UserDao;

@Service
public class OrangzationService extends BaseService<Orangezation>{
	
	private Logger logger =LoggerFactory.getLogger(OrangzationService.class);
	@Autowired
	private UserDao userDao;
	@Value(value="${BASE_USER_URL}")
	private String SERCICE_URL;
	
	public ResponeTableData<Orangezation> fetchAllOrangzations() {
		return super.fetchAll();
	}

	public Orangezation fetchOrangzationById(String id) {
		return super.fetchById(id, Orangezation.class);
	}

	public boolean updateOrangzationById(Orangezation orangezation) {
		String organze_user_id = orangezation.getOrganze_user_id();
		User temp= new User();
		temp.setUser_id(organze_user_id);
		User result = userDao.selectByPrimaryKey(temp);
		logger.info(result.toString());
		orangezation.setOrganze_user_name(result.getUser_name());
		User temp2= new User();
		temp2.setUser_id(orangezation.getCreater());
		User result2=userDao.selectByPrimaryKey(temp2);
		orangezation.setCreater_name(result2.getUser_name());
		return super.updateInfo(orangezation);
	}

	public boolean deleteOrangzationByIds(String[] ids) {
		return super.deleteByIds(ids, Orangezation.class);
	}

	public boolean saveOrangezation(Orangezation orangezation,String token) {
		User user=new User();
		user.setUser_id(orangezation.getOrganze_user_id());
		User result= userDao.selectByPrimaryKey(user);
		orangezation.setOrganze_user_name(result.getUser_name());
		orangezation.setOrange_id((int)Math.random()*100 );
		return super.saveInfo(orangezation, token,SERCICE_URL+"info" );
	}

	
	
	
}
