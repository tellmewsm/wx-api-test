package com.wx.base;

import com.wx.utils.HttpUtils;
import org.testng.Assert;
import com.alibaba.fastjson.JSON;
import com.jayway.restassured.response.Response;
import com.wx.testcases.WxAutoTest;
import com.wx.utils.ApiUtils;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuxi
 * @date 2018年11月2日
 */
public class TestBase {

	private static  WxAutoTest wxAutoTest = ApiUtils.create(WxAutoTest.class);
	private static Response response;
	private Map<String, Object> Headers = new HashMap<>();
	private Map<String, Object> body = new HashMap<>();

	@Test
	public void testPostBody() {

		body.put("testUserName","tellme");

		response = wxAutoTest.HttpsPostBody(JSON.toJSONString(body), "https://api.apiopen.top/searchMusic");

		Assert.assertEquals(HttpUtils.getField("message"), "成功!");
	}

	@Test
	public void testPostBodyHeader() {

		body.put("testUserName","tellme");

		Headers.put("Accept", "application/json, text/plain, */*");

		response = wxAutoTest.HttpsPostBodyHeader(JSON.toJSONString(body), "https://api.apiopen.top/searchMusic",Headers);

		Assert.assertEquals(HttpUtils.getField("code"), "200");

	}
	
}
