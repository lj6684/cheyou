package com.chezhu.dao.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("style")
public class Style {
	@Id
	@Column("style_id")
	private int id;
	@Name
	@Column("style_name")
	private String name;
	@Column("brand_id")
	private int bid;
	@Column("style_img")
	private String img;	
	@Column("motor")
	private String motor;
	
	
	public String getMotor() {
		return motor;
	}
	public void setMotor(String motor) {
		this.motor = motor;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "Style [id=" + id + ", name=" + name + ", bid=" + bid + ", img="
				+ img + "]";
	}

}
