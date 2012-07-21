package com.chezhu.dao.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("spark_descp")
public class SparkDescp {
	@Id
	@Column("descp_id")
	private int descpId;
	@Column("supply_id")
	private int supplyId;
	@Column("spark_code")
	private String sparkCode;
	@Column("spark_descp")
	private String sparkDescp;
	
	public int getDescpId() {
		return descpId;
	}
	public void setDescpId(int descpId) {
		this.descpId = descpId;
	}
	public int getSupplyId() {
		return supplyId;
	}
	public void setSupplyId(int supplyId) {
		this.supplyId = supplyId;
	}
	public String getSparkCode() {
		return sparkCode;
	}
	public void setSparkCode(String sparkCode) {
		this.sparkCode = sparkCode;
	}
	public String getSparkDescp() {
		return sparkDescp;
	}
	public void setSparkDescp(String sparkDescp) {
		this.sparkDescp = sparkDescp;
	}
	
	
	
	
	
	
}
