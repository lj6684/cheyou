package org.cheyou.web;

import java.util.List;

import org.cheyou.dao.BrandService;
import org.cheyou.dao.FilterService;
import org.cheyou.dao.StyleService;
import org.cheyou.dao.SupplyService;
import org.cheyou.dao.model.Brand;
import org.cheyou.dao.model.Filter;
import org.cheyou.dao.model.Style;
import org.cheyou.dao.model.Supply;
import org.cheyou.util.ContextUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FilterAction extends ActionSupport {
	
	private int id;
	
	private int brandId;
	private int styleId;
	private int supplyId;
	
	private Filter filter;
	
	private BrandService brandService = ContextUtil.getBean(BrandService.class, "brandService");
	private StyleService styleService = ContextUtil.getBean(StyleService.class, "styleService");
	private SupplyService supplyService = ContextUtil.getBean(SupplyService.class, "supplyService");
	private FilterService filterService = ContextUtil.getBean(FilterService.class, "filterService");
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public int getStyleId() {
		return styleId;
	}

	public void setStyleId(int styleId) {
		this.styleId = styleId;
	}

	public int getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(int supplyId) {
		this.supplyId = supplyId;
	}


	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public String add() throws Exception {
		filter.setBrandId(brandId);
		filter.setStyleId(styleId);
		filter.setSupplyId(supplyId);
		
		filterService.addFilter(filter);
		
		addActionMessage("添加成功");
		
		List<Brand> brands = brandService.getAllBrands();
		Brand brand = brandService.fetchLinks(brandId);
		List<Style> styles = brand.getStyles();
		List<Supply> supplies = supplyService.getAllSupplies();
		
		ActionContext ctx = ActionContext.getContext();
		ctx.put("brands", brands);
		ctx.put("styles", styles);
		ctx.put("supplies", supplies);
		
		return SUCCESS;
	}
	
	public String init() throws Exception {
		List<Brand> brands = brandService.getAllBrands();
		Brand brand = brands.get(0);
		brand = brandService.fetchLinks(brand.getId());
		brandId = brand.getId();
			
		styleId = brand.getStyles().get(0).getId();
		
		List<Supply> supplies = supplyService.getAllSupplies();
		supplyId = supplies.get(0).getId();
		
		ActionContext ctx = ActionContext.getContext();
		ctx.put("brands", brands);
		ctx.put("styles", brand.getStyles());
		ctx.put("supplies", supplies);
		
		filter = filterService.getFilter(styleId, supplyId);
		
		return SUCCESS;
	}
	
	public String changeBrand() throws Exception {
		// 更新brand，先直接影响sytle变化，默认取styles[0]的相关数据
		ActionContext ctx = ActionContext.getContext();
		
		List<Brand> brands = brandService.getAllBrands();
		ctx.put("brands", brands);
		Brand brand = brandService.fetchLinks(brandId);
		ctx.put("styles", brand.getStyles());
		List<Supply> supplies = supplyService.getAllSupplies();
		ctx.put("supplies", supplies);
		
		styleId = brand.getStyles().get(0).getId();
		
		filter = filterService.getFilter(styleId, supplyId);
		
		return SUCCESS;
	}
	
	public String changeStyleOrSupply() throws Exception {
		// 更新style或者supply，都会直接影响Filter数据
		ActionContext ctx = ActionContext.getContext();
		List<Brand> brands = brandService.getAllBrands();
		ctx.put("brands", brands);
		Brand brand = brandService.fetchLinks(brandId);
		ctx.put("styles", brand.getStyles());
		List<Supply> supplies = supplyService.getAllSupplies();
		ctx.put("supplies", supplies);
		
		filter = filterService.getFilter(styleId, supplyId);
		
		return SUCCESS;
	}
	
	public String delete() throws Exception {
		Style style = styleService.fetch(id);
		brandId = style.getBid();
		styleService.delete(id);
		
		List<Brand> brands = brandService.getAllBrands();
		Brand brand = brandService.fetchLinks(brandId);
		
		ActionContext ctx = ActionContext.getContext();
		ctx.put("brands", brands);
		ctx.put("styles", brand.getStyles());
		
		return SUCCESS;
	}
}
