package com.chezhu.dao;

import java.util.List;

import org.nutz.service.IdNameEntityService;

import com.chezhu.dao.model.Brand;

public class BrandService extends IdNameEntityService<Brand>{
	
	public Brand addBrand(Brand brand) {
		return this.dao().insert(brand);
	}
	
	public int updateBrand(Brand brand) {
		return this.dao().update(brand);
	}
	
	public List<Brand> getAllBrands() {
		return this.query(null, null);
	}
	
	public Brand fetchLinks(int id) {
		Brand brand = fetch(id);
		return this.dao().fetchLinks(brand, "styles");
	}
}
