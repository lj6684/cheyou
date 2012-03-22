package org.cheyou.dao;

import java.util.List;

import org.cheyou.dao.model.FilterView;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.service.IdEntityService;

public class FilterViewService extends IdEntityService<FilterView> {

	public List<FilterView> getAllFilters() {
		return this.query(null, null);
	}
	
	public List<FilterView> getAllFilters(int brandId) {
		Condition c = Cnd.where("brandId", "=", brandId);
		return this.query(c, null);
	}
}
