package com.chezhu.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chezhu.dao.FilterViewService;
import com.chezhu.dao.model.FilterView;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class PageMaker {
	
	public PageMaker() {
		ContextUtil.initIocContext();
	}
	
	
	public void makeFilterPage() {
		try {
			boolean once = false;
			
			FilterViewService filterViewService = ContextUtil.getBean(FilterViewService.class, "filterViewService");
			
			List<FilterView> all = filterViewService.getAllFilterViews();
	
			Configuration config = new Configuration();
			config.setDirectoryForTemplateLoading(new File("./ftl"));
			config.setObjectWrapper(new DefaultObjectWrapper());
			
			Template template = config.getTemplate("detail.html");
			
			for(FilterView filterView : all) {
				// 可以考虑使用工具自动处理
				Map data = new HashMap();
				data.put("brandImg", filterView.getBrandImg());
				data.put("styleFullName", filterView.getStyleFullName());
				data.put("styleMottor", filterView.getStyleMotor());
				data.put("supplyName", filterView.getSupplyName());
				data.put("supplyImg", filterView.getSupplyImg());
				data.put("brandName", filterView.getBrandName());
				data.put("machineOil", filterView.getMachineOil());
				data.put("fuelOil", filterView.getFuelOil());
				data.put("air", filterView.getAir());
				data.put("airConditionStd", filterView.getAirConditionStd());
				data.put("airConditionCarbon", filterView.getAirConditionCarbon());
				
				List<FilterView> otherSupplies = filterViewService.queryFilterViewByStyle(filterView.getStyleId());
				List<FilterView> otherStyles = filterViewService.queryFilterViewByBrandSP(filterView.getBrandId(), filterView.getSupplyId());
				data.put("otherSupplies", otherSupplies);
				data.put("otherStyles", otherStyles);
				
				Writer writer = new OutputStreamWriter(new FileOutputStream("./WebContent/detail/detail_" + filterView.getFilterId() + ".html"));
				template.process(data, writer);
				writer.flush();
				writer.close();
				
				if(once) {
					break;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		PageMaker maker = new PageMaker();
		maker.makeFilterPage();
	}

}
