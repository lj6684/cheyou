package com.chezhu.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

public class MyStrutsFilter extends StrutsPrepareAndExecuteFilter {
	
	private List<String> excludes = new ArrayList<String>();
	
	 
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		super.init(config);
		
		// UEditor JSP pages
		excludes.add("fileUp.jsp");
		excludes.add("getContent.jsp");
		excludes.add("getMovie.jsp");
		excludes.add("getRemoteImage.jsp");
		excludes.add("imageManager.jsp");
		excludes.add("imageUp.jsp");
	}



	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		// 不过滤的url
		String url = request.getRequestURI();
		if (url.endsWith(".jsp")) {
			// 请求JSP页面
			String jspUrl = url.substring(url.lastIndexOf("/") + 1, url.length());
			if(excludes.contains(jspUrl)) {
				// 放过UEditor自带JSP处理页面
				chain.doFilter(req, res);
			} else {
				// 保持原始Struts处理方式
				super.doFilter(req, res, chain);
			}
		} else {
			super.doFilter(req, res, chain);
		}
	}
}
