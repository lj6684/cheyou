package com.chezhu.dao;

import org.nutz.service.EntityService;

import com.chezhu.dao.model.SparkView;
import com.chezhu.util.ContextUtil;

public class SparkViewService extends EntityService<SparkView> {
	
	private StyleService styleService = ContextUtil.getBean(StyleService.class, "styleService");
	private SparkService sparkService = ContextUtil.getBean(SparkService.class, "sparkService");
	
	

}
