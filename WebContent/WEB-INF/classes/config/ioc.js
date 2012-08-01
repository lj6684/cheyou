var ioc = {
	supplyService : {
		type : "com.chezhu.dao.SupplyService",
		fields : {
			dao : {
				refer : "dao"
			}
		}
	},
	
	brandService : {
		type : "com.chezhu.dao.BrandService",
		fields : {
			dao : {
				refer : "dao"
			}
		}
	},
	
	styleService : {
		type : "com.chezhu.dao.StyleService",
		fields : {
			dao : {
				refer : "dao"
			}
		}
	},
	
	filterService : {
		type : "com.chezhu.dao.FilterService",
		fields : {
			dao : {
				refer : "dao"
			}
		}
	},
	
	filterViewService : {
		type : "com.chezhu.dao.FilterViewService",
		fields : {
			dao : {
				refer : "dao"
			}
		}
	},
	
	filterDescpService : {
		type : "com.chezhu.dao.FilterDescpService",
		fields : {
			dao : {
				refer : "dao"
			}
		}
	},
	
	sparkService : {
		type : "com.chezhu.dao.SparkService",
		fields : {
			dao : {
				refer : "dao"
			}
		}
	},
	
	sparkViewService : {
		type : "com.chezhu.dao.SparkViewService",
		fields : {
			dao : {
				refer : "dao"
			}
		}
	},
	
	sparkDescpService : {
		type : "com.chezhu.dao.SparkDescpService",
		fields : {
			dao : {
				refer : "dao"
			}
		}
	},
	
	styleViewService : {
		type : "com.chezhu.dao.StyleViewService",
		fields : {
			dao : {
				refer : "dao"
			}
		}
	},
	
	unknownRecordService : {
		type : "com.chezhu.dao.UnknownRecordService",
		fields : {
			dao : {
				refer : "dao"
			}
		}
	}
};
