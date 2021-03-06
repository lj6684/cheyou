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
	@Column("style_motor")
	private String styleMotor;
	@Column("style_outter")
	private String styleOutter;
	@Column("style_fullname")
	private String styleFullName;
	
	
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
