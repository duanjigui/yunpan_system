package com.yunpan.manager.util;

import javax.net.ssl.SSLContext;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;

public class HttpClientUtil {
	
	private static HttpClientUtil util=null;
	
	private HttpClientUtil(){}
	
	public static synchronized HttpClientUtil getHttpClientUtil() {
		if (util==null) {
			util=new HttpClientUtil();
		}
		return util;
	}
	
	
	public Registry<ConnectionSocketFactory> getRegistry() throws Exception{
		LayeredConnectionSocketFactory factory=new SSLConnectionSocketFactory(SSLContext.getDefault());
		Registry<ConnectionSocketFactory> socketFactoryRegistry=RegistryBuilder.<ConnectionSocketFactory>create()
				.register("https", factory)
				.register("http", new PlainConnectionSocketFactory())
				.build();
		return socketFactoryRegistry;
	}
	
	
}
