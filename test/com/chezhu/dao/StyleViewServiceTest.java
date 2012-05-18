package com.chezhu.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.chezhu.dao.model.StyleView;
import com.chezhu.util.ContextUtil;


public class StyleViewServiceTest {
	
	private StyleViewService service;


	static {
		ContextUtil.initIocContext();
	}
	
	@Before
	public void setUp() throws Exception {
		service = ContextUtil.getBean(StyleViewService.class, "styleViewService");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test_query_styleView() {
		List<StyleView> res = service.query("速腾");
		Assert.assertTrue(res.size() > 0);
	}

}
