package com.chezhu.web;

import java.util.List;


import com.chezhu.dao.BrandService;
import com.chezhu.dao.SparkService;
import com.chezhu.dao.SparkViewService;
import com.chezhu.dao.StyleService;
import com.chezhu.dao.SupplyService;
import com.chezhu.dao.model.Brand;
import com.chezhu.dao.model.Spark;
import com.chezhu.dao.model.SparkView;
import com.chezhu.dao.model.Style;
import com.chezhu.dao.model.Supply;
import com.chezhu.util.ContextUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SparkAction extends ActionSupport {
	
	private int brandId;
	private String brandName;
	private int supplyId;
	private String supplyName;
	private int styleId;
	private String styleName;
	private int sparkId;
	
	private String act;
	private Spark spark = new Spark();
	
	private BrandService brandService = ContextUtil.getBean(BrandService.class, "brandService");
	private SparkService sparkService = ContextUtil.getBean(SparkService.class, "sparkService");
	private SupplyService supplyService = ContextUtil.getBean(SupplyService.class, "supplyService");
	private StyleService styleService = ContextUtil.getBean(StyleService.class, "styleService");
	private SparkViewService sparkViewService = ContextUtil.getBean(SparkViewService.class, "sparkViewService");
	

	public int getSparkId() {
		return sparkId;
	}

	public void setSparkId(int sparkId) {
		this.sparkId = sparkId;
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

	public Spark getSpark() {
		return spark;
	}

	public void setSpark(Spark spark) {
		this.spark = spark;
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
			spark = sparkService.fetch(sparkId);
		}
		
		return INPUT;
	}
	
	public String add() throws Exception {
		if(act.equals("add")) {
			sparkService.addSpark(spark);
		} else if(act.equals("update")) {
			sparkService.updateSpark(spark);
		}
		initData();
		
		return SUCCESS;
	}
	
	public String delete() throws Exception{
		sparkService.delete(sparkId);
		initData();
		return SUCCESS;
	}
	
	private void initData() {
		
	}
}
