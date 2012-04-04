package org.cheyou.web;

import java.util.List;
import java.util.Map;

import org.cheyou.dao.FilterViewService;
import org.cheyou.dao.StyleViewService;
import org.cheyou.dao.model.FilterView;
import org.cheyou.dao.model.StyleView;
import org.cheyou.util.ContextUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class QueryAction extends ActionSupport {
	
	private String queryStr;
	private FilterViewService filterViewService = ContextUtil.getBean(FilterViewService.class, "filterViewService");
	private StyleViewService styleViewService = ContextUtil.getBean(StyleViewService.class, "styleViewService");
	
	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	@Override
	public String execute() throws Exception {
		List<StyleView> styles = styleViewService.query(queryStr);
		Map<String, Map<String, FilterView>> filters = filterViewService.queryFilters(queryStr);
		ActionContext context = ActionContext.getContext();
		context.put("styles", styles);
		context.put("filters", filters);
		return SUCCESS;
	}
	
	
}
