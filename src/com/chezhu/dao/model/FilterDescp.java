package com.chezhu.dao.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("filter_descp")
public class FilterDescp {
	
	@Id(auto=false)
	@Column("filter_id")
	private int filterId;
	@Column("filter_type")
	private int filterType;
	@Column("filter_descp")
	private String filterDescp;
	
	public int getFilterId() {
		return filterId;
	}
	public void setFilterId(int filterId) {
		this.filterId = filterId;
	}
	public int getFilterType() {
		return filterType;
	}
	public void setFilterType(int filterType) {
		this.filterType = filterType;
	}
	public String getFilterDescp() {
		return filterDescp;
	}
	public void setFilterDescp(String filterDescp) {
		this.filterDescp = filterDescp;
	}
	
	
}
