package com.chezhu.dao;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.service.IdNameEntityService;

import com.chezhu.dao.model.Supply;

public class SupplyService extends IdNameEntityService<Supply> {
	
	public Supply addSupply(Supply supply) {
		return this.dao().insert(supply);
	}
	
	public int updateSupply(Supply supply) {
		return this.dao().update(supply);
	}
	
	public List<Supply> getAllSupplies() {
		return this.query(Cnd.orderBy().asc("order_index"), null);
	}
	
	public List<Supply> getSuppliesById(List<String> supplyIds) {
		Condition c = Cnd.where("supply_id", "in", supplyIds).asc("order_index");
		return this.query(c, null);
	}

}
