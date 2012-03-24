package org.cheyou.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cheyou.dao.model.SparkPlug;
import org.cheyou.dao.model.SparkPlugView;
import org.cheyou.dao.model.Style;
import org.cheyou.util.ContextUtil;
import org.nutz.service.IdEntityService;

public class SparkPlugViewService extends IdEntityService<SparkPlugView> {
	
	private StyleService styleService = ContextUtil.getBean(StyleService.class, "styleService");
	private SparkPlugService sparkPlugService = ContextUtil.getBean(SparkPlugService.class, "sparkPlugService");
	
	public List<SparkPlugView> getStyleSparkPlugs(int brandId, int supplyId) {
		List<Style> styles = styleService.getStyles(brandId);
		
		Map<Integer, SparkPlugView> resMap = new HashMap<Integer, SparkPlugView>();
		for(Style style : styles) {
			SparkPlugView spView = new SparkPlugView();
			spView.setStyleId(style.getId());
			spView.setStyleName(style.getName());
			spView.setStyleImg(style.getImg());
			spView.setBrandId(style.getBid());
			spView.setSupplyId(supplyId);
			resMap.put(style.getId(), spView);
		}
		
		List<SparkPlug> sparkPlugs = sparkPlugService.getSparkPlugByBrand(brandId, supplyId);
		for(SparkPlug sp : sparkPlugs) {
			int styleId = sp.getStyleId();
			SparkPlugView spView = resMap.get(styleId);
			if(spView == null) {
				continue;
			}
			spView.setSpId(sp.getId());
			spView.setOutputVolumn(sp.getOutputVolumn());
			spView.setMotor(sp.getMotor());
			spView.setYear(sp.getYear());
			spView.setRemark(sp.getRemark());
			spView.setXunSn(sp.getXunSn());
			spView.setXunType(sp.getXunType());
			spView.setChaoSn(sp.getChaoSn());
			spView.setChaoType(sp.getChaoType());
			spView.setRuiSn(sp.getRuiSn());
			spView.setRuiType(sp.getRuiType());
			
			resMap.put(styleId, spView);
		}
		
		List<SparkPlugView> resList = new ArrayList(resMap.values());
		return resList;
	}

}
