package org.cheyou.web;

import java.util.List;

import org.cheyou.dao.BrandService;
import org.cheyou.dao.SparkPlugService;
import org.cheyou.dao.SparkPlugViewService;
import org.cheyou.dao.StyleService;
import org.cheyou.dao.SupplyService;
import org.cheyou.dao.model.Brand;
import org.cheyou.dao.model.SparkPlug;
import org.cheyou.dao.model.SparkPlugView;
import org.cheyou.dao.model.Style;
import org.cheyou.dao.model.Supply;
import org.cheyou.util.ContextUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SparkPlugAction extends ActionSupport {
	
	private int brandId;
	private String brandName;
	private int supplyId;
	private String supplyName;
	private int styleId;
	private String styleName;
	private int spId;
	
	private String act;
	private SparkPlug sparkPlug = new SparkPlug();
	
	private BrandService brandService = ContextUtil.getBean(BrandService.class, "brandService");
	private SparkPlugService sparkPlugService = ContextUtil.getBean(SparkPlugService.class, "sparkPlugService");
	private SupplyService supplyService = ContextUtil.getBean(SupplyService.class, "supplyService");
	private StyleService styleService = ContextUtil.getBean(StyleService.class, "styleService");
	private SparkPlugViewService sparkPlugViewService = ContextUtil.getBean(SparkPlugViewService.class, "sparkPlugViewService");
	

	public int getSpId() {
		return spId;
	}

	public void setSpId(int spId) {
		this.spId = spId;
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

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public SparkPlug getSparkPlug() {
		return sparkPlug;
	}

	public void setSparkPlug(SparkPlug sparkPlug) {
		this.sparkPlug = sparkPlug;
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
		styleName = style.getName();
		Supply supply = supplyService.fetch(supplyId);
		supplyName = supply.getName();
		
		if(act.equals("update")) {
			sparkPlug = sparkPlugService.fetch(spId);
		}
		
		return INPUT;
	}
	
	public String add() throws Exception {
		if(act.equals("add")) {
			sparkPlugService.addSparkPlug(sparkPlug);
		} else if(act.equals("update")) {
			sparkPlugService.updateSparkPlug(sparkPlug);
		}
		initData();
		
		return SUCCESS;
	}
	
	public String delete() throws Exception{
		sparkPlugService.delete(spId);
		initData();
		return SUCCESS;
	}
	
	private void initData() {
		ActionContext ctx = ActionContext.getContext();
		List<Brand> brands = brandService.getAllBrands();
		ctx.put("brands", brands);
			
		List<Supply> supplies = supplyService.getAllSupplies();
		ctx.put("supplies", supplies);
		
		List<SparkPlugView> sparkPlugs = sparkPlugViewService.getStyleSparkPlugs(brandId, supplyId);
		ctx.put("sparkPlugs", sparkPlugs);
	}
}
