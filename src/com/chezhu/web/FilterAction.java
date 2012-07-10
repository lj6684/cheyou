package com.chezhu.web;

import java.util.List;

import com.chezhu.dao.BrandService;
import com.chezhu.dao.FilterDescpService;
import com.chezhu.dao.FilterService;
import com.chezhu.dao.FilterViewService;
import com.chezhu.dao.StyleService;
import com.chezhu.dao.SupplyService;
import com.chezhu.dao.model.Brand;
import com.chezhu.dao.model.Filter;
import com.chezhu.dao.model.FilterDescp;
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
	
	// 三滤描述相关
	private boolean hasType0;
	private boolean hasType1;
	private boolean hasType2;
	private boolean hasType3;
	private boolean hasType4;
	
	private int type;
	private String descp = "请输入详细信息";
	
	private String act;
	private Filter filter = new Filter();
	
	private BrandService brandService = ContextUtil.getBean(BrandService.class, "brandService");
	private FilterService filterService = ContextUtil.getBean(FilterService.class, "filterService");
	private SupplyService supplyService = ContextUtil.getBean(SupplyService.class, "supplyService");
	private StyleService styleService = ContextUtil.getBean(StyleService.class, "styleService");
	private FilterViewService filterViewService = ContextUtil.getBean(FilterViewService.class, "filterViewService");
	private FilterDescpService filterDescpService = ContextUtil.getBean(FilterDescpService.class, "filterDescpService");

	
	public boolean isHasType0() {
		return hasType0;
	}

	public void setHasType0(boolean hasType0) {
		this.hasType0 = hasType0;
	}

	public boolean isHasType1() {
		return hasType1;
	}

	public void setHasType1(boolean hasType1) {
		this.hasType1 = hasType1;
	}

	public boolean isHasType2() {
		return hasType2;
	}

	public void setHasType2(boolean hasType2) {
		this.hasType2 = hasType2;
	}

	public boolean isHasType3() {
		return hasType3;
	}

	public void setHasType3(boolean hasType3) {
		this.hasType3 = hasType3;
	}

	public boolean isHasType4() {
		return hasType4;
	}

	public void setHasType4(boolean hasType4) {
		this.hasType4 = hasType4;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

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
			// 检查是否已经存在描述信息
			FilterDescp filterDescp = filterDescpService.fetch(filterId, 0);
			if(filterDescp != null) {
				hasType0 = true;
			}
			filterDescp = filterDescpService.fetch(filterId, 1);
			if(filterDescp != null) {
				hasType1 = true;
			}
			filterDescp = filterDescpService.fetch(filterId, 2);
			if(filterDescp != null) {
				hasType2 = true;
			}
			filterDescp = filterDescpService.fetch(filterId, 3);
			if(filterDescp != null) {
				hasType3 = true;
			}
			filterDescp = filterDescpService.fetch(filterId, 4);
			if(filterDescp != null) {
				hasType4 = true;
			}
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
	
	public String delete() throws Exception {
		filterService.delete(filterId);
		initData();
		return SUCCESS;
	}
	
	public String showDescp() throws Exception {
		// type
		// { 0:"air", 1:"machineOil", 2:"fuelOil", 3:"airConditionStd", 4:"airConditionCarbon" }
		if(act.equals("add")) {
			descp = "请填写详细信息";
		} else {
			FilterDescp res = filterDescpService.fetch(filterId, type);
			if(res != null) {
				descp = res.getFilterDescp();
			}
		}
		
		return "showdescp";
	}
	
	public String saveDescp() {
		// 先查找是否存在详细描述
		FilterDescp res = filterDescpService.fetch(filterId, type);
		if(res != null) {
			// 存在-更新
			res.setFilterDescp(descp);
			filterDescpService.update(res);
		} else {
			// 不存在-插入
			FilterDescp filterDescp = new FilterDescp();
			filterDescp.setFilterId(filterId);
			filterDescp.setFilterType(type);
			filterDescp.setFilterDescp(descp);
			
			filterDescpService.insert(filterDescp);
		}
		
		
		ActionContext.getContext().put("success", true);
		
		return "savedescp";
	}
	
	private void initData() {
		ActionContext ctx = ActionContext.getContext();
		List<Brand> brands = brandService.getAllBrands();
		ctx.put("brands", brands);
			
		List<Supply> supplies = supplyService.getAllSupplies();
		ctx.put("supplies", supplies);
		
		List<FilterView> styleFilters = filterViewService.getStyleFilters(brandId, supplyId);
		ctx.put("filters", styleFilters);
	}
}
