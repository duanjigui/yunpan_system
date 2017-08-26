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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunpan.manager.bean.Auth;
import com.yunpan.manager.bean.ResponeTableData;
import com.yunpan.manager.bean.Role;
import com.yunpan.manager.bean.TreeNodeEnity;
import com.yunpan.manager.service.AuthManagerService;
import com.yunpan.manager.service.RoleManagerService;

@Controller
@RequestMapping("/manager/auth")
public class AuthManagerController {
	
	private Logger logger=LoggerFactory.getLogger(AuthManagerController.class);	
	@Autowired
	private AuthManagerService authManagerService;
	@Autowired
	private RoleManagerService roleManagerService;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	@RequestMapping(value="/list")
	public String list(ModelMap model) throws JsonProcessingException{
		
		List<Auth> list= authManagerService.fetchAllAuths();
		ResponeTableData<Auth> responeTableData=new ResponeTableData<>();
		responeTableData.setRows(list);
		responeTableData.setTotal(list.size());
		model.addAttribute("auths", objectMapper.writeValueAsString(responeTableData));
		
		return "/manager/auth/list";
	}
	
	
	@RequestMapping("/delete")
	public String deleteAuths(String[] ids){
		
		boolean flag= authManagerService.deleteAuthByIds(ids);
		
		logger.trace("删除用户个数为："+ids.length+" 结果为："+flag);
		
		return "redirect:/manager/auth/list";
	}
	@RequestMapping("/toAdd")
	public String to_Add(){
		return "/manager/auth/AuthAdd";
	}
	
	@RequestMapping("/toUpdate")
	public String to_update(String id, ModelMap model){
		
		Auth auth= authManagerService.fetchAuthById(id);
		
		model.addAttribute("auth", auth);
		
		return "/manager/auth/AuthUpdate";
	}
	
	@RequestMapping(value="/all",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Auth> listAll(){
		return authManagerService.fetchAllAuths();
	}
	
	@RequestMapping("/add")
	public String add(Auth auth,@CookieValue(name="TOKEN_SSO",required=false) String token){
		boolean flag= authManagerService.saveAuth(auth, token);
		logger.trace("添加权限"+auth+"结果："+flag);
		return "empty";
	}
	@RequestMapping("/update")
	public String update(Auth auth,ModelMap model){
		boolean flag= authManagerService.updateAuth(auth);
		if (flag) {
			return "empty";
		}else {
			model.addAttribute("auth_id", auth.getAuth_id());
			return "redirect:/manager/auth/toUpdate";
		}
	}
	
	
	@RequestMapping("/dispatch/index")
	public String dispatchAuthIndex(ModelMap model) throws Exception{
		ResponeTableData<Role> responeTableData = roleManagerService.fetchAll();
		
		model.addAttribute("roles", objectMapper.writeValueAsString(responeTableData));
		
		return "/manager/auth/dispatchAuth";
	}
	
	@RequestMapping("/dispatch/view")
	public String dispatchAuthView(String id,ModelMap model) throws Exception{
		model.addAttribute("pid", id);		
		return "/manager/auth/dispatchAuthView";
	}
	@RequestMapping("/dispatch/update")
	public String dispatchAuthUpdate(String id,ModelMap model) throws Exception{
		
		model.addAttribute("pid", id);
		
		return "/manager/auth/dispatchAuthUpdate";
	}
	@RequestMapping(value="/dispatch/list",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TreeNodeEnity> dispatchAuthlist(@RequestParam(defaultValue="0",required=true)String id,String pid) throws Exception{
		return authManagerService.listAuths(id,pid);
	}
	
	@RequestMapping(value="/dispatch/reupdate")
	@ResponseBody
	public String dispatchAuthUpdate(String[] ids,String pid) throws Exception{
		
		boolean flag=authManagerService.updateRoleAuths(ids,pid);
		
		if (flag) {
			return "修改成功！";
		}else{
			return "修改失败！";
		}
	}
	@RequestMapping(value="/dispatch/role/list",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TreeNodeEnity> dispatchRoleAuthlist(@RequestParam(defaultValue="0",required=true)String id,String pid) throws Exception{
		
		return authManagerService.listroleAuths(id,pid);
	}
	
	
}
