/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.inventory.utils;

public class NoGen {

	public static int zeroLength = 8;

	public static String getNo(String prifix, int no) {

		String noStr = String.valueOf(no);

		StringBuffer resultBuffer = new StringBuffer();

		resultBuffer.append(prifix);

		for (int i = 0; i < zeroLength - noStr.length(); i++) {
			resultBuffer.append(0);
		}

		resultBuffer.append(no);

		return resultBuffer.toString();
	}

}
