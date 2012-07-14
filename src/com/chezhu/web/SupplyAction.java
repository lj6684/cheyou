package com.chezhu.web;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.util.ServletContextAware;

import com.chezhu.dao.SupplyService;
import com.chezhu.dao.model.Brand;
import com.chezhu.dao.model.Supply;
import com.chezhu.util.ContextUtil;
import com.chezhu.util.FileTool;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SupplyAction extends ActionSupport implements ServletContextAware {
	
	private int id;
	private String supplyName;
	private String supplyImg;
	private int orderIndex;
	private String updateImg = "0";
	private SupplyService supplyService = ContextUtil.getBean(SupplyService.class, "supplyService");
	
	private File img;
	private String imgContentType;
	private String imgFileName;
	private ServletContext context;
	
	private static final String UPLOAD_FILE_PATH = "images/supply/";
	
	private String descp;
	
	
	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	public int getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}

	public String getUpdateImg() {
		return updateImg;
	}

	public void setUpdateImg(String updateImg) {
		this.updateImg = updateImg;
	}

	public String getSupplyImg() {
		return supplyImg;
	}

	public void setSupplyImg(String supplyImg) {
		this.supplyImg = supplyImg;
	}

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
		String imgPath = saveUploadFile();
		
		Supply supply = new Supply();
		supply.setName(supplyName);
		supply.setImg(imgPath);
		supply.setOrderIndex(orderIndex);
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
		supplyImg = supply.getImg();
		orderIndex = supply.getOrderIndex();
		
		return INPUT;
	}
	
	public String save() throws Exception {
		Supply supply = supplyService.fetch(id);
		supply.setName(supplyName);
		supply.setOrderIndex(orderIndex);
		if(updateImg.equals("1")) {
			// 选择更新图片
			String imgPath = saveUploadFile();
			supply.setImg(imgPath);
		}
		
		supplyService.updateSupply(supply);
		
		List<Supply> supplies = supplyService.getAllSupplies();
		ActionContext.getContext().put("supplies", supplies);
		
		return SUCCESS;
	}
	
	public String showDescp() {
		Supply supply = supplyService.fetch(id);
		descp = supply.getDescp();
		if(descp == null || descp.trim().equals("")) {
			descp = "请填写详细信息";
		}
		
		return "showdescp";
	}
	
	public String saveDescp() {
		Supply supply = supplyService.fetch(id);
		supply.setDescp(descp);
		supplyService.updateSupply(supply);
		
		ActionContext.getContext().put("success", true);
		
		return "showdescp";
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
		
	}
	
	private String saveUploadFile() {
		try {
			String targetDir = context.getRealPath(UPLOAD_FILE_PATH);
			//System.out.println(targetDir);
			if(imgFileName == null || imgFileName.trim().equals("")) {
				return null;
			}
			String targetFileName = FileTool.generatePinyinFileName(supplyName, imgFileName);
			File targetFile = new File(targetDir, targetFileName);
			FileUtils.copyFile(img, targetFile);
			
			return UPLOAD_FILE_PATH + targetFileName;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
