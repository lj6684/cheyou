package org.cheyou.dao;

import java.util.List;

import junit.framework.Assert;

import org.cheyou.dao.model.FilterView;
import org.cheyou.util.ContextUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FilterViewServiceTest {
	
	static {
		ContextUtil.initIocContext();
	}
	
	private FilterViewService service;

	@Before
	public void setUp() throws Exception {
		service = ContextUtil.getBean(FilterViewService.class, "filterViewService");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_get_all_filters() {
		List<FilterView> list = service.getAllFilters();
		Assert.assertTrue(list.size() > 0);
	}

}
