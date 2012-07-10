package com.chezhu.dao;


import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.service.IdEntityService;

import com.chezhu.dao.model.FilterDescp;

public class FilterDescpService extends IdEntityService<FilterDescp> {
	
	public FilterDescp fetch(int supplyId, String filterCode) {
		if(filterCode == null || filterCode.trim().equals("")) {
			return null;
		}
		
		Condition c = Cnd.where("supplyId", "=", supplyId).and("filterCode", "=", filterCode);
		List<FilterDescp> resList = this.query(c, null);
		if(resList != null && resList.size() > 0) {
			return resList.get(0);
		} else {
			return null;
		}
	}

	public FilterDescp insert(FilterDescp entity) {
		return this.dao().insert(entity);
	}
	
	public int update(FilterDescp entity) {
		return this.dao().update(entity);
	}
}
