package com.chezhu.dao;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.service.IdEntityService;

import com.chezhu.dao.model.Filter;

public class FilterService extends IdEntityService<Filter> {
	
	public Filter addFilter(Filter filter) {
		return this.dao().insert(filter);
	}
	
	public int updateFilter(Filter filter) {
		return this.dao().update(filter);
	}
	
	public List<Filter> getAllFilters() {
		return this.query(null, null);
	}
	
	public List<Filter> getFiltersByBrand(int brandId, int supplyId) {
		Condition c = Cnd.where("brandId", "=", brandId).and("supplyId", "=", supplyId);
		return this.query(c, null);
	}

}
