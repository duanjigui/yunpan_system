package com.yunpan.sso.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunpan.sso.pojo.RegisterUserInfo;
import com.yunpan.sso.pojo.User;
import com.yunpan.sso.service.UserService;
import com.yunpan.sso.util.CookieUtill;

@Controller
@RequestMapping(value="/user")
public class UserLoginController {
	@Autowired
	private UserService userService;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	/**
	 * 
	 *@author duanjigui
	 *@Description 用户登陆接口
	 *			作用：生成token,并将用户信息存放到redis缓存中，将token存到用户本地的cookie中，用于代替session
	 *			注意：若以前登录过，但此时的cookie中的token值并未删除的话，且redis中还保存该用户信息时，可以进行复用
	 *@date 2017年1月13日下午4:11:40
	 *@param email
	 *@param password
	 *@param response	
	 *@param cookievalue
	 *@return
	 */
	@RequestMapping(value="/login",produces="application/json;charset=utf-8")
	@ResponseBody
	public ResponseEntity<Map<String, String>> login(
			@RequestParam(name="emaill",required=true,defaultValue="") String email,
			@RequestParam(name="password",required=true,defaultValue="") String password,
			HttpServletResponse response,
			@CookieValue(name="TOKEN_SSO",required=false) String cookievalue){
			Map<String, String> mes=new HashMap<>();
			try {
				String key=cookievalue;
				String token= userService.updateEmailAndPassToLogin(email,password,key);//返回token信息
				if (null==token) {
					mes.put("message", "账号或者密码出错！");
					mes.put("token", null);
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mes);
				}else {
					if (!token.equals(key)) {//当使用的不是一个key的时候需要将cookie存放到客户端
						CookieUtill.setCookie(response, "TOKEN_SSO", token, 60*60*24);//设置cookie的有效时长为1天
					}
					mes.put("message", "登录成功！");
					mes.put("token", token);
					return ResponseEntity.status(HttpStatus.OK).body(mes);
				}
			} catch (Exception e) {
				e.printStackTrace();
				mes.put("message", "服务器内部出错！");
				mes.put("token", null);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mes);
			}
	}
	/**
	 * 
	 *@author duanjigui
	 *@Description 根据token值，查询用户信息
	 *@date 2017年1月13日下午4:16:43
	 *@param token
	 *@return
	 */
	@RequestMapping(value="/info/{token}",produces="application/json;charset=utf-8")
	@ResponseBody
	public ResponseEntity<User> queryByToken(@PathVariable("token") String token){
		try {
			String userInfo=userService.fetchUserInfoByToken(token);
			if (userInfo==null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}else {
				User user = objectMapper.readValue(userInfo, User.class);
				return ResponseEntity.ok(user);
			}
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} catch (JsonParseException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} catch (JsonMappingException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	/**
	 * 
	 *@author duanjigui
	 *@Description 注册用户，附带校验
	 *@date 2017年1月13日下午4:17:26
	 *@param userInfo
	 *@param result
	 *@return
	 */
	@RequestMapping(value="/register",produces="application/json;charset=utf-8")
	@ResponseBody
	public ResponseEntity<Map<String, String>> regiester(@Valid RegisterUserInfo userInfo,BindingResult result){
		
		Map<String, String> infos=new HashMap<>(5);
		
		if (result.hasErrors()) {//校验
			
			for(ObjectError error:result.getAllErrors()){
				String message = error.getDefaultMessage();
			}
	
			List<ObjectError> errors = result.getAllErrors();
			
			int size = errors.size();
			
			infos.put("error", errors.get(size-1).getDefaultMessage());
			
			infos.put("regiest_email", null);
			
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(infos);
		}else {
			ResponseEntity<Map<String, Boolean>> hasUser = this.hasUser(userInfo.getEmaill());//首先判断是否已经注册了该账户
			
			if (!hasUser.getBody().get("exists")) {
				boolean flag= userService.saveRegiester(userInfo);
				if (flag) {
					infos.put("regiest_email", userInfo.getEmaill());
					infos.put("error", null);
					return  ResponseEntity.status(HttpStatus.OK).body(infos);
				}else {
					infos.put("regiest_email", null);
					infos.put("error", "注册账号出现异常，请重新注册！");
					return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(infos);
				}
				
			}else {
				infos.put("regiest_email", null);
				infos.put("error", "注册账号出现异常，请重新注册！");
				return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(infos);
			}
			
			
		}
		
	}

	/**
	 * 
	 *@author duanjigui
	 *@Description 注意：由于.对于路径来说有着重要的作用，所以在这里不必使用/exists/{email}路径的方法去当做访问路径
	 *				验证用户是否存在
	 *@date 2017年1月13日下午4:06:28
	 *@param email
	 *@return
	 */
	@RequestMapping(value="/exists",produces="application/json;charset=utf-8")
	@ResponseBody
	public ResponseEntity<Map<String, Boolean>> hasUser(@RequestParam(value="email",required=true,defaultValue="")String email){
		Boolean value=false;
		Map<String, Boolean> map=new HashMap<>(1);
		try {
			value = userService.fetchUserEmaillHasExist(email);
			map.put("exists", value);
		} catch (Exception e) {
			map.put("exists", false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
		return ResponseEntity.ok(map);
	}
	
	/**
	 * 
	 *@author duanjigui
	 *@Description 用户注销
	 *			     （1）根据cookie中存储的token值，然后从redis中查找是否存在key为该token值
	 *			     （2）如果存在的话，则删除该cookie值，否则什么也不做
	 *@date 2017年2月3日下午4:54:39
	 */
	@RequestMapping(value="/logout",produces="text/html;charset=utf-8")
	public ResponseEntity<String> logout(@CookieValue(name="TOKEN_SSO",required=false) String cookievalue,HttpServletResponse response){
		if (null!=cookievalue) {//当cookie中存在token时
			boolean flag=	 userService.logout(cookievalue);
			if (flag) {
				CookieUtill.setCookie(response, "TOKEN_SSO", "", -1);//清空cookie
				return ResponseEntity.ok("注销成功！");
			}else {
				return ResponseEntity.ok("注销失败！");
			}
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("你并没有登录！");
		}
		
	}
}
