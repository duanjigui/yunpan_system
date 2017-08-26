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
import com.yunpan.manager.bean.CatelogeType;
import com.yunpan.manager.bean.ResponeTableData;
import com.yunpan.manager.service.CatelogeTypeService;

@Controller
@RequestMapping("/manager/catelogtype")
public class CatelogTypeController {
	private Logger logger=LoggerFactory.getLogger(CatelogTypeController.class);	
	@Autowired
	private CatelogeTypeService catelogeTypeService;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	@RequestMapping(value="/list")
	public String list(ModelMap model) throws JsonProcessingException{
		ResponeTableData<CatelogeType> responeTableData = catelogeTypeService.fetchAll();
		
		model.addAttribute("catelogeType", objectMapper.writeValueAsString(responeTableData));
		
		return "/manager/catelogtype/list";
	}
	
	
	@RequestMapping("/delete")
	public String deletecatelogeTypes(String[] ids){
		
		boolean flag= catelogeTypeService.deleteCatelogTypeByIds(ids);
		
		logger.trace("删除内容类型的个数为："+ids.length+" 结果为："+flag);
		
		return "redirect:/manager/catelogtype/list";
	}
	@RequestMapping("/toAdd")
	public String to_Add(){
		return "/manager/catelogtype/Add";
	}
	
	@RequestMapping("/toUpdate")
	public String to_update(String id, ModelMap model){
		
		CatelogeType catelogeType= catelogeTypeService.fetchById(id, CatelogeType.class);
		
		model.addAttribute("catelogeType", catelogeType);
		
		return "/manager/catelogtype/Update";
	}
	
	@RequestMapping(value="/all",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<CatelogeType> listAll(){
		return catelogeTypeService.fetchAll().getRows();
	}
	
	@RequestMapping("/add")
	public String add(CatelogeType catelogeType,@CookieValue(name="TOKEN_SSO",required=false) String token){
		boolean flag= catelogeTypeService.savecatelogeType(catelogeType, token);
		logger.trace("添加内容类型"+catelogeType+"结果："+flag);
		return "empty";
	}
	@RequestMapping("/update")
	public String update(CatelogeType catelogeType,ModelMap model){
		boolean flag= catelogeTypeService.updatecatelogeType(catelogeType);
		if (flag) {
			return "empty";
		}else {
			model.addAttribute("catelogeType_id", catelogeType.getCatelog_type_id());
			return "redirect:/manager/catelogtype/toUpdate";
		}
	}
}
