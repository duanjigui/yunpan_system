package com.yunpan.manager.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yunpan.manager.annotantion.IdTransce;
/**
 * 
 * @ClassName: Orangezation
 * @Description: 用户组织实体类
 * @author duanjigui
 * @date 2017年4月4日 上午10:00:04
 *
 */
@Table(name="sys_orange")
public class Orangezation extends BaseBean{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orange_id;//组织id
	@Column(name="orange_name")
	private String orange_name;//组织name
	@Column(name="creater")
	private String creater; //创建人id
	@Column(name="creater_name")
	private String creater_name;//创建者name
	@Column(name="create_time")
	private Date create_time;//创建时间
	@Column(name="update_time")
	private Date update_time;//更新时间
	@Column(name="organze_user_id")
	private String organze_user_id;//组织拥有者id
	@Column(name="organze_user_name")
	private String organze_user_name;//组织拥有者name

	public int getOrange_id() {
		return orange_id;
	}
	@IdTransce
	public void setOrange_id(int orange_id) {
		this.orange_id = orange_id;
	}

	public String getOrange_name() {
		return orange_name;
	}

	public void setOrange_name(String orange_name) {
		this.orange_name = orange_name;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getCreater_name() {
		return creater_name;
	}

	public void setCreater_name(String creater_name) {
		this.creater_name = creater_name;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getOrganze_user_id() {
		return organze_user_id;
	}

	public void setOrganze_user_id(String organze_user_id) {
		this.organze_user_id = organze_user_id;
	}

	public String getOrganze_user_name() {
		return organze_user_name;
	}

	public void setOrganze_user_name(String organze_user_name) {
		this.organze_user_name = organze_user_name;
	}
	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	@Override
	public String toString() {
		return "Orangezation [orange_id=" + orange_id + ", orange_name=" + orange_name + ", creater=" + creater
				+ ", creater_name=" + creater_name + ", create_time=" + create_time + ", update_time=" + update_time
				+ ", organze_user_id=" + organze_user_id + ", organze_user_name=" + organze_user_name + "]";
	}
	
	
}
