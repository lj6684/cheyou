package org.cheyou.dao;

import java.util.List;

import junit.framework.Assert;

import org.cheyou.dao.model.Filter;
import org.cheyou.util.ContextUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FilterServiceTest {
	
	private FilterService service;


	static {
		ContextUtil.initIocContext();
	}
	
	@Before
	public void setUp() throws Exception {
		service = ContextUtil.getBean(FilterService.class, "filterService");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_add_and_list() {
		Filter filter = new Filter();
		filter.setBrandId(1);
		filter.setStyleId(1);
		filter.setSupplyId(1);
		filter.setAir("0001");
		filter.setMachineOil("0002");
		filter.setFuelOil("0003");
		filter.setAirConditionStd("004");
		filter.setAirConditionCarbon("0005");
		
		service.addFilter(filter);
		
		List<Filter> list = service.getAllFilters();
		Assert.assertTrue(list.size() > 0);
	}

}
