package com.yunpan.front.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yunpan.front.bean.FileMenusBean;
import com.yunpan.front.bean.ResourseBean;
import com.yunpan.front.bean.ShareResourceBean;
import com.yunpan.front.service.FileManagermentService;
import com.yunpan.front.service.FileMenuManagerService;
import com.yunpan.front.service.ShareResourceService;

@Controller
@RequestMapping("/files")
public class FileUploadController {
	
	private Logger logger=LoggerFactory.getLogger(FileUploadController.class);
	
	@Autowired
	private FileManagermentService fileManagermentService;
	
	@Autowired
	private FileMenuManagerService fileMenuManagerService;
	
	@Autowired
	private ShareResourceService shareResourceService;
	
	@RequestMapping(value="/fileupload",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, String> fileupload(HttpServletRequest request,
			@RequestParam(name="file") MultipartFile files,
			@CookieValue(name="TOKEN_SSO",required=false) String token,
			String current_path_id,
			String current_path) throws IOException{
		Map<String, String> map=new HashMap<>();
		boolean flag=fileManagermentService.saveFile(files,token,current_path_id,current_path);
		if (flag) {
			map.put("jsonrpc", "2.0");
			map.put("result", null);
			map.put("id", "id");
			return map;
		}else{
			return map;
		}
		
	}
	
	
	@RequestMapping(value="/layer/path/list/{type}",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FileMenusBean> filesList(String parent_id,
			@CookieValue(name="TOKEN_SSO",required=false) String token,
			@PathVariable(value="type") String type,String name){
		try {
			List<FileMenusBean> fileMenusBeans= fileMenuManagerService.fetchCurentPathByParentIdAndUser(Integer.parseInt(parent_id),token,type,name);
			return fileMenusBeans;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			List<FileMenusBean> fileMenusBeans= fileMenuManagerService.fetchCurentPathByParentIdAndUser(0,token,type,name);
			return fileMenusBeans;
		}
	}
	
	
	@RequestMapping(value="/layer/filepath/list/{type}",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ResourseBean> resourseList(String parent_id,
			@CookieValue(name="TOKEN_SSO",required=false) String token,
			@PathVariable(value="type") String type,String name){
		try {
			List<ResourseBean> resourseBeans=fileManagermentService.fetchCurrentResourseByParentIdAndUser(Integer.parseInt(parent_id),token,type,name);
			return resourseBeans;
		} catch (NumberFormatException e) {
			List<ResourseBean> resourseBeans=fileManagermentService.fetchCurrentResourseByParentIdAndUser(0,token,type,name);
			return resourseBeans;
		}
		
	}
	
	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(String fileid){
		
		ResponseEntity<byte[]> responseEntity= fileManagermentService.updatedownloadResourceByFiledId(fileid);
		
		return  responseEntity;
		
	}
	
	@RequestMapping("/chpwd")
	public String changeMenu(String menu_id,String parent_path,ModelMap model) throws UnsupportedEncodingException{
		FileMenusBean menusBean = fileMenuManagerService.fetchById(menu_id, FileMenusBean.class);
		model.addAttribute("current_path", menusBean.getVirthalenv_path());
		model.addAttribute("current_path_id", menu_id);
		model.addAttribute("parent_path_id", parent_path);
		return "index";
	}
	
	@RequestMapping("/createFloder")
	public String saveFloder(String fileName,
			String current_path,
			String current_path_id,
			String parent_path_id,
			@CookieValue(name="TOKEN_SSO",required=false) String token,
			ModelMap model) throws UnsupportedEncodingException{
		boolean flag= fileMenuManagerService.saveFloder(fileName,new String(current_path.getBytes("iso-8859-1"),"utf-8") ,parent_path_id,token);
		logger.debug("创建文件夹："+fileName+" 状态："+flag);
		model.addAttribute("current_path", new String(current_path.getBytes("iso-8859-1"),"utf-8"));
		model.addAttribute("current_path_id", current_path_id);
		model.addAttribute("parent_path_id", parent_path_id);
		return "index";
	}
	
	@RequestMapping("/deleteFiles")
	@ResponseBody
	public boolean deleteFile(String fileId,@CookieValue(name="TOKEN_SSO",required=false) String token){
		
		boolean flag= fileManagermentService.updateFileToStation(fileId,token);
		
		return flag;
	}
	
	
	@RequestMapping("/deleteFloders")
	@ResponseBody
	public boolean deleteFloder(String fileId,@CookieValue(name="TOKEN_SSO",required=false) String token){
		
		boolean flag= fileMenuManagerService.updateFloderToStation(fileId,token);
		
		return flag;
	}
	
	
		//访问资源列表页面,返回值是一个资源访问页面
		//其中type :   fl  ; fi
		@RequestMapping(value="/share/{type}/{random}")
		public String sharedFile(String userId,
				String resourceId,
				@PathVariable(value="type") String type,@PathVariable(value="random") String random,
				ModelMap model,HttpServletResponse response){
			//id需要进行解密
			List<ShareResourceBean>  shareResourceBeans= shareResourceService.fetchSharedResourseByIdAndResourceID(type,userId,resourceId);
			model.addAttribute("shareResourceBeans", shareResourceBeans);
			model.addAttribute("shared_user", shareResourceBeans.get(0).getCreater_name());
			model.addAttribute("create_date", shareResourceBeans.get(0).getCreate_time());
			model.addAttribute("path", "/");
			model.addAttribute("is_file", shareResourceBeans.get(0).getIsFloder());
			model.addAttribute("root_path","/files/share/"+type+"/"+random+"?userId="+URLEncoder.encode(userId)+"&resourceId="+URLEncoder.encode(resourceId));
			model.addAttribute("parent_id", "1");
			return "resource_share";
		}
	
		//创建访问分享资源的Url和密码，url中的资源需要进行加密
		//生成链接地址
		@RequestMapping(value="/gennerateShared",produces=MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public Map<String, Object> createSharedFile(String resourceId ,String type,String privaliege,
				@CookieValue(name="TOKEN_SSO",required=false) String token){
			Map<String, Object> map= shareResourceService.saveShareResourceInfo(resourceId,type,privaliege,token);
			return map;
		}
	
		
		@RequestMapping("/rename")
		@ResponseBody
		public boolean rename(String resource_id,
				String newName,
				String type,
				@CookieValue(name="TOKEN_SSO",required=false) String token,
				String current_path,
				String current_path_id,
				String parent_path_id,
				ModelMap model) throws UnsupportedEncodingException{
			boolean flag=false;
			if (type.equals("floder")) {
			  flag=	fileMenuManagerService.updateRenameFloder(resource_id,newName,token);
			}else if (type.equals("file")) {
			  flag=	fileManagermentService.updateRenameResource(resource_id,newName,token);
			}
			model.addAttribute("current_path", new String(current_path.getBytes("iso-8859-1"),"utf-8"));
			model.addAttribute("current_path_id", current_path_id);
			model.addAttribute("parent_path_id", parent_path_id);
			return flag;
		}
		
		
		
		@RequestMapping("/shares")
		public String listShare(String resourceId ,
				@CookieValue(name="TOKEN_SSO",required=false) String token,
				ModelMap model,String resource_path) throws UnsupportedEncodingException{
			
			List<ShareResourceBean> shareResourceBeans= shareResourceService.fetchSharedResouseList(resourceId,token);
			if (shareResourceBeans!=null && shareResourceBeans.size()>0) {
				model.addAttribute("shareResourceBeans", shareResourceBeans);
				model.addAttribute("shared_user", new String(shareResourceBeans.get(0).getCreater_name().getBytes("iso-8859-1"),"utf-8"));
				model.addAttribute("create_date", shareResourceBeans.get(0).getCreate_time());
				model.addAttribute("is_file", shareResourceBeans.get(0).getIsFloder());
			}
			model.addAttribute("path", new String(resource_path.getBytes("iso-8859-1") ,"utf-8"));
			
			return "resource_share";
		}
		
		
		@RequestMapping(value="/save/share",produces=MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public Map<String, Object> saveShareFile(String resourceId,
				@CookieValue(name="TOKEN_SSO",required=false) String token,
				String parentId){//parent_id为文件夹id
			
			Map<String, Object> message= shareResourceService.saveSahreResourceIntoMyMenu(resourceId,token,parentId);
			
			return message;
		}
		
		
		@RequestMapping(value="/moveTo",produces=MediaType.APPLICATION_JSON_VALUE)
		@ResponseBody
		public Map<String, Object> moveFileToNewFloder(String resourceId,
				@CookieValue(name="TOKEN_SSO",required=false) String token,
				String parentId){//parent_id为文件夹id
			
			Map<String, Object> message= fileMenuManagerService.updateFileToNewPosition(resourceId,token,parentId);
			
			
			return message;
		}
		
		@RequestMapping("/file/enterPass")
		public String enterPasswd(String shareResourceId,String code,String url,ModelMap model){
			
			boolean flag= shareResourceService.enterShareResourceByShareCode(shareResourceId,code);
			
			model.addAttribute("root_path",url);
			
			return "redirect:"+url;
			
		}
	
}
