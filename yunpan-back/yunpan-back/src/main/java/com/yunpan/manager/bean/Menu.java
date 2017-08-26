package com.yunpan.manager.bean;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @ClassName: Menu
 * @Description: 菜单传输实体类
 * @author duanjigui
 * @date 2017年4月8日 下午6:03:28
 *
 */
public class Menu implements Serializable{
	
	private static final long serialVersionUID = -4985215090028873014L;

	private String text;
	
	private String url;
	
	private boolean isexpand;
	
	private List<Menu> children;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isIsexpand() {
		return isexpand;
	}

	public void setIsexpand(boolean isexpand) {
		this.isexpand = isexpand;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Menu [text=" + text + ", url=" + url + ", isexpand=" + isexpand + ", children=" + children + "]";
	}
	
	
}
