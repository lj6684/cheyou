package com.chezhu.dao;

import org.nutz.service.IdEntityService;

import com.chezhu.dao.model.UnknownRecord;

public class UnknownRecordService extends IdEntityService<UnknownRecord> {
	
	public UnknownRecord addUnknownRecord(UnknownRecord unknownRecord) {
		UnknownRecord res = this.dao().insert(unknownRecord);
		return res;
	}

}
