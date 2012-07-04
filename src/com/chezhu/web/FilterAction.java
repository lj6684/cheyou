package com.chezhu.web;

import java.util.List;

import com.chezhu.dao.BrandService;
import com.chezhu.dao.DescpAirCdCarbonService;
import com.chezhu.dao.DescpAirCdStdService;
import com.chezhu.dao.DescpAirService;
import com.chezhu.dao.DescpFuelOilService;
import com.chezhu.dao.DescpMachineOilService;
import com.chezhu.dao.FilterService;
import com.chezhu.dao.FilterViewService;
import com.chezhu.dao.StyleService;
import com.chezhu.dao.SupplyService;
import com.chezhu.dao.model.Brand;
import com.chezhu.dao.model.DescpAir;
import com.chezhu.dao.model.DescpAirCdCarbon;
import com.chezhu.dao.model.DescpAirCdStd;
import com.chezhu.dao.model.DescpFuelOil;
import com.chezhu.dao.model.DescpMachineOil;
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
	
	// 三滤描述相关
	private int type;
	private String descp;
	
	private String act;
	private Filter filter = new Filter();
	
	private BrandService brandService = ContextUtil.getBean(BrandService.class, "brandService");
	private FilterService filterService = ContextUtil.getBean(FilterService.class, "filterService");
	private SupplyService supplyService = ContextUtil.getBean(SupplyService.class, "supplyService");
	private StyleService styleService = ContextUtil.getBean(StyleService.class, "styleService");
	private FilterViewService filterViewService = ContextUtil.getBean(FilterViewService.class, "filterViewService");
	private DescpAirService descpAirService = ContextUtil.getBean(DescpAirService.class, "descpAirService");
	private DescpMachineOilService descpMachineOilService = ContextUtil.getBean(DescpMachineOilService.class, "descpMachineOilService");
	private DescpFuelOilService descpFuelOilService = ContextUtil.getBean(DescpFuelOilService.class, "descpFuelOilService");
	private DescpAirCdStdService descpAirCdStdService = ContextUtil.getBean(DescpAirCdStdService.class, "descpAirCdStdService");
	private DescpAirCdCarbonService descpAirCdCarbonService = ContextUtil.getBean(DescpAirCdCarbonService.class, "descpAirCdCarbonService");
	
	
	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
		descp = "";
		switch(type) {
		case 0:
			DescpAir airD = descpAirService.fetch(filterId);
			if(airD != null) {
				descp = airD.getDescp();
			}
			break;
		case 1:
			DescpMachineOil machineOilD = descpMachineOilService.fetch(filterId);
			if(machineOilD != null) {
				descp = machineOilD.getDescp();
			}
			break;
		case 2:
			DescpFuelOil fuelOilD = descpFuelOilService.fetch(filterId);
			if(fuelOilD != null) {
				descp = fuelOilD.getDescp();
			}
			break;
		case 3:
			DescpAirCdStd airCdStdD = descpAirCdStdService.fetch(filterId);
			if(airCdStdD != null) {
				descp = airCdStdD.getDescp();
			}
			break;
		case 4:
			DescpAirCdCarbon airCdCarbonD = descpAirCdCarbonService.fetch(filterId);
			if(airCdCarbonD != null) {
				descp = airCdCarbonD.getDescp();
			}
			break;
		}
		return "descp";
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
