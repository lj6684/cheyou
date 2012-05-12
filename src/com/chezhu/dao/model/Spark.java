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
	@Column("output_volumn")
	private String outputVolumn;
	@Column("motor")
	private String motor;
	@Column("year")
	private String year;
	@Column("remark")
	private String remark;
	@Column("xun_sn")
	private String xunSn;
	@Column("xun_type")
	private String xunType;
	@Column("chao_sn")
	private String chaoSn;
	@Column("chao_type")
	private String chaoType;
	@Column("rui_sn")
	private String ruiSn;
	@Column("rui_type")
	private String ruiType;
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
	public String getOutputVolumn() {
		return outputVolumn;
	}
	public void setOutputVolumn(String outputVolumn) {
		this.outputVolumn = outputVolumn;
	}
	public String getMotor() {
		return motor;
	}
	public void setMotor(String motor) {
		this.motor = motor;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getXunSn() {
		return xunSn;
	}
	public void setXunSn(String xunSn) {
		this.xunSn = xunSn;
	}
	public String getXunType() {
		return xunType;
	}
	public void setXunType(String xunType) {
		this.xunType = xunType;
	}
	public String getChaoSn() {
		return chaoSn;
	}
	public void setChaoSn(String chaoSn) {
		this.chaoSn = chaoSn;
	}
	public String getChaoType() {
		return chaoType;
	}
	public void setChaoType(String chaoType) {
		this.chaoType = chaoType;
	}
	public String getRuiSn() {
		return ruiSn;
	}
	public void setRuiSn(String ruiSn) {
		this.ruiSn = ruiSn;
	}
	public String getRuiType() {
		return ruiType;
	}
	public void setRuiType(String ruiType) {
		this.ruiType = ruiType;
	}

	
}
