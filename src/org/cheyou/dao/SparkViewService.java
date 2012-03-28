package org.cheyou.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cheyou.dao.model.Spark;
import org.cheyou.dao.model.SparkView;
import org.cheyou.dao.model.Style;
import org.cheyou.util.ContextUtil;
import org.nutz.service.IdEntityService;

public class SparkViewService extends IdEntityService<SparkView> {
	
	private StyleService styleService = ContextUtil.getBean(StyleService.class, "styleService");
	private SparkService sparkService = ContextUtil.getBean(SparkService.class, "sparkService");
	
	public List<SparkView> getStyleSparks(int brandId, int supplyId) {
		List<Style> styles = styleService.getStyles(brandId);
		
		Map<Integer, SparkView> resMap = new HashMap<Integer, SparkView>();
		for(Style style : styles) {
			SparkView spView = new SparkView();
			spView.setStyleId(style.getId());
			spView.setStyleName(style.getName());
			spView.setStyleImg(style.getImg());
			spView.setBrandId(style.getBid());
			spView.setSupplyId(supplyId);
			resMap.put(style.getId(), spView);
		}
		
		List<Spark> sparks = sparkService.getSparksByBrand(brandId, supplyId);
		for(Spark sp : sparks) {
			int styleId = sp.getStyleId();
			SparkView sparkView = resMap.get(styleId);
			if(sparkView == null) {
				continue;
			}
			sparkView.setSparkId(sp.getId());
			sparkView.setOutputVolumn(sp.getOutputVolumn());
			sparkView.setMotor(sp.getMotor());
			sparkView.setYear(sp.getYear());
			sparkView.setRemark(sp.getRemark());
			sparkView.setXunSn(sp.getXunSn());
			sparkView.setXunType(sp.getXunType());
			sparkView.setChaoSn(sp.getChaoSn());
			sparkView.setChaoType(sp.getChaoType());
			sparkView.setRuiSn(sp.getRuiSn());
			sparkView.setRuiType(sp.getRuiType());
			
			resMap.put(styleId, sparkView);
		}
		
		List<SparkView> resList = new ArrayList(resMap.values());
		return resList;
	}

}
