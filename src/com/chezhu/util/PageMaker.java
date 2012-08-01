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
import com.chezhu.dao.SparkDescpService;
import com.chezhu.dao.SparkViewService;
import com.chezhu.dao.SupplyService;
import com.chezhu.dao.model.FilterDescp;
import com.chezhu.dao.model.FilterView;
import com.chezhu.dao.model.SparkDescp;
import com.chezhu.dao.model.SparkView;
import com.chezhu.dao.model.Supply;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * 静态页面生成工具类
 * @author Administrator
 *
 */
public class PageMaker {

	private Template template;
	private FilterViewService filterViewService;
	private FilterDescpService filterDescpService;
	private SupplyService supplyService;

	private SparkViewService sparkViewService;
	private SparkDescpService sparkDescpService;

	private String templatePath;
	private String outputPath;

	/**
	 * Constructor
	 * @param templatePath 模板文件路径
	 * @param templateName 模板文件名称
	 * @param outputPath 输出文件路径
	 */
	public PageMaker(String templatePath, String templateName, String outputPath) {
		filterViewService = ContextUtil.getBean(FilterViewService.class, "filterViewService");
		filterDescpService = ContextUtil.getBean(FilterDescpService.class, "filterDescpService");
		supplyService = ContextUtil.getBean(SupplyService.class, "supplyService");
		sparkViewService = ContextUtil.getBean(SparkViewService.class, "sparkViewService");
		sparkDescpService = ContextUtil.getBean(SparkDescpService.class, "sparkDescpService");

		this.templatePath = templatePath;
		this.outputPath = outputPath;

		try {
			Configuration config = new Configuration();
			config.setDirectoryForTemplateLoading(new File(this.templatePath));
			config.setObjectWrapper(new DefaultObjectWrapper());
			// 取消数字每3位自动格式化
			config.setNumberFormat("#");

			template = config.getTemplate(templateName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 按fiterId生成一个静态页面
	 * @param filterId 三滤器编号
	 */
	public void makeFilterPageById(long filterId) {
		try {
			FilterView filterView = filterViewService.fetch(filterId);
			System.out.println(filterView.getFilterId());
			makeFilterPage(filterView);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("finished.");
	}

	/**
	 * 生成全部三滤静态页面
	 */
	public void makeFilterPageAll() {
		try {
			List<FilterView> all = filterViewService.getAllFilterViews();

			for (FilterView filterView : all) {
				System.out.println(filterView.getFilterId());
				makeFilterPage(filterView);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("finished.");
	}
	
	/**
	 * 按sparkId生成一个静态页面
	 * @param sparkId 火花塞ID
	 */
	public void makeSparkPageById(long sparkId) {
		try {
			SparkView sparkView = sparkViewService.fetch(sparkId);
			System.out.println(sparkView.getSparkId());
			makeSparkPage(sparkView);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("finished.");
	}

	/**
	 * 生成全部火花塞静态页面
	 */
	public void makeSparkPageAll() {
		try {
			List<SparkView> all = sparkViewService.getAllSparkViews();

			for (SparkView sparkView : all) {
				System.out.println(sparkView.getSparkId());
				makeSparkPage(sparkView);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("finished.");
	}

	/**
	 * 生成三滤静态页面
	 * @param filterView 三滤详细信息
	 * @throws Exception
	 */
	public void makeFilterPage(FilterView filterView) throws Exception {
		// 可以考虑使用工具自动处理
		Map data = new HashMap();

		boolean showOriginal = true;
		if (filterView.getSupplyId() == 2) {
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

		// 供应商主页
		int supplyId = filterView.getSupplyId();
		String supplyUrl = "";
		switch (supplyId) {
		case 1:
			// 博世
			supplyUrl = "../../bosch/";
			break;
		case 2:
			// 原厂
			supplyUrl = "#";
			break;
		case 3:
			// 索菲玛
			supplyUrl = "../../sofima/";
			break;
		case 4:
			// 马勒
			supplyUrl = "../../mahle/";
			break;
		case 5:
			// 曼牌
			supplyUrl = "../../mann/";
			break;
		default:
			// 目前未知
			supplyUrl = "#";
		}
		data.put("supplyUrl", supplyUrl);

		// 详细描述信息
		String airDescp = "";
		String machineOilDescp = "";
		String fuelOilDescp = "";
		String airCdStdDescp = "";
		String airCdCarbonDescp = "";
		FilterDescp descp = filterDescpService.fetch(filterView.getSupplyId(), filterView.getAir());
		if (descp != null) {
			airDescp = descp.getFilterDescp();
		}
		descp = filterDescpService.fetch(filterView.getSupplyId(), filterView.getMachineOil());
		if (descp != null) {
			machineOilDescp = descp.getFilterDescp();
		}
		descp = filterDescpService.fetch(filterView.getSupplyId(), filterView.getFuelOil());
		if (descp != null) {
			fuelOilDescp = descp.getFilterDescp();
		}
		descp = filterDescpService.fetch(filterView.getSupplyId(), filterView.getAirConditionStd());
		if (descp != null) {
			airCdStdDescp = descp.getFilterDescp();
		}
		descp = filterDescpService.fetch(filterView.getSupplyId(), filterView.getAirConditionCarbon());
		if (descp != null) {
			airCdCarbonDescp = descp.getFilterDescp();
		}
		data.put("airDescp", airDescp);
		data.put("machineOilDescp", machineOilDescp);
		data.put("fuelOilDescp", fuelOilDescp);
		data.put("airCdStdDescp", airCdStdDescp);
		data.put("airCdCarbonDescp", airCdCarbonDescp);

		String supplyDescp = "";
		Supply supply = supplyService.fetch(filterView.getSupplyId());
		if (supply.getDescp() != null) {
			supplyDescp = supply.getDescp();
		}
		data.put("supplyDescp", supplyDescp);

		// 查本车型对应原厂数据(供对比查看)
		FilterView orgFilterView = filterViewService.queryOriginalFilterViewByStyle(filterView.getStyleId());
		if (orgFilterView == null) {
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

		List<FilterView> otherSupplies = filterViewService.queryFilterViewByStyle(filterView.getStyleId(), filterView.getSupplyId());
		List<FilterView> otherStyles = filterViewService.queryFilterViewByBrandSP(filterView.getBrandId(), filterView.getSupplyId());
		data.put("otherSupplies", otherSupplies);
		data.put("otherStyles", otherStyles);

		File file = new File(this.outputPath + filterView.getFilterId() + "/");
		if (!file.exists()) {
			file.mkdirs();
		}

		Writer writer = new OutputStreamWriter(new FileOutputStream(this.outputPath + filterView.getFilterId() + "/index.html"));
		template.process(data, writer);
		writer.flush();
		writer.close();
	}

	/**
	 * 生成火花塞静态页面
	 * @param sparkView 火花塞详细信息
	 * @throws Exception
	 */
	public void makeSparkPage(SparkView sparkView) throws Exception {
		// 可以考虑使用工具自动处理
		Map data = new HashMap();

		// 目前火花塞没有可对比原厂数据，暂时先不加
		boolean showOriginal = false;
		/*
		 * if(sparkView.getSupplyId() == 2) { // 仅原厂数据 showOriginal = false; }
		 */

		data.put("showOriginal", showOriginal);
		data.put("brandImg", formatImg(sparkView.getBrandImg()));
		data.put("styleFullName", sparkView.getStyleFullName());
		data.put("styleMottor", formatStr(sparkView.getStyleMotor()));
		data.put("supplyName", sparkView.getSupplyName());
		data.put("supplyImg", formatImg(sparkView.getSupplyImg()));
		data.put("brandName", sparkView.getBrandName());

		data.put("standard", formatStr(sparkView.getStandard()));
		data.put("platinum", formatStr(sparkView.getPlatinum()));
		data.put("iridium", formatStr(sparkView.getIridium()));
		data.put("alloy", formatStr(sparkView.getAlloy()));

		// 供应商主页
		int supplyId = sparkView.getSupplyId();
		String supplyUrl = "";
		switch (supplyId) {
		case 1:
			// 博世
			supplyUrl = "../../bosch/";
			break;
		case 7:
			// NGK
			supplyUrl = "../../ngk/";
			break;
		case 8:
			// 电装
			supplyUrl = "../../denso/";
			break;
		default:
			// 目前未知
			supplyUrl = "#";
		}
		data.put("supplyUrl", supplyUrl);

		// 详细描述信息
		String standardDescp = "";
		String platinumDescp = "";
		String iridiumDescp = "";
		String alloyDescp = "";
		SparkDescp descp = sparkDescpService.fetch(sparkView.getSupplyId(), sparkView.getStandard());
		if (descp != null) {
			standardDescp = descp.getSparkDescp();
		}
		descp = sparkDescpService.fetch(sparkView.getSupplyId(), sparkView.getPlatinum());
		if (descp != null) {
			platinumDescp = descp.getSparkDescp();
		}
		descp = sparkDescpService.fetch(sparkView.getSupplyId(), sparkView.getIridium());
		if (descp != null) {
			iridiumDescp = descp.getSparkDescp();
		}
		descp = sparkDescpService.fetch(sparkView.getSupplyId(), sparkView.getAlloy());
		if (descp != null) {
			alloyDescp = descp.getSparkDescp();
		}

		data.put("standardDescp", standardDescp);
		data.put("platinumDescp", platinumDescp);
		data.put("iridiumDescp", iridiumDescp);
		data.put("alloyDescp", alloyDescp);

		String supplyDescp = "";
		Supply supply = supplyService.fetch(sparkView.getSupplyId());
		if (supply.getDescp() != null) {
			supplyDescp = supply.getDescp();
		}
		data.put("supplyDescp", supplyDescp);

		// 查本车型对应原厂数据(供对比查看)
		/*
		 * SparkView orgSparkView =
		 * sparkViewService.queryOriginalSparkViewByStyle
		 * (sparkView.getStyleId()); if(orgSparkView == null) { orgSparkView =
		 * new SparkView(); orgSparkView.setSupplyName("原厂号");
		 * orgSparkView.setSupplyImg("images/supply/genuine.gif");
		 * orgSparkView.setStandard(""); orgSparkView.setPlatinum("");
		 * orgSparkView.setIridium(""); orgSparkView.setAlloy(""); }
		 */
		// 目前火花塞没有可对比原厂数据，暂时先不加
		SparkView orgSparkView = new SparkView();
		orgSparkView.setSupplyName("原厂号");
		orgSparkView.setSupplyImg("images/supply/genuine.gif");
		orgSparkView.setStandard("");
		orgSparkView.setPlatinum("");
		orgSparkView.setIridium("");
		orgSparkView.setAlloy("");

		data.put("orgSupplyName", orgSparkView.getSupplyName());
		data.put("orgSupplyImg", orgSparkView.getSupplyImg());

		data.put("orgStandard", formatConent(orgSparkView.getStandard()));
		data.put("orgPlatinum", formatConent(orgSparkView.getPlatinum()));
		data.put("orgIridium", formatConent(orgSparkView.getIridium()));
		data.put("orgAlloy", formatConent(orgSparkView.getAlloy()));

		List<SparkView> otherSupplies = sparkViewService.querySparkViewByStyle(sparkView.getStyleId(), sparkView.getSupplyId());
		List<SparkView> otherStyles = sparkViewService.querySparkViewByBrandSP(sparkView.getBrandId(), sparkView.getSupplyId());
		data.put("otherSupplies", otherSupplies);
		data.put("otherStyles", otherStyles);

		File file = new File(this.outputPath + sparkView.getSparkId() + "/");
		if (!file.exists()) {
			file.mkdirs();
		}

		Writer writer = new OutputStreamWriter(new FileOutputStream(this.outputPath + sparkView.getSparkId() + "/index.html"));
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
	 * 主要用于处理原厂三滤数据,将空格替换为&nbsp; 将/替换为<br/>
	 * @param str 待格式化数据
	 * @return
	 */
	public String formatConent(String str) {
		if (str == null || str.trim().equals("")) {
			return "&nbsp;";
		} else {
			if (str.indexOf("/") > 0) {
				String res = str.replaceAll("/", "<br/>");
				return res;
			} else {
				return str;
			}
		}
	}

	/**
	 * 格式化字符串,将空格替换为&nbsp;
	 * @param str 待格式化字符串
	 * @return 格式化结果
	 */
	public String formatStr(String str) {
		if (str == null || str.trim().equals("")) {
			return "&nbsp;";
		} else {
			return str;
		}
	}

	/**
	 * 格式化图片字符串
	 * @param str 待格式化字符串
	 * @return 格式化结果
	 */
	public String formatImg(String str) {
		if (str == null || str.trim().equals("")) {
			return "";
		} else {
			return str;
		}
	}

	public static void main(String[] args) {
		// 单机运行时需开启
		ContextUtil.initIocContext();

		PageMaker maker = new PageMaker("./ftl", "sanlv.html", "./WebContent/sanlv/");
		maker.makeFilterPageAll();
		//maker.makeFilterPageById(72);
		// maker.makeSiteMapPage();
	}

}
