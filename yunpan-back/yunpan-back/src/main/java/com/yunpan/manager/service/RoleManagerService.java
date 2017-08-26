package com.yunpan.manager.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yunpan.manager.bean.Role;
@Service
public class RoleManagerService extends BaseService<Role>{
	@Value(value="${SERVER_URL}")
	private String SERVER_URL;
	
	public boolean deleteRoleByIds(String[] ids) {
		return super.deleteByIds(ids, Role.class);
	}

	public boolean saveRole(Role role, String token) {
		return super.saveInfo(role, token, SERVER_URL);
	}

	public boolean updateRole(Role role) {
		return super.updateInfo(role);
	}


}
