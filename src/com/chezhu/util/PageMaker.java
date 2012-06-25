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
			
			Template template = config.getTemplate("sanlv.html");
			
			for(FilterView filterView : all) {
				// 可以考虑使用工具自动处理
				Map data = new HashMap();
				if(filterView.getBrandImg() != null) {
					data.put("brandImg", filterView.getBrandImg());
				} else {
					data.put("brandImg", "");
				}
				data.put("styleFullName", filterView.getStyleFullName());
				data.put("styleMottor", filterView.getStyleMotor());
				data.put("supplyName", filterView.getSupplyName());
				data.put("supplyImg", filterView.getSupplyImg());
				data.put("brandName", filterView.getBrandName());
				data.put("machineOil", formatConent(filterView.getMachineOil()));
				data.put("fuelOil", formatConent(filterView.getFuelOil()));
				data.put("air", formatConent(filterView.getAir()));
				data.put("airConditionStd", formatConent(filterView.getAirConditionStd()));
				data.put("airConditionCarbon", formatConent(filterView.getAirConditionCarbon()));
				
				List<FilterView> otherSupplies = filterViewService.queryFilterViewByStyle(filterView.getStyleId());
				List<FilterView> otherStyles = filterViewService.queryFilterViewByBrandSP(filterView.getBrandId(), filterView.getSupplyId());
				data.put("otherSupplies", otherSupplies);
				data.put("otherStyles", otherStyles);
				
				Writer writer = new OutputStreamWriter(new FileOutputStream("./WebContent/detail/sanlv_" + filterView.getFilterId() + ".html"));
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
	
	/**
	 * 主要用于处理原厂三滤数据，将/替换为<br/>
	 * @param str
	 * @return
	 */
	public String formatConent(String str) {
		if(str != null && !str.trim().equals("") && str.indexOf("/") > 0) {
			String res = str.replaceAll("/", "<br/>");
			return res;
		} else {
			return str;
		}
	}
	
	public static void main(String[] args) {
		PageMaker maker = new PageMaker();
		maker.makeFilterPage();
	}

}
