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
import com.yunpan.manager.bean.MessageBean;
import com.yunpan.manager.bean.ResponeTableData;
import com.yunpan.manager.service.MessageService;

@Controller
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	private Logger logger= LoggerFactory.getLogger(MessageService.class);
	
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	@RequestMapping(value="/list")
	public String list(ModelMap model) throws JsonProcessingException{
		ResponeTableData<MessageBean> responeTableData = messageService.fetchAll();
		
		model.addAttribute("messages", objectMapper.writeValueAsString(responeTableData));
		
		return "/manager/message/list";
	}
	
	
	@RequestMapping("/delete")
	public String deleteMessages(String[] ids){
		
		boolean flag= messageService.deleteCatelogByIds(ids);
		
		logger.trace("删除内容个数为："+ids.length+" 结果为："+flag);
		
		return "redirect:/message/list";
	}
	@RequestMapping("/toAdd")
	public String to_Add(){
		return "/manager/message/messageAdd";
	}
	
	@RequestMapping("/toUpdate")
	public String to_update(String id, ModelMap model){
		
		MessageBean messageBean= messageService.fetchById(id, MessageBean.class);
		
		model.addAttribute("message", messageBean);
		
		return "/manager/message/messageUpdate";
	}
	
	@RequestMapping(value="/all",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<MessageBean> listAll(){
		return messageService.fetchAll().getRows();
	}
	
	@RequestMapping("/add")
	public String add(MessageBean messageBean,@CookieValue(name="TOKEN_SSO",required=false) String token){
		boolean flag= messageService.saveMessage(messageBean, token);
		
		logger.trace("添加内容"+messageBean+"结果："+flag);
		return "empty";
	}
	@RequestMapping("/update")
	public String update(MessageBean messageBean,ModelMap model){
		boolean flag= messageService.updateMessage(messageBean);
		if (flag) {
			return "empty";
		}else {
			model.addAttribute("cateloge_id", messageBean.getMessage_id());
			return "redirect:/manager/toUpdate";
		}
	}
	
	
	
	
}

