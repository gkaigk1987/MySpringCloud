package com.gk.cloud.eureka;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gk.cloud.common.StringUtil;

public class CommonTest {

	private static Logger logger = LoggerFactory.getLogger(CommonTest.class);
	
	@Test
	public void testCommon() {
		String randomStr = StringUtil.getRandomStr(10);
		logger.info(randomStr);
	}
	
}
