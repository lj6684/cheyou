package org.cheyou.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.cheyou.dao.FilterViewService;
import org.cheyou.dao.StyleViewService;
import org.cheyou.dao.model.FilterView;
import org.cheyou.dao.model.StyleView;
import org.cheyou.util.ContextUtil;
import org.cheyou.util.StyleNameCache;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class QueryAction extends ActionSupport implements ServletResponseAware {
	
	private HttpServletResponse response;
	
	private String queryStr;
	private FilterViewService filterViewService = ContextUtil.getBean(FilterViewService.class, "filterViewService");
	private StyleViewService styleViewService = ContextUtil.getBean(StyleViewService.class, "styleViewService");
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
		ActionContext context = ActionContext.getContext();
		context.put("styles", styles);
		context.put("filters", filters);
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
