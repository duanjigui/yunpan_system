package com.yunpan.front.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.yunpan.front.enums.FileType;
import com.yunpan.front.interfaces.IdTransce;
import com.yunpan.front.interfaces.Type;

@Table(name="cus_resourse")
public class ResourseBean extends BaseBean{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer resource_id;
	@Column(name="resource_name")
	private String resource_name;
	@Column(name="resource_type")
	private String resource_type;
	@Column(name="resource_size")
	private Integer resource_size;
	@Column(name="is_catalog")
	private Integer is_catalog;
	@Column(name="resource_url")
	private String resource_url;
	@Column(name="res_true_name")
	private String res_true_name;
	@Column(name="is_delete")
	private Integer is_delete;
	@Column(name="par_res_id")
	private Integer par_res_id;
	@Column(name="is_share_file")
	private Integer is_share_file;
	@Column(name="res_virtural_url")
	private String res_virtural_url;
	@Column(name="download_num")
	private Integer download_num;
	@Transient
	private String fileType;
	@Transient
	private String size;
	
	public Integer getResource_id() {
		return resource_id;
	}
	@IdTransce(Type.INTEGER)
	public void setResource_id(Integer resource_id) {
		this.resource_id = resource_id;
	}
	public String getResource_name() {
		return resource_name;
	}
	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}
	public String getResource_type() {
		return resource_type;
	}
	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}
	public Integer getResource_size() {
		return resource_size;
	}
	public void setResource_size(Integer resource_size) {
		this.resource_size = resource_size;
	}
	public Integer getIs_catalog() {
		return is_catalog;
	}
	public void setIs_catalog(Integer is_catalog) {
		this.is_catalog = is_catalog;
	}
	public String getResource_url() {
		return resource_url;
	}
	public void setResource_url(String resource_url) {
		this.resource_url = resource_url;
	}
	public String getRes_true_name() {
		return res_true_name;
	}
	public void setRes_true_name(String res_true_name) {
		this.res_true_name = res_true_name;
	}
	public Integer getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}
	public Integer getPar_res_id() {
		return par_res_id;
	}
	public void setPar_res_id(Integer par_res_id) {
		this.par_res_id = par_res_id;
	}
	public Integer getIs_share_file() {
		return is_share_file;
	}
	public void setIs_share_file(Integer is_share_file) {
		this.is_share_file = is_share_file;
	}
	public String getRes_virtural_url() {
		return res_virtural_url;
	}
	public void setRes_virtural_url(String res_virtural_url) {
		this.res_virtural_url = res_virtural_url;
	}
	public Integer getDownload_num() {
		return download_num;
	}
	public void setDownload_num(Integer download_num) {
		this.download_num = download_num;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}

}
