package org.cheyou.dao.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("supply")
public class Supply {
	@Id
	@Column("supply_id")
	private int id;
	@Name
	@Column("supply_name")
	private String name;
	@Column("supply_img")
	private String img;
	
	
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
		return "Supply [id=" + id + ", name=" + name + ", img=" + img + "]";
	}
	
	

}
