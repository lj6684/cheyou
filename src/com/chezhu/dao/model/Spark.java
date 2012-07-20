package com.chezhu.dao.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("spark")
public class Spark {
	@Id
	@Column("spark_id")
	private int id;
	@Column("brand_id")
	private int brandId;
	@Column("style_id")
	private int styleId;
	@Column("supply_id")
	private int supplyId;
	@Column("standard")
	private String standard;
	@Column("platinum")
	private String platinum;
	@Column("iridium")
	private String iridium;
	@Column("alloy")
	private String alloy;
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
	public int getStyleId() {
		return styleId;
	}
	public void setStyleId(int styleId) {
		this.styleId = styleId;
	}
	public int getSupplyId() {
		return supplyId;
	}
	public void setSupplyId(int supplyId) {
		this.supplyId = supplyId;
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
