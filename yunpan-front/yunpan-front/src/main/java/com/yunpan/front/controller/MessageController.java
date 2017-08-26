package com.yunpan.front.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunpan.front.bean.MessageBean;
import com.yunpan.front.service.MessageService;

@Controller
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value="/all",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<MessageBean> getMessageList(@CookieValue(name="TOKEN_SSO",required=false) String token){
		
		List<MessageBean> list= messageService.fetchMessage(token);
		
		return list;
	}
	
	
	
}

