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
import com.yunpan.manager.bean.Cateloge;
import com.yunpan.manager.bean.ResponeTableData;
import com.yunpan.manager.service.CatelogService;

@Controller
@RequestMapping("/manager/catelog")
public class CatelogController {
	private Logger logger=LoggerFactory.getLogger(CatelogController.class);	
	@Autowired
	private CatelogService catelogService;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	@RequestMapping(value="/list")
	public String list(ModelMap model) throws JsonProcessingException{
		ResponeTableData<Cateloge> responeTableData = catelogService.fetchAll();
		
		model.addAttribute("cateloge", objectMapper.writeValueAsString(responeTableData));
		
		return "/manager/catelog/list";
	}
	
	
	@RequestMapping("/delete")
	public String deleteCatelogs(String[] ids){
		
		boolean flag= catelogService.deleteCatelogByIds(ids);
		
		logger.trace("删除内容个数为："+ids.length+" 结果为："+flag);
		
		return "redirect:/manager/catelog/list";
	}
	@RequestMapping("/toAdd")
	public String to_Add(){
		return "/manager/catelog/catelogAdd";
	}
	
	@RequestMapping("/toUpdate")
	public String to_update(String id, ModelMap model){
		
		Cateloge cateloge= catelogService.fetchById(id, Cateloge.class);
		
		model.addAttribute("cateloge", cateloge);
		
		return "/manager/catelog/catelogUpdate";
	}
	
	@RequestMapping(value="/all",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Cateloge> listAll(){
		return catelogService.fetchAll().getRows();
	}
	
	@RequestMapping("/add")
	public String add(Cateloge cateloge,@CookieValue(name="TOKEN_SSO",required=false) String token){
		boolean flag= catelogService.saveContent(cateloge, token);
		
		logger.trace("添加内容"+cateloge+"结果："+flag);
		return "empty";
	}
	@RequestMapping("/update")
	public String update(Cateloge cateloge,ModelMap model){
		boolean flag= catelogService.updateContent(cateloge);
		if (flag) {
			return "empty";
		}else {
			model.addAttribute("cateloge_id", cateloge.getCatelog_id());
			return "redirect:/manager/auth/toUpdate";
		}
	}
}
