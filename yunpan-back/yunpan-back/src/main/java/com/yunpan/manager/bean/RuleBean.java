package com.yunpan.manager.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yunpan.manager.annotantion.IdTransce;
import com.yunpan.manager.annotantion.Type;

@Table(name="sys_rule")
public class RuleBean extends BaseBean{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rule_id; //规则id
	@Column(name="rule_name")
	private String rule_name; //规则name
	@Column(name="rule_desc")
	private String rule_desc;//规则描述
	@Column(name="rule_sysmbol")
	private String rule_sysmbol;//规则特征符号【根据符号确定禁止还是允许通过】
	@Column(name="rule_control_field")
	private String rule_control_field;//规则控制字段
	@Column(name="rule_control_content")
	private String rule_control_content;//规则控制内容
	public int getRule_id() {
		return rule_id;
	}
	@IdTransce(Type.INTEGER)
	public void setRule_id(int rule_id) {
		this.rule_id = rule_id;
	}
	public String getRule_name() {
		return rule_name;
	}
	public void setRule_name(String rule_name) {
		this.rule_name = rule_name;
	}
	public String getRule_desc() {
		return rule_desc;
	}
	public void setRule_desc(String rule_desc) {
		this.rule_desc = rule_desc;
	}
	public String getRule_sysmbol() {
		return rule_sysmbol;
	}
	public void setRule_sysmbol(String rule_sysmbol) {
		this.rule_sysmbol = rule_sysmbol;
	}
	public String getRule_control_field() {
		return rule_control_field;
	}
	public void setRule_control_field(String rule_control_field) {
		this.rule_control_field = rule_control_field;
	}
	public String getRule_control_content() {
		return rule_control_content;
	}
	public void setRule_control_content(String rule_control_content) {
		this.rule_control_content = rule_control_content;
	}
	@Override
	public String toString() {
		return "RuleBean [rule_id=" + rule_id + ", rule_name=" + rule_name + ", rule_desc=" + rule_desc
				+ ", rule_sysmbol=" + rule_sysmbol + ", rule_control_field=" + rule_control_field
				+ ", rule_control_content=" + rule_control_content + "]";
	}
	
}
