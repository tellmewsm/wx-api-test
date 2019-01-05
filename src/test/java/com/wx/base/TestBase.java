/**
 * 
 */
package com.wx.base;

import static com.wx.variable.WxVariable.GPro;

import org.testng.Assert;
import com.alibaba.fastjson.JSON;
import com.jayway.restassured.response.Response;
import com.wx.testcases.WxAutoTest;
import com.wx.utils.ApiUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author wuxi
 * @date 2018年11月2日
 */
public class TestBase {

	public static  WxAutoTest wxAutoTest = ApiUtils.create(WxAutoTest.class);
	public static Response response;

	@BeforeClass
	public void testDemo() {

		//使用各种请求方式
		response = wxAutoTest.HttpsPostBody(JSON.toJSONString("xxx"), GPro.getKey("ServerUrl"));

		Assert.assertEquals("ok", "ok");
		
	}
	
}
