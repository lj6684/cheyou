package org.cheyou.web;

import java.util.List;

import org.cheyou.dao.BrandService;
import org.cheyou.dao.model.Brand;
import org.cheyou.util.ContextUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BrandAction extends ActionSupport {
	
	private int id;
	private String brandName;
	private BrandService brandService = ContextUtil.getBean(BrandService.class, "brandService");
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String add() throws Exception {
		Brand brand = new Brand();
		brand.setName(brandName);
		brandService.addBrand(brand);
		
		List<Brand> brands = brandService.getAllBrands();
		ActionContext.getContext().put("brands", brands);
		
		return SUCCESS;
	}
	
	public String init() throws Exception {
		List<Brand> brands = brandService.getAllBrands();
		ActionContext.getContext().put("brands", brands);
		
		return SUCCESS;
	}
	
	public String delete() throws Exception {
		brandService.delete(id);
		
		List<Brand> brands = brandService.getAllBrands();
		ActionContext.getContext().put("brands", brands);
		
		return SUCCESS;
	}
	
	public String view() throws Exception {
		Brand brand = brandService.fetch(id);
		brandName = brand.getName();
		
		return INPUT;
	}
	
	public String save() throws Exception {
		Brand brand = brandService.fetch(id);
		brand.setName(brandName);
		brandService.updateBrand(brand);
		
		List<Brand> brands = brandService.getAllBrands();
		ActionContext.getContext().put("brands", brands);
		
		return SUCCESS;
	}
}
