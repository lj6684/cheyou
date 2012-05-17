var ioc = {
	config : {
		type: 'org.nutz.ioc.impl.PropertiesProxy',
		fields: {
			paths: ['chezhu.properties'] 
		}
	},	
	
	/*
	// C3P0
    dataSource: {
        type: "com.mchange.v2.c3p0.ComboPooledDataSource",
        fields : {
			driverClass: {java:"$config.get('db.driver')"},
			jdbcUrl: {java:"$config.get('db.url')"},
            user: {java:"$config.get('db.user')"},
            password: {java:"$config.get('db.password')"},
            initialPoolSize: "5",
            minPoolSize: "1",
            maxPoolSize: "30",
            maxStatements: "50",
            maxIdleTime: "1800",
            idleConnectionTestPeriod: "1800",
            testConnectionOnCheckin: "true",
            automaticTestTable: "test",
        }
    },
	*/
	
	// proxool
	dataSource: {
        type: "org.logicalcobwebs.proxool.ProxoolDataSource",
        fields : {
			driver: {java:"$config.get('db.driver')"},
			driverUrl: {java:"$config.get('db.url')"},
            user: {java:"$config.get('db.user')"},
            password: {java:"$config.get('db.password')"},
			maximumConnectionCount: "30",
			minimumConnectionCount: "5",
			houseKeepingTestSql: "SELECT 1",
			testBeforeUse: "true"
        }
    },
	
	dao : {
		type : 'org.nutz.dao.impl.NutDao',
		args : [{refer:'dataSource'}]		
	}
};
