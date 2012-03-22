package org.cheyou.dao;

import java.util.List;

import org.cheyou.dao.model.StyleFilterView;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.service.IdEntityService;

public class StyleFilterViewService extends IdEntityService<StyleFilterView> {
	
	public List<StyleFilterView> getAll(int brandId, int supplyId) {
		SqlExpressionGroup supplyCondition = Cnd.exps("supplyId", "=", supplyId).or("supplyId", "is", null);
		Condition c = Cnd.where("brandId", "=", brandId).and(supplyCondition);
		return this.query(c, null);
	}

}
