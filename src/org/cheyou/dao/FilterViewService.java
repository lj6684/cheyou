package org.cheyou.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cheyou.dao.model.Filter;
import org.cheyou.dao.model.FilterView;
import org.cheyou.dao.model.Style;
import org.cheyou.util.ContextUtil;
import org.nutz.service.IdEntityService;

public class FilterViewService extends IdEntityService<FilterView> {
	
	private StyleService styleService = ContextUtil.getBean(StyleService.class, "styleService");
	private FilterService filterService = ContextUtil.getBean(FilterService.class, "filterService");
	
	public List<FilterView> getAllFilters() {
		return this.query(null, null);
	}
	
	public List<FilterView> getStyleFilters(int brandId, int supplyId) {
		List<Style> styles = styleService.getStyles(brandId);
		
		Map<Integer, FilterView> resMap = new HashMap<Integer, FilterView>();
		for(Style style : styles) {
			FilterView filterView = new FilterView();
			filterView.setStyleId(style.getId());
			filterView.setStyleName(style.getName());
			filterView.setStyleImg(style.getImg());
			filterView.setBrandId(style.getBid());
			filterView.setSupplyId(supplyId);
			resMap.put(style.getId(), filterView);
		}
		
		List<Filter> filters = filterService.getFiltersByBrand(brandId, supplyId);
		for(Filter filter : filters) {
			int styleId = filter.getStyleId();
			FilterView filterView = resMap.get(styleId);
			if(filterView == null) {
				continue;
			}
			filterView.setFilterId(filter.getId());
			filterView.setAir(filter.getAir());
			filterView.setMachineOil(filter.getMachineOil());
			filterView.setFuelOil(filter.getFuelOil());
			filterView.setAirConditionStd(filter.getAirConditionStd());
			filterView.setAirConditionCarbon(filter.getAirConditionCarbon());
			resMap.put(styleId, filterView);
		}
		
		List<FilterView> resList = new ArrayList(resMap.values());
		return resList;
	}

}
