package com.yunpan.manager.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunpan.manager.bean.ResourseBean;
import com.yunpan.manager.bean.ResponeTableData;
import com.yunpan.manager.service.ResourseService;

@Controller
@RequestMapping("/manager/resousre")
public class ResourceManagerController {
	
	private Logger logger=LoggerFactory.getLogger(ResourceManagerController.class);
	
	@Autowired
	private ResourseService resourseService;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	@RequestMapping(value="/list")
	public String list(ModelMap model) throws JsonProcessingException{
		
		 ResponeTableData<ResourseBean> responeTableData = resourseService.fetchAll();
		
		 model.addAttribute("resourseBeans", objectMapper.writeValueAsString(responeTableData));
		
		 return "/manager/resousre/list";
	}
	
	
	@RequestMapping("/delete")
	public String deleteResources(String[] ids){
		
		boolean flag= resourseService.deleteResources(ids);
		
		logger.trace("删除资源个数为："+ids.length+" 结果为："+flag);
		
		return "redirect:/manager/resousre/list";
	}
	
	
	@RequestMapping(value="/all",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ResourseBean> listAll(){
		return resourseService.fetchAll().getRows();
	}
	
	
	
	
}
