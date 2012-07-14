package com.chezhu.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chezhu.dao.FilterDescpService;
import com.chezhu.dao.FilterViewService;
import com.chezhu.dao.model.FilterDescp;
import com.chezhu.dao.model.FilterView;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class PageMaker {
	
	private Template template;
	private FilterViewService filterViewService;
	private FilterDescpService filterDescpService;
	
	private String templatePath;
	private String outputPath;
	
	public PageMaker(String templatePath, String outputPath) {
		// 单机运行时需开启
		//ContextUtil.initIocContext();
		filterViewService = ContextUtil.getBean(FilterViewService.class, "filterViewService");
		filterDescpService = ContextUtil.getBean(FilterDescpService.class, "filterDescpService");
		
		this.templatePath = templatePath;
		this.outputPath = outputPath;
		
		try {
			Configuration config = new Configuration();
			config.setDirectoryForTemplateLoading(new File(this.templatePath));
			config.setObjectWrapper(new DefaultObjectWrapper());
			// 取消数字每3位自动格式化
			config.setNumberFormat("#");
			           
			template = config.getTemplate("sanlv.html");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void makeFilterPageAll() {
		try {
			boolean once = false;
			
			List<FilterView> all = filterViewService.getAllFilterViews();

			for(FilterView filterView : all) {
				System.out.println(filterView.getFilterId());
				makeFilterPage(filterView);	
				if(once) {
					break;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("finished.");
	}
	
	public void makeFilterPage(FilterView filterView) throws Exception {
		// 可以考虑使用工具自动处理
		Map data = new HashMap();

		boolean showOriginal = true;
		if(filterView.getSupplyId() == 2) {
			// 仅原厂数据
			showOriginal = false;
		}
		data.put("showOriginal", showOriginal);
		data.put("brandImg", formatImg(filterView.getBrandImg()));
		data.put("styleFullName", filterView.getStyleFullName());
		data.put("styleMottor", formatStr(filterView.getStyleMotor()));
		data.put("supplyName", filterView.getSupplyName());
		data.put("supplyImg", formatImg(filterView.getSupplyImg()));
		data.put("brandName", filterView.getBrandName());
		data.put("machineOil", formatStr(filterView.getMachineOil()));
		data.put("fuelOil", formatStr(filterView.getFuelOil()));
		data.put("air", formatStr(filterView.getAir()));
		data.put("airConditionStd", formatStr(filterView.getAirConditionStd()));
		data.put("airConditionCarbon", formatStr(filterView.getAirConditionCarbon()));
		
		// 详细描述信息
		String airDescp = "";
		String machineOilDescp = "";
		String fuelOilDescp = "";
		String airCdStdDescp = "";
		String airCdCarbonDescp = "";
		FilterDescp descp = filterDescpService.fetch(filterView.getSupplyId(), filterView.getAir());
		if(descp != null) {
			airDescp = descp.getFilterDescp();
		}
		descp = filterDescpService.fetch(filterView.getSupplyId(), filterView.getMachineOil());
		if(descp != null) {
			machineOilDescp = descp.getFilterDescp();
		}
		descp = filterDescpService.fetch(filterView.getSupplyId(), filterView.getFuelOil());
		if(descp != null) {
			fuelOilDescp = descp.getFilterDescp();
		}
		descp = filterDescpService.fetch(filterView.getSupplyId(), filterView.getAirConditionStd());
		if(descp != null) {
			airCdStdDescp = descp.getFilterDescp();
		}
		descp = filterDescpService.fetch(filterView.getSupplyId(), filterView.getAirConditionCarbon());
		if(descp != null) {
			airCdCarbonDescp = descp.getFilterDescp();
		}
		data.put("airDescp", airDescp);
		data.put("machineOilDescp", machineOilDescp);
		data.put("fuelOilDescp", fuelOilDescp);
		data.put("airCdStdDescp", airCdStdDescp);
		data.put("airCdCarbonDescp", airCdCarbonDescp);
		
		
		// 查本车型对应原厂数据(供对比查看)
		FilterView orgFilterView = filterViewService.queryOriginalFilterViewByStyle(filterView.getStyleId());
		if(orgFilterView == null) {
			orgFilterView = new FilterView();
			orgFilterView.setSupplyName("原厂号");
			orgFilterView.setSupplyImg("images/supply/genuine.gif");
			orgFilterView.setMachineOil("");
			orgFilterView.setFuelOil("");
			orgFilterView.setAir("");
			orgFilterView.setAirConditionStd("");
			orgFilterView.setAirConditionCarbon("");
		}
		data.put("orgSupplyName", orgFilterView.getSupplyName());
		data.put("orgSupplyImg", orgFilterView.getSupplyImg());
		data.put("orgMachineOil", formatConent(orgFilterView.getMachineOil()));
		data.put("orgFuelOil", formatConent(orgFilterView.getFuelOil()));
		data.put("orgAir", formatConent(orgFilterView.getAir()));
		data.put("orgAirConditionStd", formatConent(orgFilterView.getAirConditionStd()));
		data.put("orgAirConditionCarbon", formatConent(orgFilterView.getAirConditionCarbon()));
		
		
		List<FilterView> otherSupplies = filterViewService.queryFilterViewByStyle(filterView.getStyleId());
		List<FilterView> otherStyles = filterViewService.queryFilterViewByBrandSP(filterView.getBrandId(), filterView.getSupplyId());
		data.put("otherSupplies", otherSupplies);
		data.put("otherStyles", otherStyles);
		
		File file = new File(this.outputPath + filterView.getFilterId() + "/");
		if(!file.exists()) {
			file.mkdirs();
		}
		
		Writer writer = new OutputStreamWriter(new FileOutputStream(this.outputPath + filterView.getFilterId() + "/index.html"));
		template.process(data, writer);
		writer.flush();
		writer.close();
	}
	
	/**
	 * 产生全站数据导航页面
	 */
	public void makeSiteMapPage() {
		try {
			FilterViewService filterViewService = ContextUtil.getBean(FilterViewService.class, "filterViewService");
			
			List<FilterView> all = filterViewService.getAllFilterViews();
	
			Configuration config = new Configuration();
			config.setDirectoryForTemplateLoading(new File("./ftl"));
			config.setObjectWrapper(new DefaultObjectWrapper());
			// 取消数字每3位自动格式化
			config.setNumberFormat("#");
			           
			Template template = config.getTemplate("sitemap.html");
			Map data = new HashMap();
			data.put("filterViews", all);
			
			Writer writer = new OutputStreamWriter(new FileOutputStream("./WebContent/sitemap.html"));
			template.process(data, writer);
			writer.flush();
			writer.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("finished.");
	}
	
	/**
	 * 主要用于处理原厂三滤数据，将/替换为<br/>
	 * @param str
	 * @return
	 */
	public String formatConent(String str) {
		if(str == null || str.trim().equals("")) {
			return "&nbsp;";
		} else {
			if(str.indexOf("/") > 0) {
				String res = str.replaceAll("/", "<br/>");
				return res;
			} else {
				return str;
			}
		}
	}
	
	public String formatStr(String str) {
		if(str == null || str.trim().equals("")) {
			return "&nbsp;";
		} else {
			return str;
		}
	}
	
	public String formatImg(String str) {
		if(str == null || str.trim().equals("")) {
			return "";
		} else {
			return str;
		}
	}
	
	public static void main(String[] args) {
		PageMaker maker = new PageMaker("./ftl", "./WebContent/sanlv/");
		maker.makeFilterPageAll();
		//maker.makeSiteMapPage();
	}

}
