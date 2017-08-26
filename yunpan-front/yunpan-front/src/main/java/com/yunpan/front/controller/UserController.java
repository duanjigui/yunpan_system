package com.yunpan.front.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.util.StringUtil;
import com.yunpan.front.bean.User;
import com.yunpan.front.service.UserService;
import com.yunpan.front.util.CookieUtill;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/index")
	public String index(){
		
		return "login";
	}
	
	@RequestMapping("/tologin")
	public String to_login(String account,String passwd,ModelMap model,HttpServletResponse response){
		boolean flag=false;
		String tip="";
		if (StringUtil.isNotEmpty(account)&&StringUtil.isNotEmpty(passwd)) {
			if (account.matches("\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}")) {
				if (passwd.length()>=6) {
					flag=userService.savelogin(account,passwd,response);
					if (!flag) {
						tip="账号或密码出错！请重新登陆";
					}
				}else {
					tip="密码长度应该大于6位！";
				}
			}else {
				tip="请输入合法的邮箱！";
			}
		}
		if (flag) {
			model.addAttribute("current_path", "/");
			model.addAttribute("current_path_id", 1);
			return "index";
		}else {
			model.addAttribute("tip",tip);
			return "login";
		}
	}
	
	
	
	@RequestMapping("/logout")
	public String logout(@CookieValue(name="TOKEN_SSO",required=false) String token,
			HttpServletResponse response){
		boolean flag= userService.logout();
		if (!flag) {//如果sso注销失败，直接删除本地的cookie信息即可
			CookieUtill.setCookie(response, "TOKEN_SSO", "", 30*60);
		}
		
		return "redirect:/tologin";
	}
	
	
	
	
	@RequestMapping("/toRegister")
	public String toRegister(){
		
		return "register";
	}
	
	@RequestMapping(value="/Register",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> Register(User user){
		
		Map<String, Object> map= userService.registerUser(user);	
		
		
		return map;
	}
	
	
	
	
	@RequestMapping("/toforgetPass")
	public String toforgetPass(){
		
		return "forgetpass";
	}
	
	
	/**
	 * 
	 *@author duanjigui
	 *@Description 修改密码
	 *@date 2017年5月3日下午12:31:42
	 *@param user
	 *@return
	 */
	@RequestMapping( value="/updatePass",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> updatePass(User user){
			
		Map<String, Object>	map= userService.updatePassword(user);
		
		return map;
	}
	
	@RequestMapping(value="/info",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User getUserinfo(@CookieValue(name="TOKEN_SSO",required=false) String token){
		
		User user= userService.getUserinfoBytoken(token);
		
		return user;
	}
	
	
	
}
