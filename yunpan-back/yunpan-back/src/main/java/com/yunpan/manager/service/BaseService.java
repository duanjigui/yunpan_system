package com.yunpan.manager.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunpan.manager.annotantion.IdTransce;
import com.yunpan.manager.annotantion.Type;
import com.yunpan.manager.bean.BaseBean;
import com.yunpan.manager.bean.ResponeTableData;
import com.yunpan.manager.bean.User;

import tk.mybatis.mapper.common.Mapper;

public class BaseService <T extends BaseBean>{
	
	@Autowired
	protected Mapper<T> mapper;
	
	@Autowired
	protected HttpService httpService;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	/**
	 * 
	 *@author duanjigui
	 *@Description 查询所有的数据集合
	 *@date 2017年4月9日上午10:40:41
	 *@return
	 */
	public ResponeTableData<T> fetchAll(){
		List<T> list = mapper.selectAll();
		ResponeTableData<T> responeTableData=new ResponeTableData<>();
		responeTableData.setRows(list);
		responeTableData.setTotal(list.size());
		return responeTableData;
	}
	/**
	 * 
	 *@author duanjigui
	 *@Description 根据id查询信息
	 *@date 2017年4月9日上午11:03:36
	 *@param id
	 *@param type
	 *@return
	 */
	public T fetchById(String id,Class<T> type){
		try {
			T t = type.newInstance();
			Method[] methods = type.getDeclaredMethods();
			for (Method method : methods) {
				if (method.isAnnotationPresent(IdTransce.class)) {
					IdTransce annotation = method.getAnnotation(IdTransce.class);
					if (annotation.value() == Type.STRING) {
						method.invoke(t, id);
					}else {
						method.invoke(t, Integer.parseInt(id));
					}
				}
			}
			return mapper.selectByPrimaryKey(t);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 *@author duanjigui
	 *@Description 插入一条记录
	 *@date 2017年4月9日上午11:20:44
	 *@param t
	 *@param token
	 *@param url
	 *@return
	 */
	public boolean saveInfo(T t,String token,String url){
		try {
			t.setCreate_time(new Date());
			t.setUpdate_time(t.getCreate_time());
			String message = httpService.getMessageFromUrl(url+"/"+token);
			if (message !=null) {
				User session = objectMapper.readValue(message,User.class);
				if (session != null) {
					t.setCreater(session.getUser_id());
					t.setCreater_name(session.getUser_name());
				}else{
					return false;
				}
			}else {
				return false;
			}
			int i = mapper.insert(t);
			if (i==1) {
				return true;
			}else {
				return false;	
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
			return false;	
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return false;	
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return false;	
		} catch (IOException e) {
			e.printStackTrace();
			return false;	
		} catch (Exception e) {
			e.printStackTrace();
			return false;	
		}
		
	}
	/**
	 * 
	 *@author duanjigui
	 *@Description 根据id删除信息
	 *@date 2017年4月9日上午11:25:31
	 *@param ids
	 *@param type
	 *@return
	 */
	public boolean deleteByIds(String [] ids,Class<T> type){
		try {
			int k=0;
			for (int i = 0; i < ids.length; i++) {
				T t = type.newInstance();
				Method[] methods = type.getDeclaredMethods();
				for (Method method : methods) {
					if (method.isAnnotationPresent(IdTransce.class)) {
						IdTransce annotation = method.getAnnotation(IdTransce.class);
						if (annotation.value() == Type.STRING) {
							method.invoke(t, ids[i]);
						}else {
							method.invoke(t, Integer.parseInt(ids[i]));
						}
					}
				}
				k+=mapper.deleteByPrimaryKey(t);
			}
			if (k==ids.length) {
				return true;
			}else {
				return false;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		} catch (InstantiationException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	 *@author duanjigui
	 *@Description 更新用户信息
	 *@date 2017年4月9日上午11:31:56
	 *@param t
	 *@return
	 */
	public boolean updateInfo(T t){
		t.setUpdate_time(new Date());
		int i = mapper.updateByPrimaryKeySelective(t);
		if (i==1) {
			return true;
		}else {
			return false;
		}
	}
}

