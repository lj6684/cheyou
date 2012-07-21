package com.chezhu.dao;


import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.service.IdEntityService;

import com.chezhu.dao.model.SparkDescp;

public class SparkDescpService extends IdEntityService<SparkDescp> {
	
	public SparkDescp fetch(int supplyId, String sparkCode) {
		if(sparkCode == null || sparkCode.trim().equals("")) {
			return null;
		}
		
		Condition c = Cnd.where("supplyId", "=", supplyId).and("sparkCode", "=", sparkCode);
		List<SparkDescp> resList = this.query(c, null);
		if(resList != null && resList.size() > 0) {
			return resList.get(0);
		} else {
			return null;
		}
	}

	public SparkDescp insert(SparkDescp entity) {
		return this.dao().insert(entity);
	}
	
	public int update(SparkDescp entity) {
		return this.dao().update(entity);
	}
}
