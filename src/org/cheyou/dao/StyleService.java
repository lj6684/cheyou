package org.cheyou.dao;

import java.util.List;

import org.cheyou.dao.model.Style;
import org.nutz.service.IdEntityService;

public class StyleService extends IdEntityService<Style> {
	
	public Style addStyle(Style style) {
		return this.dao().insert(style);
	}
	
	public int updateStyle(Style style) {
		return this.dao().update(style);
	}
	
	public List<Style> getAllStyles() {
		return this.query(null, null);
	}

}
