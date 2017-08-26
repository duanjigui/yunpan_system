package com.yunpan.manager.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.util.StringUtil;
import com.yunpan.manager.bean.Auth;
import com.yunpan.manager.bean.Cache;
import com.yunpan.manager.bean.Role;
import com.yunpan.manager.bean.TreeNodeEnity;
import com.yunpan.manager.bean.User;
import com.yunpan.manager.dao.AuthManagerDao;
import com.yunpan.manager.util.ListGenerator;

@Service
public class AuthManagerService extends BaseService<Auth>{
	
	@Value(value="${SERVER_URL}")
	private String SERVER_URL;
	
	@Autowired
	private RoleManagerService roleManagerService;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	public List<Auth> fetchAllAuths() {
		return super.fetchAll().getRows();
	}

	public boolean deleteAuthByIds(String[] ids) {
		//首先查询要删除的id,是否具有子权限id
		List<String> result=new ArrayList<>();
		for (String id : ids) {
			queryDirectChildAuth(id, result);
		}
		//将级联的子目录设置其父目录为空
		if (result.size()>0) {
			for (String id : result) {
				Auth temp=new Auth();
				temp.setAuth_id(Integer.parseInt(id));
				temp.setAuth_p_id(0);
				temp.setAuth_p_name("无");
				temp.setUpdate_time(new Date());
				super.mapper.updateByPrimaryKeySelective(temp);
			}
		}
		return super.deleteByIds(ids, Auth.class);
	}

	public Auth fetchAuthById(String id) {
		return super.fetchById(id, Auth.class);
	}

	public boolean saveAuth(Auth auth, String token) {
		auth.setAuth_id(0);
		auth.setIs_parent(0);
		int p_id = auth.getAuth_p_id();
		if (p_id!=0) {
			Auth temp=new Auth();
			temp.setAuth_id(p_id);
			temp.setIs_parent(1);
			this.updateAuth(temp);
		}
		return super.saveInfo(auth, token, SERVER_URL);
	}

	public boolean updateAuth(Auth auth) {
		auth.setUpdate_time(new Date());
		return super.updateInfo(auth);
	}
	/**
	 * 
	 *@author duanjigui
	 *@Description 根据父权限id查询子权限[级联查询子菜单权限]
	 *@date 2017年4月11日下午9:25:04
	 *@param id
	 *@param result
	 */
	public void queryChildAuth(String id,List<String> result){
		AuthManagerDao dao=(AuthManagerDao) super.mapper;
		List<Integer> auths = dao.findChildAuthId(Integer.parseInt(id));
		if (auths !=null &&auths.size()>0) {
			for (Integer auth : auths) {
				result.add(String.valueOf(auth));
				queryChildAuth(String.valueOf(auth), result);
			}
		}else {
			return;
		}
	}
	
	/**
	 * 
	 *@author duanjigui
	 *@Description 只查询其下的直接子菜单
	 *@date 2017年4月11日下午9:31:12
	 *@param id
	 *@param result
	 */
	public void queryDirectChildAuth(String id,List<String> result){
		AuthManagerDao dao=(AuthManagerDao) super.mapper;
		List<Integer> auths = dao.findChildAuthId(Integer.parseInt(id));
		if (auths !=null &&auths.size()>0) {
			for (Integer auth : auths) {
				result.add(String.valueOf(auth));
			}
		}else {
			return;
		}
	}

	public List<TreeNodeEnity> listAuths(String id,String pid) {
		
		List<TreeNodeEnity> treeNodeEnities=new ArrayList<>();
		
		Role role = roleManagerService.fetchById(pid, Role.class);//获取该用户的权限信息
		//然后查询权限列表，将所有的权限显示出来，但所具有的的权限用对勾表示
		
		String auth_lists = role.getAuth_lists();//获取用户的权限列表
		
		if (StringUtil.isNotEmpty(auth_lists)) {
			String[] lists = ListGenerator.read(auth_lists);//解析成权限列表
			if (lists !=null &&lists.length>0 ) {
				//查询所有的权限
				AuthManagerDao authManagerDao= (AuthManagerDao)this.mapper;
				 List<Auth> auths = authManagerDao.findchildAuthInfo(Integer.parseInt(id));
				if (auths !=null &&auths.size()>0) {
					for (Auth temp : auths) {
						TreeNodeEnity treeNodeEnity=new TreeNodeEnity();
						treeNodeEnity.setId(temp.getAuth_id());
						treeNodeEnity.setName(temp.getAuth_name());
							for(String s:lists){
								if (s.equals(String.valueOf(temp.getAuth_id()))) {
									treeNodeEnity.setChecked(true);
									break ;
								}else {
									treeNodeEnity.setChecked(false);
								}
								
							}
							if (temp.getIs_parent()==0) {
								treeNodeEnity.setParent(false);
							}else {
								treeNodeEnity.setParent(true);
							}	
						treeNodeEnities.add(treeNodeEnity);	
					}
					return treeNodeEnities;
				}else {
					return null;
				}
				
			}
		}else{
			//查询所有的权限
			AuthManagerDao authManagerDao= (AuthManagerDao)this.mapper;
			List<Auth> auths = authManagerDao.findchildAuthInfo(Integer.parseInt(id));
			if (auths !=null &&auths.size()>0) {
				for (Auth temp : auths) {
					TreeNodeEnity treeNodeEnity=new TreeNodeEnity();
					treeNodeEnity.setId(temp.getAuth_id());
					treeNodeEnity.setName(temp.getAuth_name());
					treeNodeEnity.setChecked(false);
					if (temp.getIs_parent()==0) {
						treeNodeEnity.setParent(false);
					}else {
						treeNodeEnity.setParent(true);
					}
					treeNodeEnities.add(treeNodeEnity);	
				}
				return treeNodeEnities;
		}
		}
		return null;
		
	}

