/**
 * 
 */
package com.wx.listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

/**
* @author wuxi
* @date 2018年11月1日
 */
public class WRetryListener implements IAnnotationTransformer {

	@Override
	public void transform(ITestAnnotation annotation, @SuppressWarnings("rawtypes") Class testClass, @SuppressWarnings("rawtypes") Constructor testConstructor, Method testMethod) {
        
		IRetryAnalyzer retry = annotation.getRetryAnalyzer(); 
        if (retry == null) {
            annotation.setRetryAnalyzer(WRetry.class);
        } 
	}

}
