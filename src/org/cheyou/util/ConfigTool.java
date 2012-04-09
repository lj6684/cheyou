package org.cheyou.util;

import org.nutz.ioc.impl.PropertiesProxy;

public class ConfigTool {
	
	public static String getProperty(String key) {
		PropertiesProxy pp = ContextUtil.getBean(PropertiesProxy.class, "config");
		return pp.get(key);
	}

}
