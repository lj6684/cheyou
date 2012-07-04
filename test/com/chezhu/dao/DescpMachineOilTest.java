package com.chezhu.dao;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.chezhu.dao.model.DescpMachineOil;
import com.chezhu.util.ContextUtil;

public class DescpMachineOilTest {
	
	static {
		ContextUtil.initIocContext();
	}
	
	private DescpMachineOilService service;
	
	@Before
	public void setUp() throws Exception {
		service = ContextUtil.getBean(DescpMachineOilService.class, "descpMachineOilService");
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test_insert() {
		DescpMachineOil dmo = new DescpMachineOil();
		dmo.setFilterId(1);
		dmo.setDescp("<html>text</html>");
		
		dmo = service.insert(dmo);
		Assert.assertNotNull(dmo);
	}
	
	@Test
	public void test_fetch() {
		DescpMachineOil dmo = service.fetch(1);
		Assert.assertNotNull(dmo);
	}
	
	@Test
	public void test_update() {
		DescpMachineOil dmo = service.fetch(1);
		dmo.setDescp("test2");
		
		int i = service.update(dmo);
		Assert.assertTrue(i > 0);
	}
	
	@Test
	public void test_delete() {
		int i = service.delete(1);
		Assert.assertTrue(i > 0);
	}

}
