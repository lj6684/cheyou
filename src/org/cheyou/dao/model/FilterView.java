package org.cheyou.dao.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.View;

@View("filter_view")
public class FilterView {
	
	@Id
	@Column("filter_id")
	private int id;
	@Column("supply_name")
	private String supplyName;
	@Column("supply_img")
	private String supplyImg;
	@Column("brand_name")
	private String brandName;
	@Column("brand_img")
	private String brandImg;
	@Column("style_name")
	private String styleName;
	@Column("style_img")
	private String styleImg;
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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "FilterView [id=" + id + ", supplyName=" + supplyName
				+ ", supplyImg=" + supplyImg + ", brandName=" + brandName
				+ ", brandImg=" + brandImg + ", styleName=" + styleName
				+ ", styleImg=" + styleImg + ", air=" + air + ", machineOil="
				+ machineOil + ", fuelOil=" + fuelOil + ", airConditionStd="
				+ airConditionStd + ", airConditionCarbon="
				+ airConditionCarbon + "]";
	}
}
