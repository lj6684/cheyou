package com.chezhu.dao.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("descp_aircdcarbon")
public class DescpAirCdCarbon {
	@Id(auto=false)
	@Column("filter_id")
	private int filterId;
	@Column("descp")
	private String descp;
	
	public int getFilterId() {
		return filterId;
	}
	public void setFilterId(int filterId) {
		this.filterId = filterId;
	}
	public String getDescp() {
		return descp;
	}
	public void setDescp(String descp) {
		this.descp = descp;
	}
	
	
}
