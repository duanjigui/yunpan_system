package com.yunpan.front.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yunpan.front.interfaces.IdTransce;
import com.yunpan.front.interfaces.Type;

/**
 * 
 * @ClassName: LoggerBean
 * @Description: 创建日志实体bean
 * @author duanjigui
 * @date 2017年4月17日 下午10:23:47
 *
 */
@Table(name="sys_logger")
public class LoggerBean extends BaseBean{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int log_id;
	@Column(name="log_leavel")
	private String log_leavel;
	@Column(name="log_content_type")
	private String log_content_type;
	@Column(name="log_content")
	private String log_content;
	@Column(name="group_id")
	private String group_id;
	@Column(name="group_name")
	private String group_name;
	@Column(name="trigger_class_name")
	private String trigger_class_name;
	@Column(name="triger_method_name")
	private String triger_method_name;
	public int getLog_id() {
		return log_id;
	}
	@IdTransce(Type.INTEGER)
	public void setLog_id(int log_id) {
		this.log_id = log_id;
	}
	public String getLog_leavel() {
		return log_leavel;
	}
	public void setLog_leavel(String log_leavel) {
		this.log_leavel = log_leavel;
	}
	public String getLog_content_type() {
		return log_content_type;
	}
	public void setLog_content_type(String log_content_type) {
		this.log_content_type = log_content_type;
	}
	public String getLog_content() {
		return log_content;
	}
	public void setLog_content(String log_content) {
		this.log_content = log_content;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getTrigger_class_name() {
		return trigger_class_name;
	}
	public void setTrigger_class_name(String trigger_class_name) {
		this.trigger_class_name = trigger_class_name;
	}
	public String getTriger_method_name() {
		return triger_method_name;
	}
	public void setTriger_method_name(String triger_method_name) {
		this.triger_method_name = triger_method_name;
	}
	@Override
	public String toString() {
		return "LoggerBean [log_id=" + log_id + ", log_leavel=" + log_leavel + ", log_content_type=" + log_content_type
				+ ", log_content=" + log_content + ", group_id=" + group_id + ", group_name=" + group_name
				+ ", trigger_class_name=" + trigger_class_name + ", triger_method_name=" + triger_method_name
				+ ", create_time=" + create_time + ", update_time=" + update_time + ", creater=" + creater
				+ ", creater_name=" + creater_name + ", getLog_id()=" + getLog_id() + ", getLog_leavel()="
				+ getLog_leavel() + ", getLog_content_type()=" + getLog_content_type() + ", getLog_content()="
				+ getLog_content() + ", getGroup_id()=" + getGroup_id() + ", getGroup_name()=" + getGroup_name()
				+ ", getTrigger_class_name()=" + getTrigger_class_name() + ", getTriger_method_name()="
				+ getTriger_method_name() + ", getCreate_time()=" + getCreate_time() + ", getUpdate_time()="
				+ getUpdate_time() + ", getCreater()=" + getCreater() + ", getCreater_name()=" + getCreater_name()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
