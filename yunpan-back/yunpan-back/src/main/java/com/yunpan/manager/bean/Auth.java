package com.yunpan.manager.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yunpan.manager.annotantion.IdTransce;
import com.yunpan.manager.annotantion.Type;
/**
 * 
 * @ClassName: Auth
 * @Description: 权限内容部分实体类
 * @author duanjigui
 * @date 2017年4月8日 下午6:25:40
 *
 */
@Table(name="sys_user_auth")
public class Auth extends BaseBean{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer auth_id;
	@Column(name="auth_name")
	private String auth_name;
	@Column(name="auth_desc")
	private String auth_desc;
	@Column(name="create_time")
	protected Date create_time;
	@Column(name="update_time")
	protected Date update_time;
	@Column(name="creater_name")
	protected String creater_name;
	@Column(name="creater")
	protected String creater;
	@Column(name="auth_identity")
	private String auth_identity;
	@Column(name="url")
	private String url;
	@Column(name="sort")
	private Integer sort;
	@Column(name="auth_p_id")
	private Integer auth_p_id;
	@Column(name="auth_p_name")
	private String auth_p_name;
	@Column(name="is_parent")
	private Integer is_parent;
	
	public String getAuth_p_name() {
		return auth_p_name;
	}
	public void setAuth_p_name(String auth_p_name) {
		this.auth_p_name = auth_p_name;
	}
	public Integer getAuth_id() {
		return auth_id;
	}
	@IdTransce(Type.INTEGER)
	public void setAuth_id(Integer auth_id) {
		this.auth_id = auth_id;
	}
	public String getAuth_name() {
		return auth_name;
	}
	public void setAuth_name(String auth_name) {
		this.auth_name = auth_name;
	}
	public String getAuth_desc() {
		return auth_desc;
	}
	public void setAuth_desc(String auth_desc) {
		this.auth_desc = auth_desc;
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
	public String getCreater_name() {
		return creater_name;
	}
	public void setCreater_name(String creater_name) {
		this.creater_name = creater_name;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getAuth_identity() {
		return auth_identity;
	}
	public void setAuth_identity(String auth_identity) {
		this.auth_identity = auth_identity;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getAuth_p_id() {
		return auth_p_id;
	}
	public void setAuth_p_id(Integer auth_p_id) {
		this.auth_p_id = auth_p_id;
	}
	public Integer getIs_parent() {
		return is_parent;
	}
	public void setIs_parent(Integer is_parent) {
		this.is_parent = is_parent;
	}
	@Override
	public String toString() {
		return "Auth [auth_id=" + auth_id + ", auth_name=" + auth_name + ", auth_desc=" + auth_desc + ", create_time="
				+ create_time + ", update_time=" + update_time + ", creater_name=" + creater_name + ", creater="
				+ creater + ", auth_identity=" + auth_identity + ", url=" + url + ", sort=" + sort + ", auth_p_id="
				+ auth_p_id + "]";
	}
	
}
