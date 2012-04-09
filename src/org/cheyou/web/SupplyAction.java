package org.cheyou.web;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.util.ServletContextAware;
import org.cheyou.dao.SupplyService;
import org.cheyou.dao.model.Supply;
import org.cheyou.util.ContextUtil;
import org.cheyou.util.FileTool;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SupplyAction extends ActionSupport implements ServletContextAware {
	
	private int id;
	private String supplyName;
	private SupplyService supplyService = ContextUtil.getBean(SupplyService.class, "supplyService");
	
	private File img;
	private String imgContentType;
	private String imgFileName;
	private ServletContext context;
	
	
	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgContentType() {
		return imgContentType;
	}

	public void setImgContentType(String imgContentType) {
		this.imgContentType = imgContentType;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public String add() throws Exception {
		String targetDir = context.getRealPath("img/upload/supplies");
		System.out.println(targetDir);
		String targetFileName = FileTool.generateFileName(imgFileName);
		File targetFile = new File(targetDir, targetFileName);
		FileUtils.copyFile(img, targetFile);
		
		Supply supply = new Supply();
		supply.setName(supplyName);
		supply.setImg("img/upload/supplies/" + targetFileName);
		supplyService.addSupply(supply);
		
		List<Supply> supplies = supplyService.getAllSupplies();
		ActionContext.getContext().put("supplies", supplies);
		
		return SUCCESS;
	}
	
	public String init() throws Exception {
		List<Supply> supplies = supplyService.getAllSupplies();
		ActionContext.getContext().put("supplies", supplies);
		
		return SUCCESS;
	}
	
	public String delete() throws Exception {
		supplyService.delete(id);
		
		List<Supply> supplies = supplyService.getAllSupplies();
		ActionContext.getContext().put("supplies", supplies);
		
		return SUCCESS;
	}
	
	public String view() throws Exception {
		Supply supply = supplyService.fetch(id);
		supplyName = supply.getName();
		
		return INPUT;
	}
	
	public String save() throws Exception {
		Supply supply = supplyService.fetch(id);
		supply.setName(supplyName);
		supplyService.updateSupply(supply);
		
		List<Supply> supplies = supplyService.getAllSupplies();
		ActionContext.getContext().put("supplies", supplies);
		
		return SUCCESS;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
		
	}
}
