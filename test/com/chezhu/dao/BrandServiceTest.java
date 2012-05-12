package com.chezhu.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.chezhu.dao.BrandService;
import com.chezhu.dao.model.Brand;
import com.chezhu.util.ContextUtil;

public class BrandServiceTest {

	static {
		ContextUtil.initIocContext();
	}
	
	private BrandService service;
	
	@Before
	public void setUp() throws Exception {
		service = ContextUtil.getBean(BrandService.class, "brandService");
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_add_and_get() {
		Brand brand = new Brand();
		brand.setName("大众");
		brand = service.addBrand(brand);
		
		Brand brand2 = new Brand();
		brand2.setName("宝马");
		brand = service.addBrand(brand2);
		
		List<Brand> list = service.getAllBrands();
		Assert.assertTrue(list.size() == 2);
	}
	
	
	@Test
	public void test_update() {
		Brand brand = service.fetch(2);
		brand.setName("通用");
		int count = service.updateBrand(brand);
		Assert.assertTrue(count == 1);
	}
	
	@Test
	public void test_delete() {
		int count = service.delete(2);
		Assert.assertTrue(count == 1);
	}

}
