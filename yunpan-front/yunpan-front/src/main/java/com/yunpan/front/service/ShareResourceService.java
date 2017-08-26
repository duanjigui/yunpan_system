package com.yunpan.front.service;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunpan.front.bean.Common;
import com.yunpan.front.bean.FileMenusBean;
import com.yunpan.front.bean.ResourseBean;
import com.yunpan.front.bean.ShareResourceBean;
import com.yunpan.front.bean.User;
import com.yunpan.front.dao.ResourseManagerDao;
import com.yunpan.front.enums.FileType;
import com.yunpan.front.util.DesUtil;
import com.yunpan.front.util.FileInfoConvertUtil;
import com.yunpan.front.util.RandomUtil;
@Service
public class ShareResourceService extends BaseService<ShareResourceBean>{

	@Value(value="${SERVER_URL}")
	private String USER_INFO_URL;
	
	private final static String SHARE_RESOURCE_URL="/files/share";
	
	@Autowired
	private FileMenuManagerService fileMenuManagerService;
	
	@Autowired
	private FileManagermentService fileManagermentService;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	public Map<String, Object> saveShareResourceInfo(String resourceId, String type, String privaliege, String token) {
		try {
			ShareResourceBean shareResourceBean=new ShareResourceBean();
			String message = httpService.getMessageFromUrl(USER_INFO_URL+"/"+token);
			String user_id="";
			if (message !=null) {
				User session = objectMapper.readValue(message,User.class);
				if (session != null) {
					user_id = session.getUser_id();
				}
			}
			if (type.equals("floder")) {
				FileMenusBean fileMenusBean = fileMenuManagerService.fetchById(resourceId, FileMenusBean.class);
				shareResourceBean.setResource_id(fileMenusBean.getFile_menu_id());
				shareResourceBean.setIsFloder(1);
				shareResourceBean.setResource_name(fileMenusBean.getFlie_menu_name());
				shareResourceBean.setShare_type("文件");
				String url=SHARE_RESOURCE_URL+"/fl" +"/"+Math.round(Math.random()*100)+"?userId="+URLEncoder.encode(DesUtil.encrypt(user_id))+"&resourceId="+URLEncoder.encode(DesUtil.encrypt(resourceId));
				shareResourceBean.setShare_url(url);
			}else if (type.equals("file")) {
				ResourseBean resourseBean = fileManagermentService.fetchById(resourceId, ResourseBean.class);
				resourseBean.setIs_share_file(1);
				fileManagermentService.updateInfo(resourseBean);
				shareResourceBean.setResource_id(resourseBean.getResource_id());
				shareResourceBean.setResource_name(resourseBean.getResource_name());
				shareResourceBean.setShare_type(resourseBean.getResource_type());
				shareResourceBean.setIsFloder(0);
				String url=SHARE_RESOURCE_URL+"/fi" +"/"+Math.round(Math.random()*100)+"?userId="+URLEncoder.encode(DesUtil.encrypt(user_id))+"&resourceId="+URLEncoder.encode(DesUtil.encrypt(resourceId));
				shareResourceBean.setShare_url(url);
			}else {
				return null;
			}
			if (privaliege.equals("1")) {//公开分享
				shareResourceBean.setShare_code(null);	
			}else {//私密分享
				shareResourceBean.setShare_code(RandomUtil.randomCode());
			}
			
			//默认失效日期为3个月
			Calendar endDate = Calendar.getInstance();
			endDate.add(Calendar.DAY_OF_MONTH, 30*3);
			shareResourceBean.setEnd_time(endDate.getTime());
			
			if (super.saveInfo(shareResourceBean, token, USER_INFO_URL)) {
				Map<String, Object> map=new HashMap<>();
				map.put("share_url", shareResourceBean.getShare_url());
				map.put("share_code", shareResourceBean.getShare_code());
				map.put("share_type", privaliege);
				map.put("end_date", shareResourceBean.getEnd_time());
				return map;
			}else {
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	public List<ShareResourceBean> fetchSharedResourseByIdAndResourceID(String type,String userId,
			String resourceId) {
			try {
				userId= DesUtil.decrypt(userId);
				resourceId=DesUtil.decrypt(resourceId);
				ShareResourceBean shareResourceBean=new ShareResourceBean();
				shareResourceBean.setCreater(userId);
				shareResourceBean.setResource_id(Integer.parseInt(resourceId));
				if (type.equals("fi")) {
					shareResourceBean.setIsFloder(0);
				}else if (type.equals("fl")) {
					shareResourceBean.setIsFloder(1);
				}
				ResourseBean resourseBean = fileManagermentService.fetchById(resourceId, ResourseBean.class);
				List<ShareResourceBean> shareResourceBeans = super.mapper.select(shareResourceBean);
				for (ShareResourceBean temp : shareResourceBeans) {
					if (temp.getShare_type().equals("文件")) {
						temp.setFileType(FileType.FLODER.getName());
					}else{
						temp.setFileType(FileInfoConvertUtil.convertFileType(temp.getResource_name(), temp.getShare_type()).getName());
					}
					temp.setDownloadNum(resourseBean.getDownload_num());	
					temp.setSharedResourceUrl(resourseBean.getResource_url());
				}
			
				return shareResourceBeans;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	}

	
	public List<ShareResourceBean> fetchSharedResouseList(String resourceId, String token) {
		
		try {
			//根据资源id，查找父目录为该id的目录
			
			FileMenusBean fileMenusBean=new FileMenusBean();
			fileMenusBean.setParent_id(Integer.parseInt(resourceId));
			fileMenusBean.setIs_delete(0);
			List<FileMenusBean> fileMenusBeans= fileMenuManagerService.fetchMenuListByExample(fileMenusBean);
			
			ResourseBean resourseBean=new ResourseBean();
			resourseBean.setPar_res_id(Integer.parseInt(resourceId));
			resourseBean.setIs_delete(0);
			List<ResourseBean> resourseBeans= fileManagermentService.fetchResourceListByExample(resourseBean);
			
			//根据资源id,查找父目录为该id的资源
			
			//组装成List<ShareResourceBean> 类型
			List<ShareResourceBean> list=new ArrayList<>();
			if (fileMenusBeans!=null &&fileMenusBeans.size()>0) {
				for (FileMenusBean temp : fileMenusBeans) {
					ShareResourceBean shareResourceBean=new ShareResourceBean();
					shareResourceBean.setResource_id(temp.getFile_menu_id());
					shareResourceBean.setResource_name(temp.getFlie_menu_name());
					shareResourceBean.setFileType(FileType.FLODER.getName());
					shareResourceBean.setCreate_time(temp.getCreate_time());
					shareResourceBean.setDownloadNum(0);
					shareResourceBean.setIsFloder(1);
					shareResourceBean.setShare_type("文件");
					shareResourceBean.setCreater_name(temp.getCreater_name());
					list.add(shareResourceBean);
				}
			}
			if (resourseBeans!=null && resourseBeans.size()>0) {
				for (ResourseBean temp : resourseBeans) {
					ShareResourceBean shareResourceBean=new ShareResourceBean();
					shareResourceBean.setResource_id(temp.getResource_id());
					shareResourceBean.setResource_name(temp.getResource_name());
					shareResourceBean.setFileType(FileInfoConvertUtil.convertFileType(temp.getResource_name(), temp.getResource_type()).getName());
					shareResourceBean.setCreate_time(temp.getCreate_time());
					shareResourceBean.setDownloadNum(temp.getDownload_num());
					shareResourceBean.setSharedResourceUrl(temp.getResource_url());
					shareResourceBean.setShare_type(temp.getResource_type());
					shareResourceBean.setIsFloder(0);
					shareResourceBean.setCreater_name(temp.getCreater_name());
					list.add(shareResourceBean);
				}
			}
			
			return list;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	/**
	 * 
	 *@author duanjigui
	 *@Description 保存资源到我的目录
	 *@date 2017年5月4日下午12:29:14
	 *@param resourceId
	 *@param token
	 *@param parentId
	 *@return
	 */
	public Map<String, Object> saveSahreResourceIntoMyMenu(String resourceId, String token, String parentId) {
		Map<String, Object> messages=new HashMap<>();
		try {
			String message = httpService.getMessageFromUrl(USER_INFO_URL+"/"+token);
			if (message !=null) {
				User session = objectMapper.readValue(message,User.class);
				if (session != null) {
					String user_id = session.getUser_id();
					//1.先查看资源是否存在
					ResourseBean resourseBean=new ResourseBean();
					resourseBean.setCreater(user_id);
					resourseBean.setResource_id(Integer.parseInt(resourceId));
					List<ResourseBean> resourseBeans = fileManagermentService.fetchResourceListByExample(resourseBean);
					if (resourseBeans!=null && resourseBeans.size()>0) {
						messages.put("status", "500");
						messages.put("error_message", "该资源在您的云盘中已经存在！");
					}else {
						//2.不存在的话保存资源
						ResourseBean info = fileManagermentService.fetchById(resourceId, ResourseBean.class);
						
						ResourseBean temp=new ResourseBean();
						temp.setIs_delete(0);
						temp.setDownload_num(0);
						temp.setResource_type(info.getResource_type());
						temp.setIs_catalog(0);
						temp.setIs_share_file(0);
						temp.setPar_res_id(Integer.parseInt(parentId));
						temp.setRes_true_name(info.getRes_true_name());
						temp.setRes_virtural_url(info.getRes_virtural_url());
						temp.setResource_name(info.getResource_name());
						temp.setResource_size(info.getResource_size());
						temp.setResource_url(info.getResource_url());
						boolean flag = fileManagermentService.saveInfo(temp, token, USER_INFO_URL);
						if (flag) {
							messages.put("status", "200");
							messages.put("error_message", null);
						}else {
							messages.put("status", "500");
							messages.put("error_message", "服务器未知错误！");
						}
					}
				}
			}
			return messages;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public boolean enterShareResourceByShareCode(String shareResourceId, String code) {
		
		boolean flag=false;
		
		ShareResourceBean shareResourceBean = super.fetchById(shareResourceId, ShareResourceBean.class);
		
		if (shareResourceBean.getShare_code().equals(code)) {
			Map<Integer, Boolean> map = (Map<Integer, Boolean>) Common.FILE_SHARE_INFO.get(shareResourceBean.getCreater());
			map.put(shareResourceBean.getResource_id(), true);
			Common.FILE_SHARE_INFO.put(shareResourceBean.getCreater(), map);
			flag=true;
		}
		return flag;
	}

}
