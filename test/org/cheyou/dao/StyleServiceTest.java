package org.cheyou.dao;

import junit.framework.Assert;

import org.cheyou.dao.model.Brand;
import org.cheyou.dao.model.Style;
import org.cheyou.util.ContextUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StyleServiceTest {

	private StyleService service;
	private BrandService brandService;
	
	static {
		ContextUtil.initIocContext();
	}
	
	@Before
	public void setUp() throws Exception {
		service = ContextUtil.getBean(StyleService.class, "styleService");
		brandService = ContextUtil.getBean(BrandService.class, "brandService");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_add_and_get() {
		Brand brand = brandService.fetch(1);
		Style style = new Style();
		style.setName("速腾 1.6 舒适");
		style.setBid(brand.getId());
		style = service.addStyle(style);
		Assert.assertTrue(style.getId() > 0);
	}
	
	@Test
	public void test_brand_fetchlinks() {
		Brand brand = brandService.fetchLinks(1);
		Assert.assertTrue(brand.getStyles().size() > 0);
	}

}
