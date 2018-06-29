package com.neuedu.lvcity.common;

import org.junit.Before;
import org.junit.Test;

public class DBUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetConnection() {
		System.out.println(DBUtils.getConnection());
	}

}
