package com.chezhu.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.service.EntityService;

import com.chezhu.dao.model.Filter;
import com.chezhu.dao.model.FilterView;
import com.chezhu.dao.model.Style;
import com.chezhu.dao.model.Supply;
import com.chezhu.util.ContextUtil;

public class FilterViewService extends EntityService<FilterView> {
	
	private StyleService styleService = ContextUtil.getBean(StyleService.class, "styleService");
	private FilterService filterService = ContextUtil.getBean(FilterService.class, "filterService");
	private SupplyService supplyService = ContextUtil.getBean(SupplyService.class, "supplyService");
	
	public List<FilterView> queryFilterView(String queryStr, List<String> supplyIds) {
		String ids = "(";
		for(int i = 0; i < supplyIds.size(); i++) {
			if(i == 0) {
				ids += supplyIds.get(i);
			} else {
				ids += ", " + supplyIds.get(i);
			}
		}
		ids += ")";
		Sql sql = Sqls.create("SELECT f.filter_id, sp.supply_id, sp.supply_name, sp.supply_img, b.brand_id, b.brand_name, b.brand_img, st.style_id, st.style_name, st.style_img, f.air, f.machine_oil, f.fuel_oil, f.air_condition_std, f.air_condition_carbon " +
				"FROM ((filter f " +
				"INNER JOIN supply sp ON f.supply_id = sp.supply_id) " +
				"INNER JOIN style st ON f.style_id = st.style_id) " + 
				"INNER JOIN brand b ON f.brand_id = b.brand_id " +
				"WHERE st.style_name LIKE @name AND sp.supply_id IN " + ids);
		sql.params().set("name", "%" + queryStr + "%");
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(this.dao().getEntity(FilterView.class));
		this.dao().execute(sql);
		List<FilterView> res = sql.getList(FilterView.class);
		return res;
	}
	
	public Map<String, Map<String, FilterView>> queryFilters(String queryStr, List<String> supplyIds) {
		List<Supply> supplies = supplyService.getSuppliesById(supplyIds);
		
		Map<String, Map<String, FilterView>> res = new HashMap<String, Map<String, FilterView>>();
		List<Style> styles = styleService.query(queryStr);
		for(Style style : styles) {
			res.put(style.getName(), createSampleMap(supplies));
		}
		
		List<FilterView> filters = this.queryFilterView(queryStr, supplyIds);
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
		
		List<FilterView> resList = new ArrayList<FilterView>(resMap.values());
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
