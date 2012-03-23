package org.cheyou.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cheyou.dao.model.Filter;
import org.cheyou.dao.model.Style;
import org.cheyou.dao.model.StyleFilterView;
import org.cheyou.util.ContextUtil;
import org.nutz.service.IdEntityService;

public class StyleFilterViewService extends IdEntityService<StyleFilterView> {
	
	private StyleService styleService = ContextUtil.getBean(StyleService.class, "styleService");
	private FilterService filterService = ContextUtil.getBean(FilterService.class, "filterService");
	
	public List<StyleFilterView> getStyleFilters(int brandId, int supplyId) {
		List<Style> styles = styleService.getStyles(brandId);
		
		Map<Integer, StyleFilterView> resMap = new HashMap<Integer, StyleFilterView>();
		for(Style style : styles) {
			StyleFilterView sfView = new StyleFilterView();
			sfView.setStyleId(style.getId());
			sfView.setStyleName(style.getName());
			sfView.setStyleImg(style.getImg());
			sfView.setBrandId(style.getBid());
			sfView.setSupplyId(supplyId);
			resMap.put(style.getId(), sfView);
		}
		
		List<Filter> filters = filterService.getFilterByBrand(brandId, supplyId);
		for(Filter filter : filters) {
			int styleId = filter.getStyleId();
			StyleFilterView sfView = resMap.get(styleId);
			sfView.setFilterId(filter.getId());
			sfView.setAir(filter.getAir());
			sfView.setMachineOil(filter.getMachineOil());
			sfView.setFuelOil(filter.getFuelOil());
			sfView.setAirConditionStd(filter.getAirConditionStd());
			sfView.setAirConditionCarbon(filter.getAirConditionCarbon());
			resMap.put(styleId, sfView);
		}
		
		List<StyleFilterView> resList = new ArrayList(resMap.values());
		return resList;
	}

}
