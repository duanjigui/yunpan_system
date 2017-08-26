package com.yunpan.front.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;


public class FilesUtil {
	
	
	/**
	 * 
	 *@author duanjigui
	 *@Description 文件上传到指定的文件服务器上
	 *@date 2017年4月27日下午11:52:13
	 *@param inputStream  inputstem
	 *@param oraginName  原始文件名
	 *@return String 文件名
	 */
	public static String uploadFileToServer(InputStream inputStream,String oraginName,String fileServer){
		try {
			String fileName=genneratorFileName(oraginName);
			
			Client client =new Client();
			WebResource resource = client.resource(fileServer+"/"+fileName);
			
			resource.put(inputStream);
			
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	
	//获取上传到文件服务器的真实地址
	private static String genneratorFileName(String oraginName) {
		//文件后缀
		String suffix = oraginName.substring(oraginName.lastIndexOf(".")+1);
		//生成文件的规则  :   当前时间+1000内的随机数+文件名的hash值
		String realpath= ""+System.currentTimeMillis()+oraginName.hashCode()+Math.round(Math.random()*1000);
		return realpath+"."+suffix;
	} 
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File f=new File("C:\\Users\\duanjigui\\Desktop\\index.html");
		
		FileInputStream inputStream = new FileInputStream(f);
		
		String name = uploadFileToServer(inputStream, "index.html","");
		System.out.println(name);
	}
	
	
}
