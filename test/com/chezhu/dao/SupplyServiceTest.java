package com.chezhu.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.chezhu.dao.SupplyService;
import com.chezhu.dao.model.Supply;
import com.chezhu.util.ContextUtil;

public class SupplyServiceTest {
	
	static {
		ContextUtil.initIocContext();
	}
	
	private SupplyService service;
	
	@Before
	public void setUp() throws Exception {
		service = ContextUtil.getBean(SupplyService.class, "supplyService");
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_add_and_get() {
		Supply supply = new Supply();
		supply.setName("博世");
		supply = service.addSupply(supply);
		
		Supply supply2 = new Supply();
		supply2.setName("索菲玛");
		supply2 = service.addSupply(supply2);
		
		List<Supply> list = service.getAllSupplies();
		Assert.assertTrue(list.size() == 2);
	}
	
	
	@Test
	public void test_update() {
		Supply supply = service.fetch(2);
		supply.setName("博世2");
		int count = service.updateSupply(supply);
		Assert.assertTrue(count == 1);
	}
	
	@Test
	public void test_delete() {
		int count = service.delete(2);
		Assert.assertTrue(count == 1);
	}

}
