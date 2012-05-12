package com.chezhu.dao;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.service.IdNameEntityService;

import com.chezhu.dao.model.Style;
import com.chezhu.util.StyleNameCache;

public class StyleService extends IdNameEntityService<Style> {
	
	public Style addStyle(Style style) {
		Style res = this.dao().insert(style);
		StyleNameCache.getInstance().refresh();
		return res;
	}
	
	public int updateStyle(Style style) {
		int res = this.dao().update(style);
		StyleNameCache.getInstance().refresh();
		return res;
	}
	
	@Override
	public int delete(long id) {
		int res = super.delete(id);
		StyleNameCache.getInstance().refresh();
		return res;
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
