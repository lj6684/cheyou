package com.chezhu.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.service.IdEntityService;

import com.chezhu.dao.model.Filter;
import com.chezhu.dao.model.FilterView;
import com.chezhu.dao.model.Style;
import com.chezhu.dao.model.Supply;
import com.chezhu.util.ContextUtil;

public class FilterViewService extends IdEntityService<FilterView> {
	
	private StyleService styleService = ContextUtil.getBean(StyleService.class, "styleService");
	private FilterService filterService = ContextUtil.getBean(FilterService.class, "filterService");
	private SupplyService supplyService = ContextUtil.getBean(SupplyService.class, "supplyService");
	
	public List<FilterView> getAllFilters() {
		return this.query(null, null);
	}
	
	public Map<String, Map<String, FilterView>> queryFilters(String queryStr, List<String> supplyIds) {
		List<Supply> supplies = supplyService.getSuppliesById(supplyIds);
		
		Map<String, Map<String, FilterView>> res = new HashMap<String, Map<String, FilterView>>();
		List<Style> styles = styleService.query(queryStr);
		for(Style style : styles) {
			res.put(style.getName(), createSampleMap(supplies));
		}
		
		Condition c = Cnd.where("style_name", "like", "%" + queryStr + "%").and("supply_id", "in", supplyIds);
		List<FilterView> filters = this.query(c, null);
		for(FilterView filter : filters) {
			String styleName = filter.getStyleName();
			String supplyName = filter.getSupplyName();
			
			res.get(styleName).put(supplyName, filter);
		}
		
		return res;
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
	
	private Map<String, FilterView> createSampleMap(List<Supply> supplies) {
		Map<String, FilterView> sample = new HashMap<String, FilterView>();
		for(Supply supply : supplies) {
			FilterView filterView = new FilterView();
			filterView.setSupplyImg(supply.getImg());
			sample.put(supply.getName(), filterView);
		}
		return sample;
	}

}
