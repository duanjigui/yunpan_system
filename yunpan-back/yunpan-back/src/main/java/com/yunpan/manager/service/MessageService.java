package com.yunpan.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunpan.manager.bean.MessageBean;
import com.yunpan.manager.bean.User;
import com.yunpan.manager.dao.MessageDao;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
@Service
public class MessageService extends BaseService<MessageBean>{
	@Value(value="${SERVER_URL}")
	private String USER_INFO_URL;	
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	public List<MessageBean> fetchMessage(String token) {
		try {
			String message = httpService.getMessageFromUrl(USER_INFO_URL+"/"+token);
			if (message !=null) {
				User session = objectMapper.readValue(message,User.class);
				if (session != null) {
					String user_id = session.getUser_id();
					MessageDao messageDao= (MessageDao) super.mapper;
					Example example =new Example(MessageBean.class);
					Criteria criteria = example.createCriteria();
					criteria.andEqualTo("mes_is_delete", 0);
					criteria.andEqualTo("mes_send_user_id", user_id);
					example.orderBy("mes_leavl").asc();
					List<MessageBean> messageBeans = messageDao.selectByExample(example);
					return messageBeans;
				}
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean saveMessage(MessageBean messageBean, String token) {
		
		messageBean.setMes_is_delete(0);
		
		return this.saveInfo(messageBean, token, USER_INFO_URL);
	}

	public boolean updateMessage(MessageBean messageBean) {
		return this.updateInfo(messageBean);
	}

	public boolean deleteCatelogByIds(String[] ids) {
		int i=0;
		if (ids!=null && ids.length>0) {
			for (String id : ids) {
				MessageBean messageBean =new MessageBean();
				messageBean.setMessage_id(Integer.parseInt(id));
				i+=this.mapper.delete(messageBean);
				
			}
		}
		return i==ids.length;
	}

}
