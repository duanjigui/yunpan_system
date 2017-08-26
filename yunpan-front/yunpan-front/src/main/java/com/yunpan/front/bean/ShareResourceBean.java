package com.yunpan.front.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.yunpan.front.interfaces.IdTransce;
import com.yunpan.front.interfaces.Type;
@Table(name="cus_shares")
public class ShareResourceBean extends BaseBean{
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer	 share_id;
		@Column(name="resource_id")
		private Integer	resource_id;
		@Column(name="resource_name")
		private String resource_name;
		@Column(name="end_time")
		private Date end_time;
		@Column(name="share_type")
		private String	share_type;
		@Column(name="share_url")
		private String	share_url;
		@Column(name="share_code")
		private String	share_code;
		@Column(name="group_id")
		private Integer	group_id;
		@Column(name="group_name")
		private String	group_name;
		@Column(name="isFloder")
		private Integer	isFloder;
		@Transient
		private Integer downloadNum;
		@Transient
		private String fileType;
		@Transient
		private String sharedResourceUrl;
		
		public Integer getShare_id() {
			return share_id;
		}
		@IdTransce(Type.INTEGER)
		public void setShare_id(Integer share_id) {
			this.share_id = share_id;
		}
		public Integer getResource_id() {
			return resource_id;
		}
		public void setResource_id(Integer resource_id) {
			this.resource_id = resource_id;
		}
		public String getResource_name() {
			return resource_name;
		}
		public void setResource_name(String resource_name) {
			this.resource_name = resource_name;
		}
		public Date getEnd_time() {
			return end_time;
		}
		public void setEnd_time(Date end_time) {
			this.end_time = end_time;
		}
		public String getShare_type() {
			return share_type;
		}
		public void setShare_type(String share_type) {
			this.share_type = share_type;
		}
		public String getShare_url() {
			return share_url;
		}
		public void setShare_url(String share_url) {
			this.share_url = share_url;
		}
		public String getShare_code() {
			return share_code;
		}
		public void setShare_code(String share_code) {
			this.share_code = share_code;
		}
		public Integer getGroup_id() {
			return group_id;
		}
		public void setGroup_id(Integer group_id) {
			this.group_id = group_id;
		}
		public String getGroup_name() {
			return group_name;
		}
		public void setGroup_name(String group_name) {
			this.group_name = group_name;
		}
		public Integer getIsFloder() {
			return isFloder;
		}
		public void setIsFloder(Integer isFloder) {
			this.isFloder = isFloder;
		}
		public Integer getDownloadNum() {
			return downloadNum;
		}
		public void setDownloadNum(Integer downloadNum) {
			this.downloadNum = downloadNum;
		}
		public String getFileType() {
			return fileType;
		}
		public void setFileType(String fileType) {
			this.fileType = fileType;
		}
		
		public String getSharedResourceUrl() {
			return sharedResourceUrl;
		}
		public void setSharedResourceUrl(String sharedResourceUrl) {
			this.sharedResourceUrl = sharedResourceUrl;
		}
		@Override
		public String toString() {
			return "ShareResourceBean [share_id=" + share_id + ", resource_id=" + resource_id + ", resource_name="
					+ resource_name + ", end_time=" + end_time + ", share_type=" + share_type + ", share_url="
					+ share_url + ", share_code=" + share_code + ", group_id=" + group_id + ", group_name=" + group_name
					+ ", isFloder=" + isFloder + "]";
		}
	
	
}
