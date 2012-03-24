package org.cheyou.dao.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.View;

@View("spark_plug_view")
public class SparkPlugView {	
	@Id
	@Column("sp_id")
	private int spId;
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
	@Column("supply_id")
	private int supplyId;
	@Column("supply_name")
	private String supplyName;
	@Column("supply_img")
	private String supplyImg;
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
