package com.chezhu.dao;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.service.IdEntityService;

import com.chezhu.dao.model.StyleView;

public class StyleViewService extends IdEntityService<StyleView> {
	
	public List<StyleView> query(String name) {
		Condition c = Cnd.where("style_name", "like", "%" + name + "%");
		return this.query(c, null);
	}

}
