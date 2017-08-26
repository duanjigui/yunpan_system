package com.yunpan.manager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunpan.manager.bean.LoggerBean;
import com.yunpan.manager.bean.ResponeTableData;
import com.yunpan.manager.service.LoggerService;

@Controller
@RequestMapping("/manager/logger")
public class LoggerController {

	private Logger logger=LoggerFactory.getLogger(LoggerController.class);	
	@Autowired
	private LoggerService loggerService;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	@RequestMapping(value="/list")
	public String list(ModelMap model) throws JsonProcessingException{
		ResponeTableData<LoggerBean> responeTableData = loggerService.fetchAll();
		
		model.addAttribute("logger", objectMapper.writeValueAsString(responeTableData));
		
		return "/manager/logger/list";
	}
	
	
	
}
