package com.chezhu.web;

import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;


import com.chezhu.dao.BrandService;
import com.chezhu.dao.SparkDescpService;
import com.chezhu.dao.SparkService;
import com.chezhu.dao.SparkViewService;
import com.chezhu.dao.SparkService;
import com.chezhu.dao.SparkViewService;
import com.chezhu.dao.StyleService;
import com.chezhu.dao.SupplyService;
import com.chezhu.dao.model.Brand;
import com.chezhu.dao.model.Spark;
import com.chezhu.dao.model.SparkDescp;
import com.chezhu.dao.model.SparkView;
import com.chezhu.dao.model.Spark;
import com.chezhu.dao.model.SparkView;
import com.chezhu.dao.model.Style;
import com.chezhu.dao.model.Supply;
import com.chezhu.util.ContextUtil;
import com.chezhu.util.PageMaker;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SparkAction extends ActionSupport implements ServletRequestAware, ServletContextAware {
	
	private int brandId;
	private String brandName;
	private int supplyId;
	private String supplyName;
	private int styleId;
	private String styleFullName;
	private int sparkId;
	
	// 三滤描述相关
	private boolean hasType0;
	private boolean hasType1;
	private boolean hasType2;
	private boolean hasType3;
	
	private int descpId0;
	private int descpId1;
	private int descpId2;
	private int descpId3;
	
	private int descpId;
	private int type;
	private String descp = "";
	
	private String act;
	private Spark spark = new Spark();
	
	private BrandService brandService = ContextUtil.getBean(BrandService.class, "brandService");
	private SparkService sparkService = ContextUtil.getBean(SparkService.class, "sparkService");
	private SupplyService supplyService = ContextUtil.getBean(SupplyService.class, "supplyService");
	private StyleService styleService = ContextUtil.getBean(StyleService.class, "styleService");
	private SparkViewService sparkViewService = ContextUtil.getBean(SparkViewService.class, "sparkViewService");
	private SparkDescpService sparkDescpService = ContextUtil.getBean(SparkDescpService.class, "sparkDescpService");
	
	private HttpServletRequest servletRequest;
	private ServletContext servletContext;

	
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getDescpId() {
		return descpId;
	}

	public void setDescpId(int descpId) {
		this.descpId = descpId;
	}

	public int getDescpId0() {
		return descpId0;
	}

	public void setDescpId0(int descpId0) {
		this.descpId0 = descpId0;
	}

	public int getDescpId1() {
		return descpId1;
	}

	public void setDescpId1(int descpId1) {
		this.descpId1 = descpId1;
	}

	public int getDescpId2() {
		return descpId2;
	}

	public void setDescpId2(int descpId2) {
		this.descpId2 = descpId2;
	}

	public int getDescpId3() {
		return descpId3;
	}

	public void setDescpId3(int descpId3) {
		this.descpId3 = descpId3;
	}

	public boolean isHasType0() {
		return hasType0;
	}

	public void setHasType0(boolean hasType0) {
		this.hasType0 = hasType0;
	}

	public boolean isHasType1() {
		return hasType1;
	}

	public void setHasType1(boolean hasType1) {
		this.hasType1 = hasType1;
	}

	public boolean isHasType2() {
		return hasType2;
	}

	public void setHasType2(boolean hasType2) {
		this.hasType2 = hasType2;
	}

	public boolean isHasType3() {
		return hasType3;
	}

	public void setHasType3(boolean hasType3) {
		this.hasType3 = hasType3;
	}

	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	public String getStyleFullName() {
		return styleFullName;
	}

	public void setStyleFullName(String styleFullName) {
		this.styleFullName = styleFullName;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public int getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(int supplyId) {
		this.supplyId = supplyId;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public int getStyleId() {
		return styleId;
	}

	public void setStyleId(int styleId) {
		this.styleId = styleId;
	}

	public int getSparkId() {
		return sparkId;
	}

	public void setSparkId(int sparkId) {
		this.sparkId = sparkId;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public Spark getSpark() {
		return spark;
	}

	public void setSpark(Spark spark) {
		this.spark = spark;
	}

	public StyleService getStyleService() {
		return styleService;
	}

	public void setStyleService(StyleService styleService) {
		this.styleService = styleService;
	}

	public String init() {
		Brand brand = brandService.fetch(brandId);
		brandName = brand.getName();
		Style style = styleService.fetch(styleId);
		styleFullName = style.getFullName();
		Supply supply = supplyService.fetch(supplyId);
		supplyName = supply.getName();
		
		if(act.equals("update")) {
			spark = sparkService.fetch(sparkId);
			
			// 检查是否已经存在描述信息
			SparkDescp sparkDescp = sparkDescpService.fetch(supplyId, spark.getStandard());
			if(sparkDescp != null) {
				hasType0 = true;
				descpId0 = sparkDescp.getDescpId();
			}
			sparkDescp = sparkDescpService.fetch(supplyId, spark.getPlatinum());
			if(sparkDescp != null) {
				hasType1 = true;
				descpId1 = sparkDescp.getDescpId();
			}
			sparkDescp = sparkDescpService.fetch(supplyId, spark.getIridium());
			if(sparkDescp != null) {
				hasType2 = true;
				descpId2 = sparkDescp.getDescpId();
			}
			sparkDescp = sparkDescpService.fetch(supplyId, spark.getAlloy());
			if(sparkDescp != null) {
				hasType3 = true;
				descpId3 = sparkDescp.getDescpId();
			}
		}
		
		return INPUT;
	}
	
	public String add() throws Exception {
		if(act.equals("add")) {
			sparkService.addSpark(spark);
		} else if(act.equals("update")) {
			sparkService.updateSpark(spark);
		}
		initData();
		
		return SUCCESS;
	}
	
	public String delete() throws Exception {
		sparkService.delete(sparkId);
		initData();
		return SUCCESS;
	}
	
	/**
	 * 查看详细页面
	 * @return
	 * @throws Exception
	 */
	public String showDescp() throws Exception {
		// type
		// { 0:"air", 1:"machineOil", 2:"fuelOil", 3:"airConditionStd", 4:"airConditionCarbon" }
		if(act.equals("add")) {
			descp = "";
		} else {
			SparkDescp res = sparkDescpService.fetch(descpId);
			descp = res.getSparkDescp();
		}
		
		return "showdescp";
	}
	
	/**
	 * 保存详细页面
	 * @return
	 */
	public String saveDescp() {
		if(act.equals("add")) {
			SparkDescp sparkDescp = new SparkDescp();
			sparkDescp.setSupplyId(supplyId);
			String sparkCode = getSparkCode(sparkId, type);
			sparkDescp.setSparkCode(sparkCode);
			sparkDescp.setSparkDescp(descp);
			
			sparkDescpService.insert(sparkDescp);
		} else {
			SparkDescp res = sparkDescpService.fetch(descpId);
			res.setSparkDescp(descp);
			
			sparkDescpService.update(res);
		}
		
		ActionContext.getContext().put("success", true);
		
		return "showdescp";
	}
	
	/**
	 * 删除详细描述
	 * @return
	 */
	public String deleteDescp() {
		sparkDescpService.delete(descpId);
		return init();
	}
	
	/**
	 * 产生静态页面
	 * @return
	 */
	public String genPage() {
		SparkView sparkView = sparkViewService.fetch(sparkId);
		
		String classPath = "";
		try {
			classPath = Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		//System.out.println(classPath);
		String templatePath = classPath + "ftl";
		String templateName = "huohuasai.html";
		String webAppPath = servletContext.getRealPath("/");
		//System.out.println(webAppPath);
		String outputPath = webAppPath + "huohuasai/";
		
		// templatePath =
		// outputPath = 
		PageMaker pageMaker = new PageMaker(templatePath, templateName, outputPath);
		try {
			pageMaker.makeSparkPage(sparkView);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		ActionContext.getContext().put("genPageSuccess", true);
		
		return init();
	}
	
	private void initData() {
		ActionContext ctx = ActionContext.getContext();
		List<Brand> brands = brandService.getAllBrands();
		ctx.put("brands", brands);
			
		List<Supply> supplies = supplyService.getAllSupplies();
		ctx.put("supplies", supplies);
		
		List<SparkView> styleSparks = sparkViewService.getStyleSparks(brandId, supplyId);
		ctx.put("sparks", styleSparks);
	}
	
	private String getSparkCode(int sparkId, int type) {
		Spark spark = sparkService.fetch(sparkId);
		if(type == 0) {
			return spark.getStandard();
		} else if(type == 1) {
			return spark.getPlatinum();
		} else if(type == 2) {
			return spark.getIridium();
		} else if(type == 3) {
			return spark.getAlloy();
		} else {
			return null;
		}
	}

	public void setServletRequest(HttpServletRequest arg0) {
		servletRequest = arg0;
		
	}

	public void setServletContext(ServletContext arg0) {
		servletContext = arg0;
		
	}
}
