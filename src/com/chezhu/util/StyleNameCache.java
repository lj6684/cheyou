package com.chezhu.util;

import java.util.ArrayList;
import java.util.List;


import com.alibaba.fastjson.JSON;
import com.chezhu.dao.StyleService;
import com.chezhu.dao.model.Style;

public class StyleNameCache {
	
	private StyleService styleService = ContextUtil.getBean(StyleService.class, "styleService");
	private List<String> names;
	private String jsonStr;
	private static StyleNameCache instance;
	
	private StyleNameCache() {
		
	}
	
	public static StyleNameCache getInstance() {
		if(instance == null) {
			synchronized(StyleNameCache.class) {
				if(instance == null) {
					instance = new StyleNameCache();
				}
			}
		}
		return instance;
	}
	
	public void refresh() {
		List<Style> styles = styleService.getAllStyles();
		names = new ArrayList<String>();
		for(Style style : styles) {
			names.add(style.getName());
		}
		
		jsonStr = JSON.toJSONString(names);
	}
	
	public List<String> getAllStyleNames() {
		return names;
	}
	
	public String getAllStyleNamesJson() {
		return jsonStr;
	}

}
