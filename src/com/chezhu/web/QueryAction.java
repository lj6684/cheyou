package com.chezhu.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.chezhu.dao.FilterViewService;
import com.chezhu.dao.StyleViewService;
import com.chezhu.dao.SupplyService;
import com.chezhu.dao.model.FilterView;
import com.chezhu.dao.model.StyleView;
import com.chezhu.dao.model.Supply;
import com.chezhu.util.ContextUtil;
import com.chezhu.util.StyleNameCache;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class QueryAction extends ActionSupport implements ServletResponseAware {
	
	private HttpServletResponse response;
	
	private String queryStr;
	private FilterViewService filterViewService = ContextUtil.getBean(FilterViewService.class, "filterViewService");
	private StyleViewService styleViewService = ContextUtil.getBean(StyleViewService.class, "styleViewService");
	private SupplyService supplyService = ContextUtil.getBean(SupplyService.class, "supplyService");
	private String filterId;
	private List<String> supplyItem;
	
	
	public List<String> getSupplyItem() {
		return supplyItem;
	}

	public void setSupplyItem(List<String> supplyItem) {
		this.supplyItem = supplyItem;
	}

	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}
	
	// 使用Ajax方式查询所有车型列表
	public String init() throws Exception {
		String data = StyleNameCache.getInstance().getAllStyleNamesJson();
		//System.out.println(data);
		response.setContentType("text/json; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println(data);
		response.getWriter().flush();
		response.getWriter().close();
		return SUCCESS;
	}

	// 查询三滤数据
	public String query() throws Exception {
		List<StyleView> styles = styleViewService.query(queryStr);
		Map<String, Map<String, FilterView>> filters = filterViewService.queryFilters(queryStr, supplyItem);
		// 为前台页面显示结果排序用，后期可以考虑优化为内存提取数据
		List<Supply> supplies = supplyService.getSuppliesById(supplyItem);
		
		ActionContext context = ActionContext.getContext();
		context.put("styles", styles);
		context.put("filters", filters);
		context.put("orderSupplies", supplies);
		return SUCCESS;
	}
	
	// 支持
	public String support() throws Exception {
		
		return SUCCESS;
	}
	
	// 建议
	public String suggest() throws Exception {
		
		return SUCCESS;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	
}
