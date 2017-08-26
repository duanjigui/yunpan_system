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
import com.yunpan.manager.bean.RuleBean;
import com.yunpan.manager.service.RuleService;

@Controller
@RequestMapping("/manager/rule")
public class RuleController {
	
	private Logger logger=LoggerFactory.getLogger(RuleController.class);
	
	@Autowired
	private RuleService ruleService;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	@RequestMapping(value="/list")
	public String list(ModelMap model) throws JsonProcessingException{
		 ResponeTableData<RuleBean> responeTableData = ruleService.fetchAll();
		
		 model.addAttribute("rules", objectMapper.writeValueAsString(responeTableData));
		
		 return "/manager/rule/list";
	}
	
	
	@RequestMapping("/delete")
	public String deleteAuths(String[] ids){
		
		boolean flag= ruleService.deleteRuleByIds(ids);
		
		logger.trace("删除用户个数为："+ids.length+" 结果为："+flag);
		
		return "redirect:/manager/rule/list";
	}
	@RequestMapping("/toAdd")
	public String to_Add(){
		return "/manager/rule/RuleAdd";
	}
	
	@RequestMapping("/toUpdate")
	public String to_update(String id, ModelMap model){
		
		 RuleBean ruleBean = ruleService.fetchById(id, RuleBean.class);
		
		model.addAttribute("rule", ruleBean);
		
		return "/manager/rule/RuleUpdate";
	}
	
	@RequestMapping(value="/all",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<RuleBean> listAll(){
		return ruleService.fetchAll().getRows();
	}
	
	@RequestMapping("/add")
	public String add(RuleBean rule,@CookieValue(name="TOKEN_SSO",required=false) String token){
		boolean flag= ruleService.saveRule(rule, token);
		logger.trace("添加权限"+rule+"结果："+flag);
		return "empty";
	}
	@RequestMapping("/update")
	public String update(RuleBean rule,ModelMap model){
		boolean flag= ruleService.updateRule(rule);
		if (flag) {
			return "empty";
		}else {
			model.addAttribute("rule_id", rule.getRule_id());
			return "redirect:/manager/rule/toUpdate";
		}
	}
	
	
	
	
	
	
	
	
}
