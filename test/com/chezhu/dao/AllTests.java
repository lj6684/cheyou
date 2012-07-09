package com.chezhu.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	SupplyServiceTest.class,
	BrandServiceTest.class, 
	StyleServiceTest.class,
	FilterServiceTest.class,
})
public class AllTests {

}
