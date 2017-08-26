package com.yunpan.front.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunpan.front.bean.User;
import com.yunpan.front.util.CookieUtill;

@Service
public class UserService extends BaseService<User>{
	
	@Autowired
	private HttpService httpService;
	@Value(value="${BASE_USER_URL}")
	private String SERCICE_URL;
	@Autowired
	private LoggerService loggerService;
	
	private String REGISTE_USER_URL="http://localhost:8082/user/register";
	
	private ObjectMapper objectMapper=new ObjectMapper();
		
	
	public boolean savelogin(String account, String passwd,HttpServletResponse response) {
		try {
			String login_message = httpService.getMessageFromUrl(SERCICE_URL+"login?emaill="+account+"&password="+passwd);
			Map<String,String> value = objectMapper.readValue(login_message, Map.class);
			if (value.get("message").equals("账号或者密码出错！")) {
				return false;
			}else if (value.get("message").equals("登录成功！")) {
				String token = value.get("token");//当前token值
				//因为使用的是httpclient，cookie并不会保留到客户端，所以需要手动的进行添加
				CookieUtill.setCookie(response, "TOKEN_SSO", token, 30*60);
				User user=new User();
				user.setUser_email(account);
				user.setUser_pwd(passwd);
				loggerService.saveloginLoggerInfo(user);
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	public boolean logout(){
		try {
			String message = httpService.getMessageFromUrl(SERCICE_URL+"logout");
			if (message.equals("注销成功！")) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}


	public Map<String, Object> registerUser(User user) {
		
		Map<String, Object> validates = validte(user);
		
		if (validates.get("validate_status").equals("200")) {//校验码为200,表名校验成功
			//开始用户注册
			
		  try {
				String message = httpService.getMessageFromUrl(REGISTE_USER_URL+
					"?emaill="+user.getUser_email()+"&nickName="+user.getUser_name()+
					"&password="+user.getUser_pwd()+"&phone="+user.getUser_phone());
				Map map = objectMapper.readValue(message, Map.class);
				
				if (map.get("error") == null) {
					String emaill = (String) map.get("regiest_email");
					validates.put("emaill", emaill);
					validates.put("registe_result","success");
				}else {
					validates.put("emaill", null);
					validates.put("registe_result","fail");
				}
				
				return validates;
			
			} catch (Exception e) {
				e.printStackTrace();
				validates.put("emaill", null);
				validates.put("registe_result","fail");
				return validates;
			}
			
		}else {
			validates.put("emaill", null);
			validates.put("registe_result","fail");
			return validates;
		}
		
	}

	/**
	 * 
	 *@author duanjigui
	 *@Description 校验用户的输入
	 *@date 2017年5月2日下午10:05:11
	 *@param user
	 *@return
	 */
	private Map<String, Object> validte(User user) {
		
		Map<String, Object> messages=new HashMap<>();
		
		String user_email = user.getUser_email();
		
		String user_phone = user.getUser_phone();
		
		String user_name = user.getUser_name();
		
		String user_pwd = user.getUser_pwd();
		
		//校验邮箱
		
		if (StringUtils.isBlank(user_email)) {
			messages.put("validate_status", "501");
			messages.put("validate_error_message", "用户邮箱不能为空！");
		}else if (StringUtils.indexOf(user_email,"@" )<=0||StringUtils.indexOf(user_email,"." )<=0) {
			messages.put("validate_status", "502");
			messages.put("validate_error_message", "用户邮箱应该包含@ .符号！");
		}else if (StringUtils.length(user_email)<6 ||StringUtils.length(user_email)>32) {
			messages.put("validate_status", "503");
			messages.put("validate_error_message", "用户邮箱应该在6-32位之间！");
		}else if (StringUtils.length(user_phone)!=11) {
			messages.put("validate_status", "511");
			messages.put("validate_error_message", "用户手机号应该是11位之间！");
		}else if (!StringUtils.isNumeric(user_phone)) {
			messages.put("validate_status", "512");
			messages.put("validate_error_message", "用户手机号应该全是数字！");
		}else if (StringUtils.length(user_pwd)<6||StringUtils.length(user_pwd)>32) {
			messages.put("validate_status", "521");
			messages.put("validate_error_message", "密码应该是在6-32位之间！");
		}else if (StringUtils.isBlank(user_name)) {
			messages.put("validate_status", "531");
			messages.put("validate_error_message", "用户名不能为空！");
		}else {
			messages.put("validate_status", "200");
			messages.put("validate_error_message", null);
		}
		
		return messages;
	}

	/**
	 * 
	 *@author duanjigui
	 *@Description 修改密码
	 *@date 2017年5月3日下午12:33:25
	 *@param user
	 *@return
	 */
	public Map<String, Object> updatePassword(User user) {
		Map<String, Object> map=new HashMap<>();
		String user_name = user.getUser_name();
		String user_email = user.getUser_email();
		String user_pwd = user.getUser_pwd();
		String user_phone = user.getUser_phone();
		user.setUser_pwd(null);	
		User temp = super.mapper.selectOne(user);
		if (temp!=null) {
			Md5Hash hash=new Md5Hash(user_pwd, "", 1);
			temp.setUser_pwd(hash.toString());
			boolean flag = super.updateInfo(temp);
			map.put("result", flag);
			if (flag) {
				map.put("error_message", null);
			}else {
				map.put("error_message", "未知错误！");
			}
		}else {
			map.put("result", false);
			map.put("error_message", "您输入的账户不存在");
		}
		
		return map;
	}


	public User getUserinfoBytoken(String token) {
		try {
			String userInfo = httpService.getMessageFromUrl(SERCICE_URL+"info/"+token);
			User user= objectMapper.readValue(userInfo, User.class);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
