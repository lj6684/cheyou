package com.chezhu.dao;

import java.util.List;

import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.service.EntityService;

import com.chezhu.dao.model.StyleView;

public class StyleViewService extends EntityService<StyleView> {
	
	public List<StyleView> query(String name) {
		Sql sql = Sqls.create("SELECT st.style_id, st.brand_id, st.style_name, st.style_img, st.style_motor, st.style_outter, st.style_fullname, b.brand_name, b.brand_img " +
				"FROM style st " +
				"INNER JOIN brand b ON st.brand_id = b.brand_id " +
				"WHERE st.style_fullname LIKE @fullName");
		sql.params().set("fullName", "%" + name + "%");
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(this.dao().getEntity(StyleView.class));
		this.dao().execute(sql);
		List<StyleView> res = sql.getList(StyleView.class);
		return res;
	}

}
