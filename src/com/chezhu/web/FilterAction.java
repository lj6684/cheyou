package com.chezhu.web;

import java.util.List;


import com.chezhu.dao.BrandService;
import com.chezhu.dao.FilterService;
import com.chezhu.dao.FilterViewService;
import com.chezhu.dao.StyleService;
import com.chezhu.dao.SupplyService;
import com.chezhu.dao.model.Brand;
import com.chezhu.dao.model.Filter;
import com.chezhu.dao.model.FilterView;
import com.chezhu.dao.model.Style;
import com.chezhu.dao.model.Supply;
import com.chezhu.util.ContextUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FilterAction extends ActionSupport {
	
	private int brandId;
	private String brandName;
	private int supplyId;
	private String supplyName;
	private int styleId;
	private String styleFullName;
	private int filterId;
	
	private String act;
	private Filter filter = new Filter();
	
	private BrandService brandService = ContextUtil.getBean(BrandService.class, "brandService");
	private FilterService filterService = ContextUtil.getBean(FilterService.class, "filterService");
	private SupplyService supplyService = ContextUtil.getBean(SupplyService.class, "supplyService");
	private StyleService styleService = ContextUtil.getBean(StyleService.class, "styleService");
	private FilterViewService FilterService = ContextUtil.getBean(FilterViewService.class, "filterViewService");
	
	
	
	public String getStyleFullName() {
		return styleFullName;
	}

	public void setStyleFullName(String styleFullName) {
		this.styleFullName = styleFullName;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public int getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(int supplyId) {
		this.supplyId = supplyId;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public int getStyleId() {
		return styleId;
	}

	public void setStyleId(int styleId) {
		this.styleId = styleId;
	}

	public int getFilterId() {
		return filterId;
	}

	public void setFilterId(int filterId) {
		this.filterId = filterId;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public StyleService getStyleService() {
		return styleService;
	}

	public void setStyleService(StyleService styleService) {
		this.styleService = styleService;
	}

	public String init() throws Exception {
		Brand brand = brandService.fetch(brandId);
		brandName = brand.getName();
		Style style = styleService.fetch(styleId);
		styleFullName = style.getFullName();
		Supply supply = supplyService.fetch(supplyId);
		supplyName = supply.getName();
		
		if(act.equals("update")) {
			filter = filterService.fetch(filterId);
		}
		
		return INPUT;
	}
	
	public String add() throws Exception {
		if(act.equals("add")) {
			filterService.addFilter(filter);
		} else if(act.equals("update")) {
			filterService.updateFilter(filter);
		}
		initData();
		
		return SUCCESS;
	}
	
	public String delete() throws Exception{
		filterService.delete(filterId);
		initData();
		return SUCCESS;
	}
	
	private void initData() {
		ActionContext ctx = ActionContext.getContext();
		List<Brand> brands = brandService.getAllBrands();
		ctx.put("brands", brands);
			
		List<Supply> supplies = supplyService.getAllSupplies();
		ctx.put("supplies", supplies);
		
		List<FilterView> styleFilters = FilterService.getStyleFilters(brandId, supplyId);
		ctx.put("filters", styleFilters);
	}
}
