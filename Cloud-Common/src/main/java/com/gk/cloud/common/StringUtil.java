package com.gk.cloud.common;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtil {
	
	private static Logger logger = LoggerFactory.getLogger(StringUtil.class);
	
	/**
	 * 获取指定长度的随机字符串
	 * @param len
	 * @return
	 */
	public static String getRandomStr(int len) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		logger.info("随机字符串为:{}",sb.toString());
		return sb.toString();
	}
}
