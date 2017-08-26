package com.yunpan.front.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunpan.front.bean.FileMenusBean;
import com.yunpan.front.bean.ResourseBean;
import com.yunpan.front.bean.User;
import com.yunpan.front.dao.FileMenuManagerDao;
import com.yunpan.front.enums.FileType;
import com.yunpan.front.util.CONFIG_FLAG;
@Service
public class FileMenuManagerService extends BaseService<FileMenusBean>{
	@Value(value="${SERVER_URL}")
	private String USER_INFO_URL;
	@Autowired
	private FileManagermentService fileManagermentService;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	public List<FileMenusBean> fetchCurentPathByParentIdAndUser(int parentId, String token,String type,String name) {
			
		if (type.equals(CONFIG_FLAG.ALL)) {
			try {
				String message = httpService.getMessageFromUrl(USER_INFO_URL+"/"+token);
				if (message !=null) {
					User session = objectMapper.readValue(message,User.class);
					if (session != null) {
						String user_id = session.getUser_id();
						FileMenusBean fileMenusBean=new FileMenusBean();
						fileMenusBean.setParent_id(parentId);
						fileMenusBean.setCreater(user_id);
						fileMenusBean.setIs_delete(0);
						FileMenuManagerDao fileMenuManagerDao= (FileMenuManagerDao) this.mapper;
						List<FileMenusBean> fileMenusBeans = fileMenuManagerDao.findMenuByExample(fileMenusBean);
						for (FileMenusBean temp : fileMenusBeans) {
							temp.setFileType(FileType.FLODER.getName());
							temp.setSize("-");
						}
						return fileMenusBeans;
					}
				}
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}else if (type.equals(CONFIG_FLAG.SEARCH)) {//模糊查询
			try {
				String message = httpService.getMessageFromUrl(USER_INFO_URL+"/"+token);
				if (message !=null) {
					User session = objectMapper.readValue(message,User.class);
					if (session != null) {
						String user_id = session.getUser_id();
						FileMenuManagerDao fileMenuManagerDao= (FileMenuManagerDao) this.mapper;
						List<FileMenusBean> fileMenusBeans = fileMenuManagerDao.fetchFileMenuByName(name, user_id);
						for (FileMenusBean temp : fileMenusBeans) {
							temp.setFileType(FileType.FLODER.getName());
							temp.setSize("-");
						}
						return fileMenusBeans;
					}
				}
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}else if (type.equals(CONFIG_FLAG.TRASH)) {
			try {
				String message = httpService.getMessageFromUrl(USER_INFO_URL+"/"+token);
				if (message !=null) {
					User session = objectMapper.readValue(message,User.class);
					if (session != null) {
						FileMenusBean fileMenusBean=new FileMenusBean();
						String user_id = session.getUser_id();
						fileMenusBean.setCreater(user_id);
						fileMenusBean.setIs_delete(1);
						List<FileMenusBean> fileMenusBeans = super.mapper.select(fileMenusBean);
						for (FileMenusBean temp : fileMenusBeans) {
							temp.setFileType(FileType.FLODER.getName());
							temp.setSize("-");
						}
						return fileMenusBeans;
					}
				}
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}else{
			return null;
		}
		
	}

	/**
	 * 
	 *@author duanjigui
	 *@Description 创建文件夹
	 *@date 2017年4月30日下午6:05:40
	 *@param fileName
	 *@param parent_path_id
	 *@return
	 */
	public boolean saveFloder(String fileName,String current_path, String parent_path_id,String token) {
		try {
			FileMenusBean fileMenusBean=new FileMenusBean();
			fileMenusBean.setFlie_menu_name(fileName);
			fileMenusBean.setParent_id(Integer.parseInt(parent_path_id));
			fileMenusBean.setIs_delete(0);
			if (current_path.equals("/")) {
				fileMenusBean.setVirthalenv_path("/"+fileName);
			}else {
				fileMenusBean.setVirthalenv_path(current_path+"/"+fileName);
			}
			return super.saveInfo(fileMenusBean, token, USER_INFO_URL);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateFloderToStation(String floderId, String token) {
		try {
			String message = httpService.getMessageFromUrl(USER_INFO_URL+"/"+token);
			if (message !=null) {
				User session = objectMapper.readValue(message,User.class);
				if (session != null) {
					String user_id = session.getUser_id();
					FileMenusBean fileMenusBean=new FileMenusBean();
					fileMenusBean.setFile_menu_id(Integer.parseInt(floderId));
					fileMenusBean.setCreater(user_id);
					fileMenusBean.setIs_delete(1);
					int i= super.mapper.updateByPrimaryKeySelective(fileMenusBean);
					return i==1;
				}else {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean updateRenameFloder(String resource_id, String newName, String token) {
		try {
			String message = httpService.getMessageFromUrl(USER_INFO_URL+"/"+token);
			if (message !=null) {
				User session = objectMapper.readValue(message,User.class);
				if (session != null) {
					String user_id = session.getUser_id();
					FileMenusBean fileMenusBean=new FileMenusBean();
					fileMenusBean.setFile_menu_id(Integer.parseInt(resource_id));
					fileMenusBean.setCreater(user_id);
					fileMenusBean.setFlie_menu_name(newName);
					int i= super.mapper.updateByPrimaryKeySelective(fileMenusBean);
					return i==1;
				}else {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public List<FileMenusBean> fetchMenuListByExample(FileMenusBean fileMenusBean) {
		return super.mapper.select(fileMenusBean);
	}

	/**
	 * 
	 *@author duanjigui
	 *@Description 移动文件到指定的目录
	 *@date 2017年5月4日下午12:34:33
	 *@param resourceId
	 *@param token
	 *@param parentId
	 *@return
	 */
	public Map<String, Object> updateFileToNewPosition(String resourceId, String token, String parentId) {
		Map<String, Object> messages=new HashMap<>();
		try {
			String message = httpService.getMessageFromUrl(USER_INFO_URL+"/"+token);
			if (message !=null) {
				User session = objectMapper.readValue(message,User.class);
				if (session != null) {
						String user_id = session.getUser_id();
						ResourseBean resourseBean=new ResourseBean(); 
						resourseBean.setResource_id(Integer.parseInt(resourceId));
						resourseBean.setPar_res_id(Integer.parseInt(parentId));
						boolean flag = fileManagermentService.updateInfo(resourseBean);
						if (flag) {
							messages.put("status", "200");
							messages.put("message","更新成功！");
						}else {
							messages.put("status", "500");
							messages.put("message", "服务器未知错误，更新失败！");
						}
					}
				}
			return messages;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
}
