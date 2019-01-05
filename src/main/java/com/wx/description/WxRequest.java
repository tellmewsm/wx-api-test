/**
 * 
 */
package com.wx.description;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @author wuxi
* @date 2018年11月2日
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface WxRequest {
	
	String protocol();
    String wmethod();
    String description();
    
}
