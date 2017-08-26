package com.yunpan.manager.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.util.StringUtil;
import com.yunpan.manager.bean.ResponeTableData;
import com.yunpan.manager.bean.Role;
import com.yunpan.manager.bean.TreeNodeEnity;
import com.yunpan.manager.bean.User;
import com.yunpan.manager.dao.UserDao;
import com.yunpan.manager.util.CookieUtill;
import com.yunpan.manager.util.ListGenerator;

@Controller
public class UserService extends BaseService<User>{
	@Autowired
	private HttpService httpService;
	
	@Autowired
	private UserDao userDao;
	
	//服务端访问地址
	@Value(value="${BASE_USER_URL}")
	private String SERCICE_URL;
	
	//解析json
	private ObjectMapper objectMapper=new ObjectMapper();
	
	@Autowired
	private RoleManagerService roleManagerService;
	
	/**
	 * 
	 *@author duanjigui
	 *@Description 获取用户的信息
	 *		具体步骤：（1）根据邮箱和密码获取 ，是否拥有该用户可以登录
	 *			        （2）根据cookie值去获取用户的信息
	 *			        （3）只要用户类型不为0，用户即可登录后台管理系统
	 *
	 *@date 2017年2月17日上午10:36:07
	 *@param user
	 *@return
	 */
	public String fetchInfoByEmailAndPwd(User user,HttpServletRequest request,HttpServletResponse response) {
		try {
			String login_message = httpService.getMessageFromUrl(SERCICE_URL+"login?emaill="+user.getUser_email()+"&password="+user.getUser_pwd());
			Map<String,String> value = objectMapper.readValue(login_message, Map.class);
			if (value.get("message").equals("账号或者密码出错！")) {
				return value.get("message");
			}else if (value.get("message").equals("登录成功！")) {
				String token = value.get("token");//当前token值
				//因为使用的是httpclient，cookie并不会保留到客户端，所以需要手动的进行添加
				CookieUtill.setCookie(response, "TOKEN_SSO", token, 30*60);
				if (token != null) {
					String userinfo = httpService.getMessageFromUrl(SERCICE_URL+"info/"+token);
					char user_type = userinfo.split("\"user_type\":")[1].charAt(0);
					if (user_type!='0') {
						return userinfo;
					}else {
						return "该账户没有权限登录系统！";
					}
				}
			}else {
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 *@author duanjigui
	 *@Description 获取当前用户的token值
	 *@date 2017年2月17日上午11:10:07
	 *@param request
	 *@return
	 */
	private String fetchToken(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("TOKEN_SSO")) {
				return cookie.getValue();
			}
		}
		return null;
	}
	/**
	 * 
	 *@author duanjigui
	 *@Description 注销
	 *@date 2017年2月18日上午10:32:09
	 *@return
	 * @throws Exception 
	 * @throws ClientProtocolException 
	 */
	public boolean logout() throws ClientProtocolException, Exception {
		String message = httpService.getMessageFromUrl(SERCICE_URL+"logout");
		if (message.equals("注销成功！")) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 
	 *@author duanjigui
	 *@Description 获取所有用户的信息
	 *@date 2017年2月22日上午10:37:02
	 *@return
	 */
	public ResponeTableData<User> fetchAllUser() {
		return super.fetchAll();
	}
	public boolean saveUser(User user,String cookievalue) {
		if (cookievalue!=null) {
			Md5Hash hash=new Md5Hash("123456", "", 1);//默认密码
			user.setUser_pwd(hash.toString());
			user.setUser_id(UUID.randomUUID().toString().replaceAll("-", ""));//用户id
			user.setIs_delete(0);
			return super.saveInfo(user, cookievalue, SERCICE_URL+"info");
		}
		return false;
	}
	/**
	 * 
	 *@author duanjigui
	 *@Description 根据用户Id删除用户
	 *@date 2017年3月4日下午2:22:46
	 *@param ids
	 *@return
	 */
	public boolean deleteUserByIds(String[] ids) {
		return super.deleteByIds(ids, User.class);
		
	}
	/**
	 * 
	 *@author duanjigui
	 *@Description 根据id查询用户信息
	 *@date 2017年3月4日下午4:24:00
	 *@param id
	 *@return
	 */
	public User fetchUserInfoById(String id) {
		return super.fetchById(id, User.class);
	}
	/**
	 * 
	 *@author duanjigui
	 *@Description 根据主键修改用户信息
	 *@date 2017年3月4日下午4:24:27
	 *@param user
	 *@return
	 */
	public boolean updateUser(User user) {
		return super.updateInfo(user);
	}
	
	
	/**
	 * 
	 *@author duanjigui
	 *@Description 更新密码操作
	 *				1.先判断原密码是否相同
	 *				2.相同在执行更新操作，否则报错
	 *@date 2017年4月17日下午9:38:26
	 *@param token
	 *@param o_pwd 旧密码
	 *@param n_pwd 新密码
	 *@return
	 */
	public Map<String, Object> updatePasswd(String token, String o_pwd, String n_pwd) {
		Map<String, Object> map=new HashMap<>();
		try {
			String message = httpService.getMessageFromUrl(SERCICE_URL+"/info"+"/"+token);
			String user_id=null;
			if (message !=null) {
				User session = objectMapper.readValue(message,User.class);
				if (session != null) {
					user_id=session.getUser_id();
				}else{
					map.put("status", "404");
					map.put("message", "错误：未知用户！");
					return map;
				}
			}else {
				map.put("status", "404");
				map.put("message", "错误：未知用户！");
				return map;
			}
			
			if (user_id==null) {
				map.put("status", "404");
				map.put("message", "错误：未知用户！");
				return map;
			}else {
				Md5Hash hash=new Md5Hash(o_pwd, "", 1);
				User user=new User();
				user.setUser_id(user_id);
				User temp = userDao.selectByPrimaryKey(user);
				if (temp.getUser_pwd().equals(hash.toString())) {
					Md5Hash hash2=new Md5Hash(n_pwd, "", 1);
					temp.setUser_pwd(hash2.toString());
					temp.setUpdate_time(new Date());
					int i = userDao.updateByPrimaryKeySelective(temp);
					if (i==1) {
						map.put("status", "200");
						map.put("message", "更新成功！");
						return map;
					}else {
						map.put("status", "500");
						map.put("message", "更新密码出现错误！");
						return map;
					}
				}else {
					map.put("status", "410");
					map.put("message", "输入的原密码不正确！");
					return map;
				}
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
			map.put("status", "500");
			map.put("message", "错误：服务器json转换异常！");
			return map;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			map.put("status", "500");
			map.put("message", "错误：服务器json转换异常！");
			return map;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			map.put("status", "500");
			map.put("message", "错误：服务器json转换异常！");
			return map;
		} catch (IOException e) {
			e.printStackTrace();
			map.put("status", "500");
			map.put("message", "错误：服务器IO异常！");
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "500");
			map.put("message", "错误：服务器未知异常！");
			return map;
		}
	}
	
	
	public List<TreeNodeEnity> fectchRolelist(String pid) {
		try {
			List<TreeNodeEnity> list=new ArrayList<>();
			User user=new User();
			user.setUser_id(pid);
			User temp = super.mapper.selectByPrimaryKey(user);//查询用户的信息
			String role_lists = temp.getRole_lists();
			if (StringUtil.isNotEmpty(role_lists)) {
				String[] lists = ListGenerator.read(role_lists);
				if (lists!=null &&lists.length>0) {
					//查询所有的role列表
					List<Role> roles = roleManagerService.fetchAll().getRows();
					if (roles!=null &&roles.size()>0) {
						for (Role role : roles) {
							TreeNodeEnity treeNodeEnity=new TreeNodeEnity();
							treeNodeEnity.setId(role.getRole_id());
							treeNodeEnity.setName(role.getRole_name());
							for (String s : lists) {
								if (role.getRole_id()==Integer.parseInt(s)) {
									treeNodeEnity.setChecked(true);
								}else {
									treeNodeEnity.setChecked(false);
								}
							}
							treeNodeEnity.setParent(false);
							list.add(treeNodeEnity);
						}
					}
					
				}
				return list;
			}else {
				//查询所有的role列表
				List<Role> roles = roleManagerService.fetchAll().getRows();
				if (roles!=null &&roles.size()>0) {
					for (Role role : roles) {
						TreeNodeEnity treeNodeEnity=new TreeNodeEnity();
						treeNodeEnity.setId(role.getRole_id());
						treeNodeEnity.setName(role.getRole_name());
						treeNodeEnity.setChecked(false);
						treeNodeEnity.setParent(false);
						list.add(treeNodeEnity);
					}
				}
				return list;
			}
		} catch (NumberFormatException e) {
			return null;
		}
	
	}
	
	public boolean updateRoleInfo(String[] ids, String pid) {
		User user=new User();
		user.setUser_id(pid);
		user.setRole_lists(ListGenerator.write(ids));
		return this.updateInfo(user);
	}
	public List<TreeNodeEnity> fetchUserRoleInfo(String pid) {
		List<TreeNodeEnity>list=new ArrayList<>();
		User user = super.fetchById(pid, User.class);
		String role_lists = user.getRole_lists();
		if (StringUtil.isNotEmpty(role_lists)) {
			String[] temp = ListGenerator.read(role_lists);
			if (temp!=null &&temp.length>0) {
				 List<Role> roles = roleManagerService.fetchAll().getRows();
					for (Role role : roles) {
						for (String s : temp) {
							if (role.getRole_id()==Integer.parseInt(s)) {
								TreeNodeEnity treeNodeEnity=new TreeNodeEnity();
								treeNodeEnity.setId(role.getRole_id());
								treeNodeEnity.setName(role.getRole_name());
								treeNodeEnity.setParent(false);
								list.add(treeNodeEnity);
							}
						}
						
						
					}
			}
			
			return list;
			
		}
		
		return null;
	}
	
	
}
