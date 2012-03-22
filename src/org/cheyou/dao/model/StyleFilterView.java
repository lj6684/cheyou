package org.cheyou.dao.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.View;

@View("style_filter_view")
public class StyleFilterView {
	@Id
	@Column("style_id")
	private int styleId;
	@Column("style_name")
	private String styleName;
	@Column("style_img")
	private String styleImg;
	@Column("brand_id")
	private int brandId;
	@Column("supply_id")
	private int supplyId;
	@Column("filter_id")
	private int filterId;
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
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	public int getSupplyId() {
		return supplyId;
	}
	public void setSupplyId(int supplyId) {
		this.supplyId = supplyId;
	}
	public int getFilterId() {
		return filterId;
	}
	public void setFilterId(int filterId) {
		this.filterId = filterId;
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
	
	
}
