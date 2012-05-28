package com.chezhu.dao.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("")
public class StyleView {
	@Id
	@Column("style_id")
	private int styleId;
	@Column("brand_id")
	private int brandId;
	@Column("style_name")
	private String styleName;
	@Column("brand_name")
	private String brandName;
	@Column("style_img")
	private String styleImg;
	@Column("brand_img")
	private String brandImg;
	@Column("motor")
	private String motor;
	@Column("outter")
	private String outter;
	@Column("style_fullname")
	private String fullName;
	
	
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getOutter() {
		return outter;
	}
	public void setOutter(String outter) {
		this.outter = outter;
	}
	public String getMotor() {
		return motor;
	}
	public void setMotor(String motor) {
		this.motor = motor;
	}
	public int getStyleId() {
		return styleId;
	}
	public void setStyleId(int styleId) {
		this.styleId = styleId;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	public String getStyleName() {
		return styleName;
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getStyleImg() {
		return styleImg;
	}
	public void setStyleImg(String styleImg) {
		this.styleImg = styleImg;
	}
	public String getBrandImg() {
		return brandImg;
	}
	public void setBrandImg(String brandImg) {
		this.brandImg = brandImg;
	}
	
	

}
