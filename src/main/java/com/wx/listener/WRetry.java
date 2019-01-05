/**
 * 
 */
package com.wx.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * @author wuxi
 * @date 2018年11月1日
 */
public class WRetry implements IRetryAnalyzer {

	private static Logger logger = LoggerFactory.getLogger(WRetry.class);
	private int retryCount = 0;
	private int maxRetryCount = 1;

	@Override
	public boolean retry(ITestResult result) {

		if (retryCount < maxRetryCount) {
			String message = "running retry for  '" + result.getName() + "' on class " + this.getClass().getName()
					+ " Retrying " + retryCount + " times";
			logger.info(message);
			retryCount++;
			return true;
		}
		return false;
	}

	// 用于重置retryCnt
	public void reset() {
		retryCount = 0;
	}

}
