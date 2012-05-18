package com.chezhu.dao;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.chezhu.dao.model.FilterView;
import com.chezhu.util.ContextUtil;

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
	public void test_query_filters() {
		List<String> supplyIds = new ArrayList<String>();
		supplyIds.add("1");
		supplyIds.add("2");
		supplyIds.add("3");
		List<FilterView> list = service.queryFilterView("速腾", supplyIds);
		Assert.assertTrue(list.size() > 0);
	}

}
