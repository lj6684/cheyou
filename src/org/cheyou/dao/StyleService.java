package org.cheyou.dao;

import java.util.List;

import org.cheyou.dao.model.Style;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.service.IdNameEntityService;

public class StyleService extends IdNameEntityService<Style> {
	
	public Style addStyle(Style style) {
		return this.dao().insert(style);
	}
	
	public int updateStyle(Style style) {
		return this.dao().update(style);
	}
	
	public List<Style> getAllStyles() {
		return this.query(null, null);
	}
	
	public List<Style> getStyles(int brandId) {
		Condition c = Cnd.where("bid", "=", brandId);
		return this.query(c, null);
	}
	
	public List<Style> query(String name) {
		Condition c = Cnd.where("style_name", "like", "%" + name + "%");
		return this.query(c, null);
	}

}
