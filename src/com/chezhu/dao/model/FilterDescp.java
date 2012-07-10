package com.chezhu.dao.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("filter_descp")
public class FilterDescp {
	@Id
	@Column("descp_id")
	private int descpId;
	@Column("supply_id")
	private int supplyId;
	@Column("filter_code")
	private String filterCode;
	@Column("filter_descp")
	private String filterDescp;
	
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
	public String getFilterCode() {
		return filterCode;
	}
	public void setFilterCode(String filterCode) {
		this.filterCode = filterCode;
	}
	public String getFilterDescp() {
		return filterDescp;
	}
	public void setFilterDescp(String filterDescp) {
		this.filterDescp = filterDescp;
	}
	
	
	
	
	
}
