package com.yunpan.manager.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yunpan.manager.annotantion.IdTransce;
import com.yunpan.manager.annotantion.Type;

@Table(name="sys_message")
public class MessageBean extends BaseBean{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer message_id;
	@Column(name="mes_send_group_id")
	private Integer mes_send_group_id;
	@Column(name="mes_send_group_name")
	private String mes_send_group_name;
	@Column(name="mes_send_user_id")
	private String mes_send_user_id;
	@Column(name="mes_content")
	private String mes_content;
	@Column(name="mes_send_user_name")
	private String mes_send_user_name;
	@Column(name="mes_end_time")
	private Date mes_end_time;
	@Column(name="mes_leavl")
	private Integer mes_leavl;
	@Column(name="mes_is_delete")
	private Integer mes_is_delete;

	public Integer getMessage_id() {
		return message_id;
	}
	@IdTransce(Type.INTEGER)
	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}

	public Integer getMes_send_group_id() {
		return mes_send_group_id;
	}

	public void setMes_send_group_id(Integer mes_send_group_id) {
		this.mes_send_group_id = mes_send_group_id;
	}

	public String getMes_send_group_name() {
		return mes_send_group_name;
	}

	public void setMes_send_group_name(String mes_send_group_name) {
		this.mes_send_group_name = mes_send_group_name;
	}

	public String getMes_send_user_id() {
		return mes_send_user_id;
	}

	public void setMes_send_user_id(String mes_send_user_id) {
		this.mes_send_user_id = mes_send_user_id;
	}

	public String getMes_content() {
		return mes_content;
	}

	public void setMes_content(String mes_content) {
		this.mes_content = mes_content;
	}

	public String getMes_send_user_name() {
		return mes_send_user_name;
	}

	public void setMes_send_user_name(String mes_send_user_name) {
		this.mes_send_user_name = mes_send_user_name;
	}

	public Date getMes_end_time() {
		return mes_end_time;
	}

	public void setMes_end_time(Date mes_end_time) {
		this.mes_end_time = mes_end_time;
	}

	public Integer getMes_leavl() {
		return mes_leavl;
	}

	public void setMes_leavl(Integer mes_leavl) {
		this.mes_leavl = mes_leavl;
	}

	public Integer getMes_is_delete() {
		return mes_is_delete;
	}

	public void setMes_is_delete(Integer mes_is_delete) {
		this.mes_is_delete = mes_is_delete;
	}
	
	
}
