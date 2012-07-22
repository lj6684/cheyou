package com.chezhu.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.service.EntityService;

import com.chezhu.dao.model.Spark;
import com.chezhu.dao.model.SparkView;
import com.chezhu.dao.model.Style;
import com.chezhu.dao.model.Supply;
import com.chezhu.util.ContextUtil;

public class SparkViewService extends EntityService<SparkView> {
	
	private StyleService styleService = ContextUtil.getBean(StyleService.class, "styleService");
	private SparkService sparkService = ContextUtil.getBean(SparkService.class, "sparkService");
	private SupplyService supplyService = ContextUtil.getBean(SupplyService.class, "supplyService");
	
	private String querySQL =  "SELECT s.spark_id, sp.supply_id, sp.supply_name, sp.supply_img, b.brand_id, b.brand_name, b.brand_img, st.style_id, st.style_name, st.style_img, st.style_motor, st.style_outter, st.style_fullname, s.standard, s.platinum, s.iridium, s.alloy " +
			"FROM ((spark s " +
			"INNER JOIN supply sp ON s.supply_id = sp.supply_id) " +
			"INNER JOIN style st ON s.style_id = st.style_id) " + 
			"INNER JOIN brand b ON s.brand_id = b.brand_id ";
	
	/**
	 * 根据ID查SparkView
	 * 生成详细页面使用
	 * @param sparkId
	 * @return
	 */
	public SparkView fetch(long sparkId) {
		Sql sql = Sqls.create(querySQL + "WHERE s.spark_id=@sparkId");
		sql.params().set("sparkId", sparkId);
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(this.dao().getEntity(SparkView.class));
		this.dao().execute(sql);
		return sql.getObject(SparkView.class);
	}
	
	/**
	 * 查询全部SparkView集合
	 * 生成详细页面使用
	 * @return
	 */
	public List<SparkView> getAllSparkViews() {
		Sql sql = Sqls.create(querySQL);
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(this.dao().getEntity(SparkView.class));
		this.dao().execute(sql);
		return sql.getList(SparkView.class);
	}
	
	/**
	 * 根据车型查询同类SparkView集合(排除原厂数据)
	 * 生成详细页面使用
	 * @param id
	 */
	public List<SparkView> querySparkViewByStyle(long id, long currentSupplyId) {
		// 火花塞供应商列表 1-博世 7-NGK 8-电装
		// 拼接查询条件，不包括当前页面的主供应商
		int[] supplies = {1,7,8};
		String suppliesStr = "(";
		for(int i = 0; i < supplies.length; i++) {
			if(supplies[i] == currentSupplyId) {
				continue;
			} else {
				if(i == 0) {
					suppliesStr += supplies[0];
				} else {
					suppliesStr += "," + supplies[i];
				}
			}
		}
		suppliesStr += ")";
		
		Sql sql = Sqls.create(querySQL + "WHERE st.style_id=@id AND sp.supply_id in " + suppliesStr);
		sql.params().set("id", id);
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(this.dao().getEntity(SparkView.class));
		this.dao().execute(sql);
		return sql.getList(SparkView.class);
	}
	
	/**
	 * 根据品牌&供应商查询同类SparkView集合
	 * 生成详细页面使用
	 */
	public List<SparkView> querySparkViewByBrandSP(long brandId, long supplyId) {
		Sql sql = Sqls.create(querySQL + "WHERE b.brand_id=@brandId AND sp.supply_id=@supplyId");
		sql.params().set("brandId", brandId);
		sql.params().set("supplyId", supplyId);
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(this.dao().getEntity(SparkView.class));
		this.dao().execute(sql);
		return sql.getList(SparkView.class);
	}
	
	/**
	 * 根据车型查询同类SparkView原厂数据(对比显示用)
	 * 生成详细页面使用
	 * @param id
	 */
	public SparkView queryOriginalSparkViewByStyle(long id) {
		Sql sql = Sqls.create(querySQL + "WHERE st.style_id=@id AND sp.supply_id=2");
		sql.params().set("id", id);
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(this.dao().getEntity(SparkView.class));
		this.dao().execute(sql);
		return sql.getObject(SparkView.class);
	}
	
