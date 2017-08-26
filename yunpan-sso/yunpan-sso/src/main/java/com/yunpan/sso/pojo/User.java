package com.yunpan.sso.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name="sys_user")
public class User implements Serializable{
	@Id
	@Column(name="user_id")
	private String user_id;
	
	@Column(name="user_email")
	private String user_email;
	@JsonIgnore
	@Column(name="user_pwd")
	private String user_pwd;
	
	@Column(name="user_name")
	private String user_name;
	
	@Column(name="user_type")
	private Integer user_type;
	
	@Column(name="create_time")
	private Date create_time;
	
	@Column(name="update_time")
	private Date update_time;
	
	@Column(name="user_orginze_id")
	private String user_orginze_id;
	
	@Column(name="user_orginze_name")
	private String user_orginze_name;
	
	@Column(name="user_phone")
	private String user_phone;
	
	@Column(name="user_head_url")
	private String user_head_url;
	
	@Column(name="user_back_templete")
	private Integer user_back_templete;
	
	@Column(name="last_login_time")
	private Date last_login_time;
	
	@Column(name="is_delete")
	private Integer is_delete;
	
	@Column(name="templete_url")
	private String templete_url;
	
	@Column(name="other")
	private String other;

	@Column(name="role_lists")
	private String role_lists;
	
	@Column(name="creater_name")
	private String creater_name;
	@JsonProperty(value="creater")
	@Column(name="creater")
	private String create_id;
	
	public String getCreater_name() {
		return creater_name;
	}

	public void setCreater_name(String creater_name) {
		this.creater_name = creater_name;
	}

	public String getCreate_id() {
		return create_id;
	}

	public void setCreate_id(String create_id) {
		this.create_id = create_id;
	}

	public String getRole_lists() {
		return role_lists;
	}

	public void setRole_lists(String role_lists) {
		this.role_lists = role_lists;
	}
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Integer getUser_type() {
		return user_type;
	}

	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public String getUser_orginze_id() {
		return user_orginze_id;
	}

	public void setUser_orginze_id(String user_orginze_id) {
		this.user_orginze_id = user_orginze_id;
	}

	public String getUser_orginze_name() {
		return user_orginze_name;
	}

	public void setUser_orginze_name(String user_orginze_name) {
		this.user_orginze_name = user_orginze_name;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_head_url() {
		return user_head_url;
	}

	public void setUser_head_url(String user_head_url) {
		this.user_head_url = user_head_url;
	}

	public Integer getUser_back_templete() {
		return user_back_templete;
	}

	public void setUser_back_templete(Integer user_back_templete) {
		this.user_back_templete = user_back_templete;
	}

	public Date getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}

	public Integer getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}

	public String getTemplete_url() {
		return templete_url;
	}

	public void setTemplete_url(String templete_url) {
		this.templete_url = templete_url;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_email=" + user_email + ", user_pwd=" + user_pwd + ", user_name="
				+ user_name + ", user_type=" + user_type + ", create_time=" + create_time + ", update_time="
				+ update_time + ", user_orginze_id=" + user_orginze_id + ", user_orginze_name=" + user_orginze_name
				+ ", user_phone=" + user_phone + ", user_head_url=" + user_head_url + ", user_back_templete="
				+ user_back_templete + ", last_login_time=" + last_login_time + ", is_delete=" + is_delete
				+ ", templete_url=" + templete_url + ", other=" + other + "]";
	}
	
}
