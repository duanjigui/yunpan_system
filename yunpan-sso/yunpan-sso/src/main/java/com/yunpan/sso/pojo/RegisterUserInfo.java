package com.yunpan.sso.pojo;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class RegisterUserInfo {
	@Email(message="邮箱格式不正确")
	private String emaill;
	@Length(min=1,message="昵称的长度最少为1位")
	private String nickName;
	@Length(min=6,max=12,message="密码的长度为6~12位")
	private String password;
	@Length(min=11,max=11,message="手机号的长度为11位")
	private String phone;
	public String getEmaill() {
		return emaill;
	}
	public void setEmaill(String emaill) {
		this.emaill = emaill;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
