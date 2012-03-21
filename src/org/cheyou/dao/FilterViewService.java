package org.cheyou.dao;

import java.util.List;

import org.cheyou.dao.model.FilterView;
import org.nutz.service.IdEntityService;

public class FilterViewService extends IdEntityService<FilterView> {

	public List<FilterView> getAllFilters() {
		return this.query(null, null);
	}
}