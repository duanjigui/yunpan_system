package com.yunpan.manager.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @ClassName: TreeNodeEnity
 * @Description: 树形节点通用实体类
 * @author duanjigui
 * @date 2017年4月23日 上午11:45:20
 *
 */
public class TreeNodeEnity {
	
	private int id;
	
	private String name;
	
	private boolean checked;
	
	@JsonProperty(value="isParent")
	private boolean isParent;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isParent() {
		return isParent;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	
}
