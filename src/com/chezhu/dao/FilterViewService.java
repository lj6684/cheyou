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
	
	private String querySQL =  "SELECT f.filter_id, sp.supply_id, sp.supply_name, sp.supply_img, b.brand_id, b.brand_name, b.brand_img, st.style_id, st.style_name, st.style_img, st.style_motor, st.style_outter, st.style_fullname, f.air, f.machine_oil, f.fuel_oil, f.air_condition_std, f.air_condition_carbon " +
			"FROM ((filter f " +
			"INNER JOIN supply sp ON f.supply_id = sp.supply_id) " +
			"INNER JOIN style st ON f.style_id = st.style_id) " + 
			"INNER JOIN brand b ON f.brand_id = b.brand_id ";
	
	/**
	 * 根据ID查FilterView
	 * 生成详细页面使用
	 * @param filterId
	 * @return
	 */
	public FilterView fetch(long filterId) {
		Sql sql = Sqls.create(querySQL + "WHERE f.filter_id=@filterId");
		sql.params().set("filterId", filterId);
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(this.dao().getEntity(FilterView.class));
		this.dao().execute(sql);
		return sql.getObject(FilterView.class);
	}
	
	/**
	 * 查询全部FilterView集合
	 * 生成详细页面使用
	 * @return
	 */
	public List<FilterView> getAllFilterViews() {
		Sql sql = Sqls.create(querySQL);
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(this.dao().getEntity(FilterView.class));
		this.dao().execute(sql);
		return sql.getList(FilterView.class);
	}
	
	/**
	 * 根据车型查询同类FilterView集合(排除原厂数据)
	 * 生成详细页面使用
	 * @param id
	 */
	public List<FilterView> queryFilterViewByStyle(long id, long currentSupplyId) {
		// 三滤供应商列表 1-博世 2-原厂 3-索菲玛 4-马勒 5-曼牌 6-豹王
		// 拼接查询条件，不包括当前页面的主供应商
		int[] supplies = {1,3,4,5,6};
		boolean first = true;
		String suppliesStr = "(";
		for(int i = 0; i < supplies.length; i++) {
			if(supplies[i] == currentSupplyId) {
				continue;
			} else {
				if(first) {
					suppliesStr += supplies[i];
					first = false;
				} else {
					suppliesStr += "," + supplies[i];
				}
			}
		}
		suppliesStr += ")";
		
		Sql sql = Sqls.create(querySQL + "WHERE st.style_id=@id AND sp.supply_id in " + suppliesStr);
		sql.params().set("id", id);
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(this.dao().getEntity(FilterView.class));
		this.dao().execute(sql);
		return sql.getList(FilterView.class);
	}
	
	/**
	 * 根据品牌&供应商查询同类FilterView集合
	 * 生成详细页面使用
	 */
	public List<FilterView> queryFilterViewByBrandSP(long brandId, long supplyId) {
		Sql sql = Sqls.create(querySQL + "WHERE b.brand_id=@brandId AND sp.supply_id=@supplyId");
		sql.params().set("brandId", brandId);
		sql.params().set("supplyId", supplyId);
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(this.dao().getEntity(FilterView.class));
		this.dao().execute(sql);
		return sql.getList(FilterView.class);
	}
	
	/**
	 * 根据车型查询同类FilterView原厂数据(对比显示用)
	 * 生成详细页面使用
	 * @param id
	 */
	public FilterView queryOriginalFilterViewByStyle(long id) {
		Sql sql = Sqls.create(querySQL + "WHERE st.style_id=@id AND sp.supply_id=2");
		sql.params().set("id", id);
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(this.dao().getEntity(FilterView.class));
		this.dao().execute(sql);
		return sql.getObject(FilterView.class);
	}
	
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
		Sql sql = Sqls.create(querySQL +
				"WHERE st.style_fullname LIKE @fullName AND sp.supply_id IN " + ids);
		sql.params().set("fullName", "%" + queryStr + "%");
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(this.dao().getEntity(FilterView.class));
		this.dao().execute(sql);
		List<FilterView> res = sql.getList(FilterView.class);
		return res;
	}
	
	/**
	 * 根据queryStr和supplyId列表查询符合结果滤清器集合
	 * @param queryStr
	 * @param supplyIds
	 * @return Map<styleFullName, Map<supplyName, filter>>
	 */
	public Map<String, Map<String, FilterView>> queryFilters(String queryStr, List<String> supplyIds) {
		List<Supply> supplies = supplyService.getSuppliesById(supplyIds);
		
		Map<String, Map<String, FilterView>> res = new HashMap<String, Map<String, FilterView>>();
		List<Style> styles = styleService.query(queryStr);
		for(Style style : styles) {
			res.put(style.getFullName(), createSampleMap(supplies));
		}
		
		List<FilterView> filters = this.queryFilterView(queryStr, supplyIds);
		for(FilterView filter : filters) {
			String styleFullName = filter.getStyleFullName();
			String supplyName = filter.getSupplyName();
			
			res.get(styleFullName).put(supplyName, filter);
		}
		
		return res;
	}
	
	/**
	 * 根据brandId和supplyId查询滤清器列表
	 * @param brandId
	 * @param supplyId
	 * @return
	 */
	public List<FilterView> getStyleFilters(int brandId, int supplyId) {
		List<Style> styles = styleService.getStyles(brandId);
		
		Map<Integer, FilterView> resMap = new HashMap<Integer, FilterView>();
		for(Style style : styles) {
			FilterView filterView = new FilterView();
			filterView.setStyleId(style.getId());
			filterView.setStyleName(style.getName());
			filterView.setStyleImg(style.getImg());
			filterView.setStyleMotor(style.getMotor());
			filterView.setStyleOutter(style.getOutter());
			filterView.setStyleFullName(style.getFullName());
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
