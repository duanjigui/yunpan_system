package com.yunpan.manager.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunpan.manager.bean.ResponeTableData;
import com.yunpan.manager.bean.Role;
import com.yunpan.manager.bean.TreeNodeEnity;
import com.yunpan.manager.bean.User;
import com.yunpan.manager.service.RoleManagerService;
import com.yunpan.manager.service.UserService;

@Controller
@RequestMapping("/manager/role")
public class RoleManagerController {

	private Logger logger=LoggerFactory.getLogger(RoleManagerController.class);	
	@Autowired
	private RoleManagerService roleManagerService;
	@Autowired
	private UserService userService;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	@RequestMapping(value="/list")
	public String list(ModelMap model) throws JsonProcessingException{
		ResponeTableData<Role> responeTableData = roleManagerService.fetchAll();
		
		model.addAttribute("roles", objectMapper.writeValueAsString(responeTableData));
		
		return "/manager/role/list";
	}
	
	
	@RequestMapping("/delete")
	public String deleteAuths(String[] ids){
		
		boolean flag= roleManagerService.deleteRoleByIds(ids);
		
		logger.trace("删除用户个数为："+ids.length+" 结果为："+flag);
		
		return "redirect:/manager/role/list";
	}
	@RequestMapping("/toAdd")
	public String to_Add(){
		return "/manager/role/RoleAdd";
	}
	
	@RequestMapping("/toUpdate")
	public String to_update(String id, ModelMap model){
		
		Role role= roleManagerService.fetchById(id, Role.class);
		
		model.addAttribute("role", role);
		
		return "/manager/role/RoleUpdate";
	}
	
	@RequestMapping(value="/all",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Role> listAll(){
		return roleManagerService.fetchAll().getRows();
	}
	
	@RequestMapping("/add")
	public String add(Role role,@CookieValue(name="TOKEN_SSO",required=false) String token){
		boolean flag= roleManagerService.saveRole(role, token);
		logger.trace("添加权限"+role+"结果："+flag);
		return "empty";
	}
	@RequestMapping("/update")
	public String update(Role role,ModelMap model){
		boolean flag= roleManagerService.updateRole(role);
		if (flag) {
			return "empty";
		}else {
			model.addAttribute("auth_id", role.getRole_id());
			return "redirect:/manager/auth/toUpdate";
		}
	}
	
	
	@RequestMapping("/dispatch")
	public String roledispatch(ModelMap model) throws Exception{
		
		 ResponeTableData<User> user = userService.fetchAllUser();
		
		model.addAttribute("users", objectMapper.writeValueAsString(user));
		
		return "/manager/role/RoleDispatch";
	}
	@RequestMapping("/viewRole")
	public String roledispatchView(String id,ModelMap model) throws Exception{
		model.addAttribute("pid",id);
		return "/manager/role/roledispatchview";
	}
	@RequestMapping("/updateRole")
	public String roledispatchUpdate(String id,ModelMap model) throws Exception{
		model.addAttribute("pid",id);
		return "/manager/role/roledispatchupdate";
	}
	
	@RequestMapping(value="/dispatch/list",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TreeNodeEnity> roledispatchuserUpdatelist(String pid,ModelMap model) throws Exception{
		
		List<TreeNodeEnity> treeNodeEnities= userService.fectchRolelist(pid);
		
		return treeNodeEnities;
	}
	
	@RequestMapping(value="/dispatch/reupdate")
	@ResponseBody
	public String roledispatchuserUpdatereupdate(String[] ids,String pid,ModelMap model) throws Exception{
		
		boolean flag= userService.updateRoleInfo(ids,pid);
		if (flag) {
			return "更新成功！";
		}else {
			return "更新失败！";
		}
		
		
	}
	
	
	
	@RequestMapping(value="/dispatch/role/list",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TreeNodeEnity> roledispatchuserview(String pid,ModelMap model) throws Exception{
		return userService.fetchUserRoleInfo(pid);
	}
	
}