	public boolean updateRoleAuths(String[] ids,String pid) {
		try {
			Role role=new Role();
			role.setRole_id(Integer.parseInt(pid));
			role.setAuth_lists(ListGenerator.write(ids));
			boolean flag=this.roleManagerService.updateRole(role);
			return flag;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 
	 *@author duanjigui
	 *@Description 获取角色的权限
	 *@date 2017年4月23日下午6:11:21
	 *@param id
	 *@param pid
	 *@return
	 */
	public List<TreeNodeEnity> listroleAuths(String id, String pid) {
		
		List<TreeNodeEnity> treeNodeEnities=new ArrayList<>();
		
		Role role = roleManagerService.fetchById(pid, Role.class);//获取该用户的权限信息
		//然后查询权限列表，将所有的权限显示出来，但所具有的的权限用对勾表示
		
		String auth_lists = role.getAuth_lists();//获取用户的权限列表
		if (StringUtil.isNotEmpty(auth_lists)) {
			String[] lists = ListGenerator.read(auth_lists);//解析成权限列表
			if (lists !=null &&lists.length>0 ) {
				//查询所有的权限
				AuthManagerDao authManagerDao= (AuthManagerDao)this.mapper;
				 List<Auth> auths = authManagerDao.findchildAuthInfo(Integer.parseInt(id));
				if (auths !=null &&auths.size()>0) {
					for(String s:lists){
						for (Auth temp : auths) {
								if (s.equals(String.valueOf(temp.getAuth_id()))) {
									TreeNodeEnity treeNodeEnity=new TreeNodeEnity();
									treeNodeEnity.setId(temp.getAuth_id());
									treeNodeEnity.setName(temp.getAuth_name());
									if (temp.getIs_parent()==0) {
										treeNodeEnity.setParent(false);
									}else {
										treeNodeEnity.setParent(true);
									}
									treeNodeEnities.add(treeNodeEnity);	
									break ;
								}
								
							}
							
					}
				}
			}
					
			return treeNodeEnities;
		}
		return null;
	}
	
	/**
	 * 
	 *@author duanjigui
	 *@Description 获取一级菜单
	 *@date 2017年6月3日上午9:53:36
	 *@param token
	 *@return
	 */
	public List<Auth> fifterFirstMenuByUser(User user){
		Set<String> auths = fetchUserAllAuth(user);
		Cache.authCache.put(user.getUser_id(), auths);//缓存到内存中
		Auth record=new Auth();
		record.setAuth_p_id(0);
		List<Auth> list = this.mapper.select(record);//查询一级菜单权限
		//查询菜单获取父权限id为null的菜单项
		List<Auth> firstmenu=new ArrayList<>();
		if (auths!=null &&auths.size()>0 && list !=null && list.size()>0) {
			for (Auth auth : list) {
				for (String temp : auths) {
					if (String.valueOf(auth.getAuth_id()).equals(temp)) {
						firstmenu.add(auth);
						continue;
					}
				}
			}
		}
		return firstmenu;
	}
	
	/**
	 * 
	 *@author duanjigui
	 *@Description 获取用户所有的权限列表
	 *@date 2017年6月3日上午9:40:14
	 *@param token
	 *@return
	 */
	public Set<String> fetchUserAllAuth(User user){
		try {
			Set<String> set =new HashSet<>();
				String role_lists = user.getRole_lists();
				if (StringUtils.isNotBlank(role_lists)) {
					String[] lits = ListGenerator.read(role_lists);//获取用户所具有的角色
					if (lits != null && lits.length>0) {
						for (String role : lits) {//根据角色获取权限
							Role r = roleManagerService.fetchById(role, Role.class);//具体角色
							String auth_lists = r.getAuth_lists();
							if (StringUtils.isNotBlank(auth_lists)) {
								String[] auths = ListGenerator.read(auth_lists);//获取用户所具有的角色
								if (auths !=null && auths.length>0) {
									for (String auth : auths) {
										set.add(auth);
									}
								}
							}
						}
					}
					return set;
				}else {
					return null;
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Auth> fetchAuthByUserIdAndParentId(String pid, String userid) {
		try {
			if (Cache.authCache.containsKey(userid)) {
				Set<String> auths = (Set<String>) Cache.authCache.get(userid);//获取用户的权限
				//查询获取指定pid的子权限列表
				List<String> result=new ArrayList<>();
				List<Auth> temp=new ArrayList<>();
				List<Auth> real=new ArrayList<>();
				queryDirectChildAuth(pid,result);
				if (result !=null && result.size() > 0) {
					for (String authid : result) {
						temp.add(this.fetchById(authid, Auth.class));	
					}
					for (Auth auth : temp) {
						for (String l : auths) {
							if (String.valueOf(auth.getAuth_id()).equals(l)) {
								real.add(auth);	
								continue;
							}
						}
					}
				}
				return real;
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
