package com.yunpan.front.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.yunpan.front.interfaces.IdTransce;
import com.yunpan.front.interfaces.Type;
/**
 * 
 * @ClassName: FileMenusBean
 * @Description: 文件目录bean
 * @author duanjigui
 * @date 2017年4月29日 下午5:04:53
 *
 */
@Table(name="cus_file_menu")
public class FileMenusBean extends BaseBean{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer file_menu_id;
	@Column(name="flie_menu_name")
	private String flie_menu_name;
	@Column(name="parent_id")
	private Integer parent_id;
	@Column(name="virthalenv_path")
	private String virthalenv_path;
	@Column(name="is_delete")
	private Integer is_delete;
	
	@Transient
	private String fileType;  //文件类型
	
	@Transient
	private String size; //文件大小  -
	
	
	public Integer getFile_menu_id() {
		return file_menu_id;
	}
	@IdTransce(Type.INTEGER)
	public void setFile_menu_id(int file_menu_id) {
		this.file_menu_id = file_menu_id;
	}

	public String getFlie_menu_name() {
		return flie_menu_name;
	}

	public void setFlie_menu_name(String flie_menu_name) {
		this.flie_menu_name = flie_menu_name;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	public String getVirthalenv_path() {
		return virthalenv_path;
	}

	public void setVirthalenv_path(String virthalenv_path) {
		this.virthalenv_path = virthalenv_path;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public Integer getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}
	public void setFile_menu_id(Integer file_menu_id) {
		this.file_menu_id = file_menu_id;
	}
	
	
}
