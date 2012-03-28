package org.cheyou.dao;

import java.util.List;

import org.cheyou.dao.model.Spark;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.service.IdEntityService;

public class SparkService extends IdEntityService<Spark> {
	
	public Spark addSpark(Spark sparkPlug) {
		return this.dao().insert(sparkPlug);
	}
	
	public int updateSpark(Spark sparkPlug) {
		return this.dao().update(sparkPlug);
	}
	
	public List<Spark> getAllSparks() {
		return this.query(null, null);
	}
	
	public List<Spark> getSparksByBrand(int brandId, int supplyId) {
		Condition c = Cnd.where("brandId", "=", brandId).and("supplyId", "=", supplyId);
		return this.query(c, null);
	}
}