	public List<SparkView> querySparkView(String queryStr, List<String> supplyIds) {
		String ids = "(";
		for(int i = 0; i < supplyIds.size(); i++) {
			if(i == 0) {
				ids += supplyIds.get(i);
			} else {
				ids += ", " + supplyIds.get(i);
			}
		}
		ids += ")";
		Sql sql = Sqls.create(querySQL +
				"WHERE st.style_fullname LIKE @fullName AND sp.supply_id IN " + ids);
		sql.params().set("fullName", "%" + queryStr + "%");
		sql.setCallback(Sqls.callback.entities());
		sql.setEntity(this.dao().getEntity(SparkView.class));
		this.dao().execute(sql);
		List<SparkView> res = sql.getList(SparkView.class);
		return res;
	}
	
	/**
	 * 根据queryStr和supplyId列表查询符合结果滤清器集合
	 * @param queryStr
	 * @param supplyIds
	 * @return Map<styleFullName, Map<supplyName, spark>>
	 */
	public Map<String, Map<String, SparkView>> querySparks(String queryStr, List<String> supplyIds) {
		List<Supply> supplies = supplyService.getSuppliesById(supplyIds);
		
		Map<String, Map<String, SparkView>> res = new HashMap<String, Map<String, SparkView>>();
		List<Style> styles = styleService.query(queryStr);
		for(Style style : styles) {
			res.put(style.getFullName(), createSampleMap(supplies));
		}
		
		List<SparkView> sparks = this.querySparkView(queryStr, supplyIds);
		for(SparkView spark : sparks) {
			String styleFullName = spark.getStyleFullName();
			String supplyName = spark.getSupplyName();
			
			res.get(styleFullName).put(supplyName, spark);
		}
		
		return res;
	}
	
	/**
	 * 根据brandId和supplyId查询滤清器列表
	 * @param brandId
	 * @param supplyId
	 * @return
	 */
	public List<SparkView> getStyleSparks(int brandId, int supplyId) {
		List<Style> styles = styleService.getStyles(brandId);
		
		Map<Integer, SparkView> resMap = new HashMap<Integer, SparkView>();
		for(Style style : styles) {
			SparkView sparkView = new SparkView();
			sparkView.setStyleId(style.getId());
			sparkView.setStyleName(style.getName());
			sparkView.setStyleImg(style.getImg());
			sparkView.setStyleMotor(style.getMotor());
			sparkView.setStyleOutter(style.getOutter());
			sparkView.setStyleFullName(style.getFullName());
			sparkView.setBrandId(style.getBid());
			sparkView.setSupplyId(supplyId);
			resMap.put(style.getId(), sparkView);
		}
		
		List<Spark> sparks = sparkService.getSparksByBrand(brandId, supplyId);
		for(Spark spark : sparks) {
			int styleId = spark.getStyleId();
			SparkView sparkView = resMap.get(styleId);
			if(sparkView == null) {
				continue;
			}
			sparkView.setSparkId(spark.getId());
			sparkView.setStandard(spark.getStandard());
			sparkView.setPlatinum(spark.getPlatinum());
			sparkView.setIridium(spark.getIridium());
			sparkView.setAlloy(spark.getAlloy());
			
			resMap.put(styleId, sparkView);
		}
		
		List<SparkView> resList = new ArrayList<SparkView>(resMap.values());
		return resList;
	}
	
	private Map<String, SparkView> createSampleMap(List<Supply> supplies) {
		Map<String, SparkView> sample = new HashMap<String, SparkView>();
		for(Supply supply : supplies) {
			SparkView sparkView = new SparkView();
			sparkView.setSupplyImg(supply.getImg());
			sample.put(supply.getName(), sparkView);
		}
		return sample;
	}
}
