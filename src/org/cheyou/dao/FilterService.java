package org.cheyou.dao;

import java.util.List;

import org.cheyou.dao.model.Filter;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.service.IdEntityService;

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
	
	public Filter getFilter(int styleId, int supplyId) {
		Condition c = Cnd.where("styleId", "=", styleId).and("supplyId", "=", supplyId);
		List<Filter> filters = this.dao().query(Filter.class, c);
		if(filters.size() > 0) {
			return filters.get(0);
		} else {
			return new Filter();
		}
	}

}
