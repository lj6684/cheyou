package com.chezhu.web;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

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
import com.chezhu.util.PageMaker;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FilterAction extends ActionSupport implements ServletRequestAware, ServletContextAware {
	
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
	
	private int descpId0;
	private int descpId1;
	private int descpId2;
	private int descpId3;
	private int descpId4;
	
	private int descpId;
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
	
	private HttpServletRequest servletRequest;
	private ServletContext servletContext;

	
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getDescpId() {
		return descpId;
	}

	public void setDescpId(int descpId) {
		this.descpId = descpId;
	}

	public int getDescpId0() {
		return descpId0;
	}

	public void setDescpId0(int descpId0) {
		this.descpId0 = descpId0;
	}

	public int getDescpId1() {
		return descpId1;
	}

	public void setDescpId1(int descpId1) {
		this.descpId1 = descpId1;
	}

	public int getDescpId2() {
		return descpId2;
	}

	public void setDescpId2(int descpId2) {
		this.descpId2 = descpId2;
	}

	public int getDescpId3() {
		return descpId3;
	}

	public void setDescpId3(int descpId3) {
		this.descpId3 = descpId3;
	}

	public int getDescpId4() {
		return descpId4;
	}

	public void setDescpId4(int descpId4) {
		this.descpId4 = descpId4;
	}

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

	public String init() {
		Brand brand = brandService.fetch(brandId);
		brandName = brand.getName();
		Style style = styleService.fetch(styleId);
		styleFullName = style.getFullName();
		Supply supply = supplyService.fetch(supplyId);
		supplyName = supply.getName();
		
		if(act.equals("update")) {
			filter = filterService.fetch(filterId);
			
			// 检查是否已经存在描述信息
			FilterDescp filterDescp = filterDescpService.fetch(supplyId, filter.getAir());
			if(filterDescp != null) {
				hasType0 = true;
				descpId0 = filterDescp.getDescpId();
			}
			filterDescp = filterDescpService.fetch(supplyId, filter.getMachineOil());
			if(filterDescp != null) {
				hasType1 = true;
				descpId1 = filterDescp.getDescpId();
			}
			filterDescp = filterDescpService.fetch(supplyId, filter.getFuelOil());
			if(filterDescp != null) {
				hasType2 = true;
				descpId2 = filterDescp.getDescpId();
			}
			filterDescp = filterDescpService.fetch(supplyId, filter.getAirConditionStd());
			if(filterDescp != null) {
				hasType3 = true;
				descpId3 = filterDescp.getDescpId();
			}
			filterDescp = filterDescpService.fetch(supplyId, filter.getAirConditionCarbon());
			if(filterDescp != null) {
				hasType4 = true;
				descpId4 = filterDescp.getDescpId();
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
	
	/**
	 * 查看详细页面
	 * @return
	 * @throws Exception
	 */
	public String showDescp() throws Exception {
		// type
		// { 0:"air", 1:"machineOil", 2:"fuelOil", 3:"airConditionStd", 4:"airConditionCarbon" }
		if(act.equals("add")) {
			descp = "请填写详细信息";
		} else {
			FilterDescp res = filterDescpService.fetch(descpId);
			descp = res.getFilterDescp();
		}
		
		return "showdescp";
	}
	
	/**
	 * 保存详细页面
	 * @return
	 */
	public String saveDescp() {
		if(act.equals("add")) {
			FilterDescp filterDescp = new FilterDescp();
			filterDescp.setSupplyId(supplyId);
			String filterCode = getFilterCode(filterId, type);
			filterDescp.setFilterCode(filterCode);
			filterDescp.setFilterDescp(descp);
			
			filterDescpService.insert(filterDescp);
		} else {
			FilterDescp res = filterDescpService.fetch(descpId);
			res.setFilterDescp(descp);
			
			filterDescpService.update(res);
		}
		
		ActionContext.getContext().put("success", true);
		
		return "showdescp";
	}
	
	/**
	 * 删除详细描述
	 * @return
	 */
	public String deleteDescp() {
		filterDescpService.delete(descpId);
		return init();
	}
	
	/**
	 * 产生静态页面
	 * @return
	 */
	public String genPage() {
		FilterView filterView = filterViewService.fetch(filterId);
		
		String classPath = "";
		try {
			classPath = Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		//System.out.println(classPath);
		String templatePath = classPath + "ftl";
		
		String webAppPath = servletContext.getRealPath("/");
		//System.out.println(webAppPath);
		String outputPath = webAppPath + "sanlv/";
		
		// templatePath =
		// outputPath = 
		PageMaker pageMaker = new PageMaker(templatePath, outputPath);
		try {
			pageMaker.makeFilterPage(filterView);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		ActionContext.getContext().put("genPageSuccess", true);
		
		return init();
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
	
	private String getFilterCode(int filterId, int type) {
		Filter filter = filterService.fetch(filterId);
		if(type == 0) {
			return filter.getAir();
		} else if(type == 1) {
			return filter.getMachineOil();
		} else if(type == 2) {
			return filter.getFuelOil();
		} else if(type == 3) {
			return filter.getAirConditionStd();
		} else if(type == 4) {
			return filter.getAirConditionCarbon();
		} else {
			return null;
		}
	}

	public void setServletRequest(HttpServletRequest arg0) {
		servletRequest = arg0;
		
	}

	public void setServletContext(ServletContext arg0) {
		servletContext = arg0;
		
	}
}
