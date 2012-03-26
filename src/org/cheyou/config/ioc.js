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
	
	sparkPlugViewService : {
		type : "org.cheyou.dao.SparkPlugViewService",
		fields : {
			dao : {
				refer : "dao"
			}
		}
	},
	
	sparkPlugService : {
		type : "org.cheyou.dao.SparkPlugService",
		fields : {
			dao : {
				refer : "dao"
			}
		}
	}
};
