package com.yunpan.front.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunpan.front.bean.ResourseBean;
import com.yunpan.front.bean.User;
import com.yunpan.front.dao.ResourseManagerDao;
import com.yunpan.front.util.CONFIG_FLAG;
import com.yunpan.front.util.FileInfoConvertUtil;
import com.yunpan.front.util.FilesUtil;

@Service
public class FileManagermentService extends BaseService<ResourseBean>{
	@Value(value="${SERVER_URL}")
	private String USER_INFO_URL; 
	
	@Value(value="${FILE_SERVER}")
	private String FILE_SERVER; 
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	
	public boolean saveFile(MultipartFile files,String token,String current_path_id,String current_path) {
		String realName=null;
		try {
			String contentType = files.getContentType();
			String originalFilename = files.getOriginalFilename();
			long size = files.getSize();
			InputStream inputStream = files.getInputStream();
			realName = FilesUtil.uploadFileToServer(inputStream, originalFilename,FILE_SERVER);
			if (realName!=null) {
				ResourseBean resourseBean=new ResourseBean();
				resourseBean.setIs_delete(0);
				resourseBean.setIs_share_file(0);
				resourseBean.setResource_size((int)size);
				resourseBean.setResource_type(contentType);
				resourseBean.setResource_name(originalFilename);
				resourseBean.setRes_true_name(realName);
				resourseBean.setDownload_num(0);
				resourseBean.setIs_catalog(0);
				if (current_path.equals("/")) {
					resourseBean.setRes_virtural_url(current_path+realName);//不能准确的判定
				}else{
					resourseBean.setRes_virtural_url(current_path+"/"+realName);//不能准确的判定
				}
				resourseBean.setResource_url(FILE_SERVER+"/"+realName);
				resourseBean.setIs_catalog(0);//根据具体条件判断是否为目录
				resourseBean.setPar_res_id(Integer.parseInt(current_path_id));
				return super.saveInfo(resourseBean, token, USER_INFO_URL);
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<ResourseBean> fetchCurrentResourseByParentIdAndUser(int parentId, String token,String type,String name) {
		if (type.equals(CONFIG_FLAG.SEARCH)) {
			try {
				String message = httpService.getMessageFromUrl(USER_INFO_URL+"/"+token);
				if (message !=null) {
					User session = objectMapper.readValue(message,User.class);
					if (session != null) {
						String user_id = session.getUser_id();
						ResourseManagerDao resourseManagerDao= (ResourseManagerDao) this.mapper;
						List<ResourseBean> resourseBeans = resourseManagerDao.fetchResourceByName(name, user_id);
						for (ResourseBean temp : resourseBeans) {
							temp.setFileType(FileInfoConvertUtil.convertFileType(temp.getResource_name(), temp.getResource_type()).getName());
							temp.setSize(FileInfoConvertUtil.convert(temp.getResource_size()));
						}
						return resourseBeans;
					}
				}
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}else if (type.equals(CONFIG_FLAG.ALL)) {
			try {
				String message = httpService.getMessageFromUrl(USER_INFO_URL+"/"+token);
				if (message !=null) {
					User session = objectMapper.readValue(message,User.class);
					if (session != null) {
						String user_id = session.getUser_id();
						ResourseBean resourseBean=new ResourseBean();
						resourseBean.setPar_res_id(parentId);
						resourseBean.setCreater(user_id);
						resourseBean.setIs_delete(0);//条件3
						resourseBean.setResource_type(null);//条件1
						//resourseBean.setIs_share_file(0);//条件2
						ResourseManagerDao resourseManagerDao= (ResourseManagerDao) this.mapper;
						List<ResourseBean> resourseBeans = resourseManagerDao.findResourceByExample(resourseBean);
						for (ResourseBean temp : resourseBeans) {
							temp.setFileType(FileInfoConvertUtil.convertFileType(temp.getResource_name(), temp.getResource_type()).getName());
							temp.setSize(FileInfoConvertUtil.convert(temp.getResource_size()));
						}
						return resourseBeans;
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
						String user_id = session.getUser_id();
						ResourseBean resourseBean=new ResourseBean();
						resourseBean.setCreater(user_id);
						resourseBean.setIs_delete(1);//条件3
						ResourseManagerDao resourseManagerDao= (ResourseManagerDao) this.mapper;
						List<ResourseBean> resourseBeans = resourseManagerDao.findResourceByExample(resourseBean);
						for (ResourseBean temp : resourseBeans) {
							temp.setFileType(FileInfoConvertUtil.convertFileType(temp.getResource_name(), temp.getResource_type()).getName());
							temp.setSize(FileInfoConvertUtil.convert(temp.getResource_size()));
						}
						return resourseBeans;
					}
				}
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
		}else if (type.equals(CONFIG_FLAG.SHARE)) {
			try {
				String message = httpService.getMessageFromUrl(USER_INFO_URL+"/"+token);
				if (message !=null) {
					User session = objectMapper.readValue(message,User.class);
					if (session != null) {
						String user_id = session.getUser_id();
						ResourseBean resourseBean=new ResourseBean();
						resourseBean.setCreater(user_id);
						resourseBean.setIs_delete(0);//条件3
						resourseBean.setIs_share_file(1);//条件2
						ResourseManagerDao resourseManagerDao= (ResourseManagerDao) this.mapper;
						List<ResourseBean> resourseBeans = resourseManagerDao.findResourceByExample(resourseBean);
						for (ResourseBean temp : resourseBeans) {
							temp.setFileType(FileInfoConvertUtil.convertFileType(temp.getResource_name(), temp.getResource_type()).getName());
							temp.setSize(FileInfoConvertUtil.convert(temp.getResource_size()));
						}
						return resourseBeans;
					}
				}
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
		}else{
			try {
				String message = httpService.getMessageFromUrl(USER_INFO_URL+"/"+token);
				if (message !=null) {
					User session = objectMapper.readValue(message,User.class);
					if (session != null) {
						String user_id = session.getUser_id();
						ResourseBean resourseBean=new ResourseBean();
						resourseBean.setCreater(user_id);
						resourseBean.setIs_delete(0);//条件3
						ResourseManagerDao resourseManagerDao= (ResourseManagerDao) this.mapper;
						List<ResourseBean> resourseBeans = resourseManagerDao.findResourceByExample(resourseBean);
						List<ResourseBean> result=new ArrayList<>();
						for (ResourseBean temp : resourseBeans) {
							temp.setFileType(FileInfoConvertUtil.convertFileType(temp.getResource_name(), temp.getResource_type()).getName());
							temp.setSize(FileInfoConvertUtil.convert(temp.getResource_size()));
							if (type.equals(CONFIG_FLAG.DOCUMENT)) {
								if (temp.getResource_type().contains("text/plain")||temp.getResource_type().contains("pdf")||temp.getResource_type().contains("vnd.ms-powerpoint")||temp.getResource_type().contains("vnd.ms-excel")||temp.getResource_type().contains("msword")) {
								    result.add(temp);
								}
							}else if (type.equals(CONFIG_FLAG.IMAGE)) {
								if (temp.getResource_type().contains("image")) {
								    result.add(temp);
								}
							}else if (type.equals(CONFIG_FLAG.MUSIC)) {
								if (temp.getResource_type().contains("audio")||temp.getResource_type().contains("mp3")) {
								    result.add(temp);
								}
							}else if (type.equals(CONFIG_FLAG.VIEDO)) {
								if (temp.getResource_type().contains("video")||temp.getResource_type().contains(".flv")||temp.getResource_type().contains(".3gp")) {
								    result.add(temp);
								}
							}
						}
						return result;
					}
				}
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
		}
		
		
		
		
		
	}
	/**
	 * 
	 *@author duanjigui
	 *@Description 下载文件
	 *@date 2017年4月30日下午3:36:52
	 *@param fileid
	 *@return
	 */
	public ResponseEntity<byte[]> updatedownloadResourceByFiledId(String fileid) {
		try {
			ResourseBean resourseBean=new ResourseBean();
			resourseBean.setResource_id(Integer.parseInt(fileid));
			ResourseBean temp = this.mapper.selectByPrimaryKey(resourseBean);
			String resource_url = temp.getResource_url();
			//下载次数+1
			temp.setDownload_num(temp.getDownload_num()+1);
			this.mapper.updateByPrimaryKeySelective(temp);
			//通过网络请求获取流
			InputStream inputStream = super.httpService.downloadFiles(resource_url);
			ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
			byte[] bs=new byte[2048];
			int length=0;
			while ((length=inputStream.read(bs))>0) {
				outputStream.write(bs, 0, length);
			}
			HttpHeaders headers=new HttpHeaders();
			headers.setContentDispositionFormData("attachment",new String(temp.getResource_name().getBytes("utf-8"),"iso-8859-1") );
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			return new ResponseEntity<byte[]>(outputStream.toByteArray(),headers,HttpStatus.CREATED);
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean updateFileToStation(String fileId, String token) {
		try {
			String message = httpService.getMessageFromUrl(USER_INFO_URL+"/"+token);
			if (message !=null) {
				User session = objectMapper.readValue(message,User.class);
				if (session != null) {
					String user_id = session.getUser_id();
					ResourseBean resourseBean=new ResourseBean();
					resourseBean.setResource_id(Integer.parseInt(fileId));
					resourseBean.setCreater(user_id);
					resourseBean.setIs_delete(1);
					int i= super.mapper.updateByPrimaryKeySelective(resourseBean);
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

	public boolean updateRenameResource(String resource_id, String newName, String token) {
		try {
			String message = httpService.getMessageFromUrl(USER_INFO_URL+"/"+token);
			if (message !=null) {
				User session = objectMapper.readValue(message,User.class);
				if (session != null) {
					String user_id = session.getUser_id();
					ResourseBean resourseBean=new ResourseBean();
					resourseBean.setResource_id(Integer.parseInt(resource_id));
					resourseBean.setCreater(user_id);
					resourseBean.setResource_name(newName);
					int i= super.mapper.updateByPrimaryKeySelective(resourseBean);
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

	public List<ResourseBean> fetchResourceListByExample(ResourseBean resourseBean) {
		return super.mapper.select(resourseBean);
	}

	
}
