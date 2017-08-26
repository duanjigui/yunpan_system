package com.yunpan.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 
 * @ClassName: SystemBackupController
 * @Description: 系统备份controller
 * 				    系统备份就是将数据库中的数据都查询一遍，将其以json的格式输出到文件中
 * 				    系统恢复就是重新执行以下sql语句，将数据执行完毕	
 * 
 * @author duanjigui
 * @date 2017年4月20日 下午11:28:19
 *
 */
@Controller
@RequestMapping("manager/system")
public class SystemBackupController {

	
	@RequestMapping("/list")
	public String list(){
		
		return "/manager/system/systemBack";
	}
	
	
	
}
