package com.yunpan.manager.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yunpan.manager.annotantion.IdTransce;
import com.yunpan.manager.annotantion.Type;
@Table(name="sys_role")
public class Role extends BaseBean{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int role_id;
	@Column(name="role_name")
	private String role_name;
	@Column(name="role_desc")
	private String role_desc;
	@Column(name="auth_lists")
	private String auth_lists;

	public int getRole_id() {
		return role_id;
	}
	@IdTransce(Type.INTEGER)
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getRole_desc() {
		return role_desc;
	}

	public void setRole_desc(String role_desc) {
		this.role_desc = role_desc;
	}

	public String getAuth_lists() {
		return auth_lists;
	}

	public void setAuth_lists(String auth_lists) {
		this.auth_lists = auth_lists;
	}
	
	
	
}
