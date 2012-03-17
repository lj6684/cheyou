package org.cheyou.dao.model;

import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("brand")
public class Brand {
	@Id
	@Column("brand_id")
	private int id;
	@Name
	@Column("brand_name")
	private String name;
	@Column("brand_img")
	private String img;
	@Many(target=Style.class, field="bid")
	private List<Style> styles;
	
	
	public List<Style> getStyles() {
		return styles;
	}
	public void setStyles(List<Style> styles) {
		this.styles = styles;
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
		return "Brand [id=" + id + ", name=" + name + ", img=" + img + "]";
	}
}
