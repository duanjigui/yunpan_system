package com.yunpan.manager.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunpan.manager.bean.ResponeTableData;
import com.yunpan.manager.bean.User;
import com.yunpan.manager.service.UserService;

@Controller
@RequestMapping("/manager")
public class UserManagerController {
	private static final Logger logger = Logger.getLogger(UserManagerController.class);

	@Autowired
	private UserService userService;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	/**
	 * 
	 *@author duanjigui
	 *@Description 跳转到user的列表显示页面
	 *@date 2017年3月4日上午10:29:14
	 *@param model
	 *@return
	 *@throws JsonProcessingException
	 */
	@RequestMapping(value="/user/list",produces="application/json;charset=utf-8")
	public String list(String page,String pagesize, ModelMap model) throws JsonProcessingException{
		if (logger.isDebugEnabled()) {
			logger.debug("访问路径 :/manager/user/list");
			logger.debug("entering list(ModelMap)");
			logger.debug("model: " + model);
		}
		ResponeTableData<User> responeTableData= userService.fetchAllUser();
		
		model.addAttribute("responeTableData", objectMapper.writeValueAsString(responeTableData));
		
		return "/manager/user/userManager";
	}
	/**
	 * 
	 *@author duanjigui
	 *@Description 跳转到添加用户界面
	 *@date 2017年3月4日上午10:29:56
	 *@return
	 */
	@RequestMapping("/user/useradd")
	public String toUseradd(){
		return"/manager/user/useradd";
	}
	/**
	 * 
	 *@author duanjigui
	 *@Description 添加用户逻辑
	 *@date 2017年3月4日上午10:30:27
	 *@param user
	 *@param cookievalue
	 *@return
	 */
	@RequestMapping("/user/add")
	public String adduser(User user,@CookieValue(name="TOKEN_SSO",required=false) String cookievalue){
		if (logger.isDebugEnabled()) {
			logger.debug("访问路径 :/manager/user/add");
			logger.debug("entering adduser(User,String)");
			logger.debug("user: " + user);
			logger.debug("cookievalue: \"" + cookievalue + "\"");
		}
		
		boolean flag= userService.saveUser(user,cookievalue);
		//日志
		if (logger.isDebugEnabled()) {
			logger.debug("add user " + flag);
		}
		if (flag) {
			return "redirect:/manager/user/list";
		}else {
			return "redirect:/manager/user/add";
		}
	}
	/**
	 * 
	 *@author duanjigui
	 *@Description 删除用户
	 *@date 2017年3月4日上午10:33:06
	 *@return
	 */
	@RequestMapping("/user/delete")
	public String deleteUser(@RequestParam(name="ids") String[] ids){
		boolean flag= userService.deleteUserByIds(ids);
		if (logger.isDebugEnabled()) {
			logger.debug("delete user：" + flag);
		}
		return "redirect:/manager/user/list";
	}
	/**
	 * 
	 *@author duanjigui
	 *@Description 查询用户Id跳转到更改用户信息页面
	 *@date 2017年3月4日下午4:00:55
	 *@param id
	 *@param model
	 *@return
	 */
	@RequestMapping("/user/userUpdate")
	public String toUpdateUser(String id,ModelMap model){
		User user= userService.fetchUserInfoById(id);
		model.addAttribute("user", user);
		return "/manager/user/userUpdate";
	}
	
	@RequestMapping("/user/update")
	public String UpdateUser(User user){
		boolean flag= userService.updateUser(user);
		if (flag) {
			return "redirect:/manager/user/list";
		}else{
			return "redirect:/manager/user/userUpdate";
		}
	}
	
	@RequestMapping(value="/user/all",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> AllUser(){
		return userService.fetchAllUser().getRows();
	}
	
}
