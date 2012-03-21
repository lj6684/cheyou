package org.cheyou.dao;

import java.util.List;

import org.cheyou.dao.model.Brand;
import org.nutz.service.IdNameEntityService;

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
