package org.cheyou.dao;

import java.util.List;

import org.cheyou.dao.model.Supply;
import org.nutz.service.IdNameEntityService;

public class SupplyService extends IdNameEntityService<Supply> {
	
	public Supply addSupply(Supply supply) {
		return this.dao().insert(supply);
	}
	
	public int updateSupply(Supply supply) {
		return this.dao().update(supply);
	}
	
	public List<Supply> getAllSupplies() {
		return this.query(null, null);
	}

}