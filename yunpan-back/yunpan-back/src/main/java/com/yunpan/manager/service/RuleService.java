package com.yunpan.manager.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yunpan.manager.bean.RuleBean;
@Service
public class RuleService extends BaseService<RuleBean>{
	@Value(value="${SERVER_URL}")
	private String SERVER_URL;

	public boolean saveRule(RuleBean rule, String token) {
		
		return super.saveInfo(rule, token, SERVER_URL);
	}

	public boolean updateRule(RuleBean rule) {
		return super.updateInfo(rule);
	}

	public boolean deleteRuleByIds(String[] ids) {
		return super.deleteByIds(ids, RuleBean.class);
	}
	
	
}
