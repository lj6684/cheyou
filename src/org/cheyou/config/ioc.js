var ioc = {
	supplyService : {
		type : "org.cheyou.dao.SupplyService",
		fields : {
			dao : {
				refer : "dao"
			}
		}
	},
	
	brandService : {
		type : "org.cheyou.dao.BrandService",
		fields : {
			dao : {
				refer : "dao"
			}
		}
	},
	
	styleService : {
		type : "org.cheyou.dao.StyleService",
		fields : {
			dao : {
				refer : "dao"
			}
		}
	},
	
	filterService : {
		type : "org.cheyou.dao.FilterService",
		fields : {
			dao : {
				refer : "dao"
			}
		}
	},
	
	filterViewService : {
		type : "org.cheyou.dao.FilterViewService",
		fields : {
			dao : {
				refer : "dao"
			}
		}
	},
	
	sparkViewService : {
		type : "org.cheyou.dao.SparkViewService",
		fields : {
			dao : {
				refer : "dao"
			}
		}
	},
	
	sparkService : {
		type : "org.cheyou.dao.SparkService",
		fields : {
			dao : {
				refer : "dao"
			}
		}
	},
	
	styleViewService : {
		type : "org.cheyou.dao.StyleViewService",
		fields : {
			dao : {
				refer : "dao"
			}
		}
	}
};
