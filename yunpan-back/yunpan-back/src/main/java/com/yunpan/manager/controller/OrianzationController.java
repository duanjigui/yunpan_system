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
import com.yunpan.manager.bean.Orangezation;
import com.yunpan.manager.bean.ResponeTableData;
import com.yunpan.manager.service.OrangzationService;

/**
 * 
 * @ClassName: OrianzationController
 * @Description: 组织管理controller
 * @author duanjigui
 * @date 2017年3月5日 下午10:14:57
 *
 */
@Controller
@RequestMapping("/manager/orianzation")
public class OrianzationController {
	
   private Logger logger =LoggerFactory.getLogger(OrianzationController.class);
   
   private ObjectMapper objectMapper=new ObjectMapper();
	
	@Autowired
	private OrangzationService orangzationService;
	
	@RequestMapping("/list")
	public String list(ModelMap model) throws JsonProcessingException{
		
	    ResponeTableData<Orangezation> responeTableData= orangzationService.fetchAllOrangzations();
		
	    logger.debug("查询出来的组织列表数据为："+responeTableData);	
	    
	    model.addAttribute("responeTableData",objectMapper.writeValueAsString(responeTableData));
		
		return "/manager/orangzatin/list";
	}
	
	@RequestMapping(value="/listAll",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public List<Orangezation> listAllOragzation(){
		return orangzationService.fetchAllOrangzations().getRows();
	}
	
	
	@RequestMapping("/toAdd")
	public String to_add(){
		
		return "/manager/orangzatin/orangzationAdd";
	}
	
	@RequestMapping("/toUpdate")
	public String to_update(String id,ModelMap model){
		
		Orangezation orangezation= orangzationService.fetchOrangzationById(id);
		
		model.addAttribute("orangezation", orangezation);
		
		return "/manager/orangzatin/orangzationUpdate";
	}
	
	@RequestMapping("/update")
	public String update(Orangezation orangezation,ModelMap model){
		
		logger.info(orangezation.toString());
		
		boolean flag= orangzationService.updateOrangzationById(orangezation);
		
		logger.debug("更新"+orangezation.getOrange_id()+"结果为："+flag);
		
		if (flag) {
			return "redirect:/manager/orianzation/list";
		}else {
			model.addAttribute("id", orangezation.getOrange_id());
			
			return "redirect:/manager/orianzation/toUpdate";
		}
	}
	
	@RequestMapping("/delete")
	public String delete(String[] ids){
		
		boolean flag= orangzationService.deleteOrangzationByIds(ids);
		
		logger.debug("删除指定orangzation，结果为："+flag);
		
		return 	"redirect:/manager/orianzation/list";
	}
	
	@RequestMapping("/add")
	public String add(Orangezation orangezation,@CookieValue(name="TOKEN_SSO",required=false) String token){
		
		boolean flag= orangzationService.saveOrangezation(orangezation,token);
		
		return "empty";
	}
	
	
	
}
