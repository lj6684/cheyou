var ioc = {
	config : {
		type: 'org.nutz.ioc.impl.PropertiesProxy',
		fields: {
			paths: ['datasource.properties'] 
		}
	},	
	
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
            maxIdleTime: "60"
        }
    },
	
	dao : {
		type : 'org.nutz.dao.impl.NutDao',
		args : [{refer:'dataSource'}]		
	}
};
