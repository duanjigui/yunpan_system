package com.yunpan.front.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RandomUtil {
	
	
	
	
	public static String randomCode() {
		StringBuilder builder=new StringBuilder();
		Object[] await_list= {0,1,2,3,4,5,6,7,8,9,
	    "a","b","c","d","e","f","g","h","i","j","k","l","m","n",
	    "o","p","q","r","s","t","u","v","w","x","y","z",
	    "A","B","C","D","E","F","G","H","I","J","K","L","M","N",
	    "O","P","Q","R","S"
	    ,"T","U","V","W","X","Y","Z"
		};
		for(int i=0;i<4;i++){
			builder.append(await_list[(int) (Math.round(Math.random()*100)%62)] );
		}
		return builder.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(randomCode());
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.DAY_OF_MONTH, 30*3);
		System.out.println(format.format(instance.getTime()));
	}
}
