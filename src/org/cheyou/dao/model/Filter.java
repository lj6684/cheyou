package org.cheyou.dao.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("filter")
public class Filter {
	@Id
	@Column("filter_id")
	private int id;
	@Column("supply_id")
	private int supplyId;
	@Column("brand_id")
	private int brandId;
	@Column("style_id")
	private int styleId;
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
		return "Filter [id=" + id + ", supplyId=" + supplyId + ", brandId="
				+ brandId + ", styleId=" + styleId + ", air=" + air
				+ ", machineOil=" + machineOil + ", fuelOil=" + fuelOil
				+ ", airConditionStd=" + airConditionStd
				+ ", airConditionCarbon=" + airConditionCarbon + "]";
	}
	
	

}
