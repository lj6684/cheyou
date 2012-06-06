package com.chezhu.util;

import java.util.List;

import com.chezhu.dao.BrandService;
import com.chezhu.dao.model.Brand;

public class DBTools {
	
	public DBTools() {
		ContextUtil.initIocContext();
	}
	
	/**
	 * 获得当前品牌信息的 Ruby-Map
	 * key-品牌名称
	 * value-品牌ID
	 * @return
	 */
	public String getBrandMap() {
		BrandService brandService = ContextUtil.getBean(BrandService.class, "brandService");
		List<Brand> brands = brandService.getAllBrands();
		String res = "{";
		for(int i = 0; i < brands.size(); i++) {
			Brand brand = brands.get(i);
			if(i == 0) {
				res += "'" + brand.getName() + "'=>" + brand.getId();
			} else {
				res += " ,'" + brand.getName() + "'=>" + brand.getId();
			}
		}
		res += "}";
		return res;
	}
	

	public static void main(String[] args) {
		DBTools tools = new DBTools();
		String brands = tools.getBrandMap();
		System.out.println(brands);
	}
}
