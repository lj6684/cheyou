package com.chezhu.web;

import java.util.List;


import com.chezhu.dao.BrandService;
import com.chezhu.dao.StyleService;
import com.chezhu.dao.model.Brand;
import com.chezhu.dao.model.Style;
import com.chezhu.util.ContextUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class StyleAction extends ActionSupport {
	
	private int id;
	
	private int brandId;
	private String brandName;
	private String styleName;
	private String motor;
	private BrandService brandService = ContextUtil.getBean(BrandService.class, "brandService");
	private StyleService styleService = ContextUtil.getBean(StyleService.class, "styleService");
	
	
	
	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

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

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public String add() throws Exception {
		Style style = new Style();
		style.setBid(brandId);
		Brand brand = brandService.fetch(brandId);
		style.setName(brand.getName() + " " + styleName);
		style.setMotor(motor);
		styleService.addStyle(style);
		
		brand = brandService.fetchLinks(brandId);
		List<Brand> brands = brandService.getAllBrands();
		List<Style> styles = brand.getStyles();
		
		ActionContext ctx = ActionContext.getContext();
		ctx.put("brands", brands);
		ctx.put("styles", styles);
		
		return SUCCESS;
	}
	
	public String init() throws Exception {
		List<Brand> brands = brandService.getAllBrands();
		Brand brand = brands.get(0);
		brand = brandService.fetchLinks(brand.getId());
		brandId = brand.getId();
			
		ActionContext ctx = ActionContext.getContext();
		ctx.put("brands", brands);
		ctx.put("styles", brand.getStyles());
		
		return SUCCESS;
	}
	
	public String change() throws Exception {
		List<Brand> brands = brandService.getAllBrands();
		Brand brand = brandService.fetchLinks(brandId);
			
		ActionContext ctx = ActionContext.getContext();
		ctx.put("brands", brands);
		ctx.put("styles", brand.getStyles());
		
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
	
	public String view() throws Exception {
		Style style = styleService.fetch(id);
		motor = style.getMotor();
		brandId = style.getBid();
		Brand brand = brandService.fetch(brandId);
		brandName = brand.getName();
		// 显示车型信息时不包括品牌信息，防止品牌信息添加重复
		styleName = style.getName().substring(brandName.length() + 1);
		
		return INPUT;
	}
	
	public String save() throws Exception {
		Style style = styleService.fetch(id);
		Brand brand = brandService.fetch(brandId);
		style.setName(brand.getName() + " " + styleName);
		style.setMotor(motor);
		styleService.updateStyle(style);
		
		brandId = style.getBid();
		
		brand = brandService.fetchLinks(brandId);
		List<Brand> brands = brandService.getAllBrands();
		List<Style> styles = brand.getStyles();
		
		ActionContext ctx = ActionContext.getContext();
		ctx.put("brands", brands);
		ctx.put("styles", styles);
		
		return SUCCESS;
	}
}
