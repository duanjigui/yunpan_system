package com.yunpan.manager.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunpan.manager.bean.Auth;
import com.yunpan.manager.bean.User;
import com.yunpan.manager.service.AuthManagerService;
import com.yunpan.manager.service.LoggerService;
import com.yunpan.manager.service.UserService;
import com.yunpan.manager.util.CookieUtill;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	@Autowired
	private LoggerService loggerService;
	
	@Autowired
	private  AuthManagerService authManagerService;
	
	private ObjectMapper mapper=new ObjectMapper();
	/**
	 * 
	 *@author duanjigui
	 *@Description 跳转到后台登录页面，并回显错误信息
	 *@date 2017年2月18日上午10:23:07
	 *@param tip
	 *@param user_email
	 *@param model
	 *@return
	 *@throws Exception
	 */
	@RequestMapping("/login")
	public String to_login(String tip,String user_email,ModelMap model) throws Exception{
		if (tip!=null) {
			model.put("tip", new String(tip.getBytes("ISO-8859-1"), "UTF-8"));
		}
		if (user_email!=null) {
			model.put("user_email", new String(user_email.getBytes("ISO-8859-1"), "UTF-8"));	
		}
		
		return "/login/login";
	}
	/**
	 * 
	 *@author duanjigui
	 *@Description 登录并获取用户信息【通过httpclient远程访问sso系统获取内容】
	 *@date 2017年2月18日上午10:23:50
	 *@param request
	 *@param response
	 *@param user
	 *@param model
	 *@return
	 *@throws Exception
	 */
	@RequestMapping("/manager")
	public String to_back(HttpServletRequest request,HttpServletResponse response, User user,ModelMap model) throws Exception{
		//接受输入的账号和密码，查询用户信息登录
		String message=userService.fetchInfoByEmailAndPwd(user,request,response);
		if (message==null||message.equals("账号或者密码出错！")||message.equals("该账户没有权限登录系统！")) {//没有消息返回，说明登录失败！
			if (message!=null) {
				model.put("tip", message);
			}else{
				model.put("tip", "账号或者密码出错！");
			}
			model.put("user_email", user.getUser_email());
			return "redirect:/login";
		}else{
			User user_info = mapper.readValue(message, User.class);
			model.put("user_info", user_info);
			model.addAttribute("firstMenu", authManagerService.fifterFirstMenuByUser(user_info));
			loggerService.saveloginLoggerInfo(user);
			return "/manager/manager";
		}
	}
	/**
	 * 
	 *@author duanjigui
	 *@Description 后台管理页面的欢迎页面
	 *@date 2017年2月18日上午10:25:05
	 *@return
	 */
	@RequestMapping("/welcome")
	public String welcome(){
		return "/manager/welcome";
	}
	/**
	 * 
	 *@author duanjigui
	 *@Description 用户注销
	 *@date 2017年2月18日上午10:40:23
	 *@param response
	 *@return
	 *@throws ClientProtocolException
	 *@throws Exception
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletResponse response) throws ClientProtocolException, Exception{
		boolean flag= userService.logout();
		if (!flag) {//如果sso注销失败，直接删除本地的cookie信息即可
			CookieUtill.setCookie(response, "TOKEN_SSO", "", 30*60);
		}
		return "redirect:/login";
	}
	/**
	 * 
	 *@author duanjigui
	 *@Description 跳转到修改密码页面
	 *@date 2017年4月17日下午9:26:14
	 *@return
	 */
	@RequestMapping("/toUpdatepasswd")
	public String toUpdatepasswd(){
		
		return "modifyPasswd";
	}
	
	@RequestMapping("/Updatepasswd")
	public void UpdatePasswd(@CookieValue(name="TOKEN_SSO",required=false)String token ,
			String o_pwd,
			String n_pwd,  
			HttpServletResponse response) throws IOException{
		Map<String, Object> result= userService.updatePasswd(token,o_pwd,n_pwd);
		//首先，先判断原密码是否相同
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		if (result.get("status")!=null && !result.get("status").equals("200")) {
			if (result.get("message")!=null) {
				response.getWriter().println("<h1>"+result.get("message")+"</h1>");
			}
		}else{
			response.getWriter().println("<h1>修改成功！</h1>");
		}
	}
	
	
	
	@RequestMapping(value="/fetchMenu")
	@ResponseBody
	public List<Auth> fetchAuthByUserIdAndParentId(String pid,String userid){
				
		List<Auth> auths= authManagerService.fetchAuthByUserIdAndParentId(pid,userid);
		
		return auths;
	}
	
	
} 
