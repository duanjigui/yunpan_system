package com.yunpan.front.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yunpan.front.bean.Common;
import com.yunpan.front.bean.ShareResourceBean;

public class FileShareInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		if (modelAndView!=null) {
			ModelMap model = modelAndView.getModelMap();
			
			if (model !=null) {
				List<ShareResourceBean> list= (List<ShareResourceBean>) model.get("shareResourceBeans");
				
				if (list!=null && list.size() >0) {
					String share_code = list.get(0).getShare_code();
					if (StringUtils.isNotBlank(share_code)) {
						String user_id = list.get(0).getCreater();
						int resource_id = list.get(0).getResource_id();
						if (!Common.FILE_SHARE_INFO.containsKey(user_id)) {
							Common.FILE_SHARE_INFO.put(user_id, null);
						}
						Map<Integer, Boolean> resource_flag = (Map<Integer, Boolean>) Common.FILE_SHARE_INFO.get(user_id);
						if (resource_flag==null || !resource_flag.containsKey(resource_id)) {
							if (resource_flag ==null) {
								resource_flag=new HashMap<>();
							}
							resource_flag.put(resource_id, false);
							Common.FILE_SHARE_INFO.put(user_id, resource_flag);
						}else {
							if (resource_flag.get(resource_id) == true) {
								return ;	
							}
						}
						modelAndView.setViewName("res_share_pass");
					}
				}else {
					modelAndView.setViewName("tip");
				}
			}else {
				modelAndView.setViewName("tip");
			}
		}else {
			modelAndView=new ModelAndView();
			modelAndView.setViewName("tip");
		}
		
		
		
		
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
