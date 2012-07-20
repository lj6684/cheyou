package com.chezhu.dao.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("")
public class SparkView {	
	@Id
	@Column("spark_id")
	private int sparkId;
	@Column("supply_id")
	private int supplyId;
	@Column("supply_name")
	private String supplyName;
	@Column("supply_img")
	private String supplyImg;
	@Column("brand_id")
	private int brandId;
	@Column("brand_name")
	private String brandName;
	@Column("brand_img")
	private String brandImg;
	@Column("style_id")
	private int styleId;
	@Column("style_name")
	private String styleName;
	@Column("style_img")
	private String styleImg;
	@Column("style_motor")
	private String styleMotor;
	@Column("style_outter")
	private String styleOutter;
	@Column("style_fullname")
	private String styleFullName;
	@Column("standard")
	private String standard;
	@Column("platinum")
	private String platinum;
	@Column("iridium")
	private String iridium;
	@Column("alloy")
	private String alloy;
	public int getSparkId() {
		return sparkId;
	}
	public void setSparkId(int sparkId) {
		this.sparkId = sparkId;
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
	public String getSupplyImg() {
		return supplyImg;
	}
	public void setSupplyImg(String supplyImg) {
		this.supplyImg = supplyImg;
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
	public String getBrandImg() {
		return brandImg;
	}
	public void setBrandImg(String brandImg) {
		this.brandImg = brandImg;
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
	public String getStyleImg() {
		return styleImg;
	}
	public void setStyleImg(String styleImg) {
		this.styleImg = styleImg;
	}
	public String getStyleMotor() {
		return styleMotor;
	}
	public void setStyleMotor(String styleMotor) {
		this.styleMotor = styleMotor;
	}
	public String getStyleOutter() {
		return styleOutter;
	}
	public void setStyleOutter(String styleOutter) {
		this.styleOutter = styleOutter;
	}
	public String getStyleFullName() {
		return styleFullName;
	}
	public void setStyleFullName(String styleFullName) {
		this.styleFullName = styleFullName;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getPlatinum() {
		return platinum;
	}
	public void setPlatinum(String platinum) {
		this.platinum = platinum;
	}
	public String getIridium() {
		return iridium;
	}
	public void setIridium(String iridium) {
		this.iridium = iridium;
	}
	public String getAlloy() {
		return alloy;
	}
	public void setAlloy(String alloy) {
		this.alloy = alloy;
	}
	
	

}
