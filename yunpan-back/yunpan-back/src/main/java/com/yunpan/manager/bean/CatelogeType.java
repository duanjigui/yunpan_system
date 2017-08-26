package com.yunpan.manager.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yunpan.manager.annotantion.IdTransce;
import com.yunpan.manager.annotantion.Type;

@Table(name="sys_catelog_type")
public class CatelogeType extends BaseBean{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer catelog_type_id;
	@Column(name="catelog_type_name")
	private String catelog_type_name;
	@Column(name="parent_id")
	private Integer parent_id;
	@Column(name="sort")
	private Integer  sort;
	@Column(name="is_parenty")
	private Integer is_parenty;
	@Column(name="status")
	private Integer status;
	@Column(name="parent_name")
	private String parent_name;

	public String getParent_name() {
		return parent_name;
	}
	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}
	public Integer getCatelog_type_id() {
		return catelog_type_id;
	}
	@IdTransce(Type.INTEGER)
	public void setCatelog_type_id(Integer catelog_type_id) {
		this.catelog_type_id = catelog_type_id;
	}

	public String getCatelog_type_name() {
		return catelog_type_name;
	}

	public void setCatelog_type_name(String catelog_type_name) {
		this.catelog_type_name = catelog_type_name;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getIs_parenty() {
		return is_parenty;
	}

	public void setIs_parenty(Integer is_parenty) {
		this.is_parenty = is_parenty;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
}
