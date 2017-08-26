package com.yunpan.front.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunpan.front.bean.Cateloge;
import com.yunpan.front.service.CatelogService;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private CatelogService catelogService;
	
	
	@RequestMapping(value="/foot",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Cateloge> fetchIndexFootData(){
		
		List<Cateloge> cateloges= catelogService.fetchIndexFootData(11);	
		
		return cateloges;
	}
	
	
	@RequestMapping(value="/right/header",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Cateloge> fetchIndexRightHeader(){
		
		List<Cateloge> cateloges= catelogService.fetchIndexFootData(12);	
		
		return cateloges;
	}
	
	@RequestMapping(value="/left/header",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Cateloge> fetchIndexLeftHeader(){
		
		List<Cateloge> cateloges= catelogService.fetchIndexFootData(13);	
		
		return cateloges;
	}
	
	@RequestMapping(value="/main/scroll",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Cateloge> fetchIndexMainScroll(){
		
		List<Cateloge> cateloges= catelogService.fetchIndexFootData(10);	
		
		return cateloges;
	}
	
	@RequestMapping(value="/main/menu/left",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Cateloge> fetchIndexLeftMenu(){
		
		List<Cateloge> cateloges= catelogService.fetchIndexFootData(14);	
		
		return cateloges;
	}
	
	@RequestMapping(value="/main/menu/topleft",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Cateloge> fetchIndexLeftTopMenu(){
		
		List<Cateloge> cateloges= catelogService.fetchIndexFootData(15);	
		
		return cateloges;
	}
	
	@RequestMapping(value="/main/menu/topright",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Cateloge> fetchIndexrightTopMenu(){
		
		List<Cateloge> cateloges= catelogService.fetchIndexFootData(16);	
		
		return cateloges;
	}
	
	
}
