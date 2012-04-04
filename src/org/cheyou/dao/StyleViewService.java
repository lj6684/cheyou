package org.cheyou.dao;

import java.util.List;

import org.cheyou.dao.model.StyleView;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.service.IdEntityService;

public class StyleViewService extends IdEntityService<StyleView> {
	
	public List<StyleView> query(String name) {
		Condition c = Cnd.where("style_name", "like", "%" + name + "%");
		return this.query(c, null);
	}

}
