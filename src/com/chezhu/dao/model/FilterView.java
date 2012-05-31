package com.chezhu.dao.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("")
public class FilterView implements Cloneable {
	
	@Id
	@Column("filter_id")
	private int filterId;
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
	@Column("air")
	private String air;
	@Column("machine_oil")
	private String machineOil;
	@Column("fuel_oil")
	private String fuelOil;
	@Column("air_condition_std")
	private String airConditionStd;
	@Column("air_condition_carbon")
	private String airConditionCarbon;
	
	
	
	public String getStyleFullName() {
		return styleFullName;
	}
	public void setStyleFullName(String styleFullName) {
		this.styleFullName = styleFullName;
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
	public int getSupplyId() {
		return supplyId;
	}
	public void setSupplyId(int supplyId) {
		this.supplyId = supplyId;
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
	public String getAir() {
		return air;
	}
	public void setAir(String air) {
		this.air = air;
	}
	public String getMachineOil() {
		return machineOil;
	}
	public void setMachineOil(String machineOil) {
		this.machineOil = machineOil;
	}
	public String getFuelOil() {
		return fuelOil;
	}
	public void setFuelOil(String fuelOil) {
		this.fuelOil = fuelOil;
	}
	public String getAirConditionStd() {
		return airConditionStd;
	}
	public void setAirConditionStd(String airConditionStd) {
		this.airConditionStd = airConditionStd;
	}
	public String getAirConditionCarbon() {
		return airConditionCarbon;
	}
	public void setAirConditionCarbon(String airConditionCarbon) {
		this.airConditionCarbon = airConditionCarbon;
	}
	public int getFilterId() {
		return filterId;
	}
	public void setFilterId(int filterId) {
		this.filterId = filterId;
	}
	@Override
	public String toString() {
		return "FilterView [air=" + air + ", airConditionCarbon="
				+ airConditionCarbon + ", airConditionStd=" + airConditionStd
				+ ", brandId=" + brandId + ", brandImg=" + brandImg
				+ ", brandName=" + brandName + ", filterId=" + filterId
				+ ", fuelOil=" + fuelOil + ", machineOil=" + machineOil
				+ ", styleId=" + styleId + ", styleImg=" + styleImg
				+ ", styleName=" + styleName + ", supplyId=" + supplyId
				+ ", supplyImg=" + supplyImg + ", supplyName=" + supplyName
				+ "]";
	}
	
}
