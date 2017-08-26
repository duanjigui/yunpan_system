package com.yunpan.front.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yunpan.front.interfaces.IdTransce;
import com.yunpan.front.interfaces.Type;


@Table(name="sys_catelog")
public class Cateloge extends BaseBean{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer catelog_id;
	@Column(name="catelog_type_id")
	private Integer catelog_type_id;
	@Column(name="title")
	private String title;
	@Column(name="is_delete")
	private int is_delete;
	@Column(name="content")
	private String content;
	@Column(name="catelog_type_name")
	private String catelog_type_name;
	
	public String getCatelog_type_name() {
		return catelog_type_name;
	}
	public void setCatelog_type_name(String catelog_type_name) {
		this.catelog_type_name = catelog_type_name;
	}
	public Integer getCatelog_id() {
		return catelog_id;
	}
	@IdTransce(Type.INTEGER)
	public void setCatelog_id(Integer catelog_id) {
		this.catelog_id = catelog_id;
	}

	public Integer getCatelog_type_id() {
		return catelog_type_id;
	}

	public void setCatelog_type_id(Integer catelog_type_id) {
		this.catelog_type_id = catelog_type_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(int is_delete) {
		this.is_delete = is_delete;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
}
