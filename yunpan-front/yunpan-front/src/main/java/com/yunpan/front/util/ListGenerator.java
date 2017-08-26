package com.yunpan.front.util;

/**
 * 
 * @ClassName: ListGenerator
 * @Description: 列表生成器
 * @author duanjigui
 * @date 2017年4月23日 下午12:30:38
 *
 */
public class ListGenerator {

	/**
	 * 
	 *@author duanjigui
	 *@Description 生成列表数据  
	 *			   输入 1,2,3
	 *			 生成：(1,2,3)
	 *@date 2017年4月23日下午12:28:45
	 *@param list
	 *@return
	 */
	public static String write(String[] list){
		StringBuilder builder=new StringBuilder();
		builder.append("(");
		for (String s : list) {
			builder.append(s).append(",");
		}
		builder.deleteCharAt(builder.length()-1);
		builder.append(")");
		return builder.toString();
	}
	
	
	/**
	 * 
	 *@author duanjigui
	 *@Description 解析生成的列表数据
	 *				（1,2,3,4）
	 *				【1,2,3,4】
	 *
	 *@date 2017年4月23日下午12:29:37
	 *@return
	 */
	public static String[] read(String s){
		int left= s.indexOf('(');
		int right= s.indexOf(')');
		System.out.println(left+" "+right);
		if (left>=0&&right>=0) {
			String temp= s.substring(left+1, right);
			return temp.split(",");
		}else {
			System.out.println("信息不合法，校验失败！");
			return null;
		}
		
	}
	
	
	
}
