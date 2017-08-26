package com.yunpan.front.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * 
 * @ClassName: HttpService
 * @Description: 对httpclient的一次简单封装
 * @author duanjigui
 * @date 2017年2月17日 上午10:17:15
 *
 */
@Component
public class HttpService {
	@Autowired
	private CloseableHttpClient httpClient;
	/**
	 * 
	 *@author duanjigui
	 *@Description 使用get的方式远程访问url，获取服务端的响应
	 *@date 2017年2月16日上午11:41:55
	 *@param url
	 *@return
	 * @throws Exception 
	 * @throws ClientProtocolException 
	 */
	public String getMessageFromUrl(String url) throws ClientProtocolException, Exception{
		HttpGet httpGet=new HttpGet(url);
		httpGet.addHeader("Host", "localhost:8082");	
		httpGet.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");	
		httpGet.addHeader("Connection", "keep-alive");	
		httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0");
		httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httpGet.addHeader("Accept-Encoding", "gzip, deflate");
		CloseableHttpResponse response = httpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		InputStream content = entity.getContent();
		byte[] s=new byte[512];
		int length=0;
		StringBuilder builder=new StringBuilder();
		while ((length=content.read(s))>0) {
			builder.append(new String(s, 0, length));
		}
		return builder.toString();
	}
	/**
	 * 
	 *@author duanjigui
	 *@Description 使用post的方法，远程获取服务端内容
	 *@date 2017年2月17日上午10:08:08
	 *@param url
	 *@param params
	 *@return
	 *@throws ClientProtocolException
	 *@throws Exception
	 */
	public String postMessageFromUrl(String url,Map<String, String> params) throws ClientProtocolException, Exception{
		HttpPost httpPost=new HttpPost(url);
		
		if (params!=null) {
			List<BasicNameValuePair> parameters=new ArrayList<>();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				BasicNameValuePair basicNameValuePair=new BasicNameValuePair(entry.getKey(), entry.getValue());
				parameters.add(basicNameValuePair);
			}
			HttpEntity entity=new UrlEncodedFormEntity(parameters, "utf-8");
			httpPost.setEntity(entity);
		}
		CloseableHttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		InputStream content = entity.getContent();
		byte[] s=new byte[512];
		int length=0;
		StringBuilder builder=new StringBuilder();
		while ((length=content.read(s))>0) {
			builder.append(new String(s, 0, length));
		}
		return builder.toString();
	}
	
	public InputStream downloadFiles(String url	){
        try {
			HttpGet httpget = new HttpGet(url);  
			HttpResponse response = httpClient.execute(httpget);  
			HttpEntity entity = response.getEntity();  
			InputStream is = entity.getContent();
			return is;
		} catch (UnsupportedOperationException | IOException e) {
			e.printStackTrace();
			return null;
		}  
	}
}
