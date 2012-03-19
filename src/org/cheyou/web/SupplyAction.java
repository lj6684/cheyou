package org.cheyou.web;

import java.util.List;

import org.cheyou.dao.SupplyService;
import org.cheyou.dao.model.Brand;
import org.cheyou.dao.model.Supply;
import org.cheyou.util.ContextUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SupplyAction extends ActionSupport {
	
	private int id;
	private String supplyName;
	private SupplyService supplyService = ContextUtil.getBean(SupplyService.class, "supplyService");
	
	
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
		Supply supply = new Supply();
		supply.setName(supplyName);
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
}
