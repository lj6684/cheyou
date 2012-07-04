package com.chezhu.dao;

import org.nutz.service.IdEntityService;

public class DescpEntityService<T> extends IdEntityService<T> {

	public T insert(T entity) {
		return this.dao().insert(entity);
	}
	
	public int update(T entity) {
		return this.dao().update(entity);
	}
}
