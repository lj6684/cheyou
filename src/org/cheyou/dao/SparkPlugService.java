package org.cheyou.dao;

import java.util.List;

import org.cheyou.dao.model.Filter;
import org.cheyou.dao.model.SparkPlug;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.service.IdEntityService;

public class SparkPlugService extends IdEntityService<SparkPlug> {
	
	public SparkPlug addSparkPlug(SparkPlug sparkPlug) {
		return this.dao().insert(sparkPlug);
	}
	
	public int updateSparkPlug(SparkPlug sparkPlug) {
		return this.dao().update(sparkPlug);
	}
	
	public List<SparkPlug> getAllSparkPlugs() {
		return this.query(null, null);
	}
	
	public List<SparkPlug> getSparkPlugByBrand(int brandId, int supplyId) {
		Condition c = Cnd.where("brandId", "=", brandId).and("supplyId", "=", supplyId);
		return this.query(c, null);
	}
}
