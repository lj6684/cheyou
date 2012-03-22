package org.cheyou.web;

import java.util.List;

import org.cheyou.dao.BrandService;
import org.cheyou.dao.StyleFilterViewService;
import org.cheyou.dao.SupplyService;
import org.cheyou.dao.model.Brand;
import org.cheyou.dao.model.Style;
import org.cheyou.dao.model.StyleFilterView;
import org.cheyou.dao.model.Supply;
import org.cheyou.util.ContextUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class StyleFilterAction extends ActionSupport {
	
	private int styleId;
	private int brandId;
	private int supplyId;
	
	private String brandName;
	private String styleName;
	private String supplyName;
	private StyleFilterView styleFilterView;
	
	private BrandService brandService = ContextUtil.getBean(BrandService.class, "brandService");
	private SupplyService supplyService = ContextUtil.getBean(SupplyService.class, "supplyService");
	private StyleFilterViewService styleFilterService = ContextUtil.getBean(StyleFilterViewService.class, "styleFilterViewService");
	
	
	public int getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(int supplyId) {
		this.supplyId = supplyId;
	}

	public int getStyleId() {
		return styleId;
	}

	public void setStyleId(int styleId) {
		this.styleId = styleId;
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

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public StyleFilterView getStyleFilterView() {
		return styleFilterView;
	}

	public void setStyleFilterView(StyleFilterView styleFilterView) {
		this.styleFilterView = styleFilterView;
	}

	public String add() throws Exception {
		
		
		return SUCCESS;
	}
	
	public String init() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		List<Brand> brands = brandService.getAllBrands();
		Brand brand = brands.get(0);
		brandId = brand.getId();
		ctx.put("brands", brands);
		
		List<Supply> supplies = supplyService.getAllSupplies();
		Supply supply = supplies.get(0);
		supplyId = supply.getId();
		ctx.put("supplies", supplies);
		
		List<StyleFilterView> styleFilters = styleFilterService.getAll(brandId, supplyId);
		ctx.put("styleFilters", styleFilters);
		
		return SUCCESS;
	}
	
	public String change() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		List<Brand> brands = brandService.getAllBrands();
		ctx.put("brands", brands);
			
		List<Supply> supplies = supplyService.getAllSupplies();
		ctx.put("supplies", supplies);
		
		List<StyleFilterView> styleFilters = styleFilterService.getAll(brandId, supplyId);
		ctx.put("styleFilters", styleFilters);
		
		return SUCCESS;
	}
	
	public String delete() throws Exception {
		
		
		return SUCCESS;
	}
	
	public String view() throws Exception {


		return INPUT;
	}
	
	public String save() throws Exception {
		
		
		return SUCCESS;
	}
}
