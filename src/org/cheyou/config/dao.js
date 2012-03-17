var ioc = {
    dataSource: {
        type: "com.mchange.v2.c3p0.ComboPooledDataSource",
        fields: {
			driverClass: 'com.mysql.jdbc.Driver',
			jdbcUrl: 'jdbc:mysql://127.0.0.1:3306/cheyou',
            user: 'root',
            password: '11111111',
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
