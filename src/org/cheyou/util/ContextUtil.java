package org.cheyou.util;


import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;

public class ContextUtil {
    private static Ioc ioc;

	public static <T> T getBean(Class<T> type, String name) {
		if (ioc != null) {
			return ioc.get(type, name);
		}
		return null;
	}
	
    public static void initIocContext() {
    	ioc = new NutIoc(new JsonLoader(
    			"org/cheyou/config/dao.js",
    			"org/cheyou/config/ioc.js"
    			));
    }
}
