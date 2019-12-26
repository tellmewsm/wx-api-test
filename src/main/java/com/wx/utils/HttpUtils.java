/**
 * 
 */
package com.wx.utils;

import static com.jayway.restassured.RestAssured.given;
import static com.wx.variable.WxVariable.JsonType;
import static com.wx.variable.WxVariable.FormType;
import static com.wx.variable.WxVariable.Charset;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.wx.entity.TestGlobal;
import io.restassured.RestAssured;

/**
 * @author wuxi
 * @date 2018年11月2日
 */
public class HttpUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
	private static Map<String, Object> Cookies = new HashMap<>();
	private static Map<String, Object> Headers = new HashMap<>();
	private static RequestSpecification requestSpecifcation;
	private static Response response;
	private static String baseURL;

	// 动态代理
	public static Response httpRequest(TestGlobal testGlobal) throws Exception {

		// 判断请求头
		if (testGlobal.getHeaders() == null) {
			requestSpecifcation = given();
		} else {
			requestSpecifcation = given().headers(testGlobal.getHeaders());
		}

		// 判断是否加密
		if (testGlobal.getProtocol().equals("https")) {
			requestSpecifcation = requestSpecifcation.relaxedHTTPSValidation();
		}

		// 判断请求类型
		if (testGlobal.getBody() == null) {
			try {
				logger.info("[Params]=" + testGlobal.getParams());
				response = requestSpecifcation.contentType(FormType + ";" + Charset).parameters(testGlobal.getParams())
						.post(testGlobal.getUrl());

			} catch (Exception e) {
				logger.info("[Error]=" + e.getMessage());
			}

		} else {
			try {
				logger.info("[Body]=" + testGlobal.getBody());
				response = requestSpecifcation.contentType(JsonType + ";" + Charset).body(testGlobal.getBody())
						.post(testGlobal.getUrl());

			} catch (Exception e) {
				logger.info("[Error]=" + e.getMessage());
			}
		}

		logger.info("[responses]=" + responses());

		response.then().statusCode(200);

		return response;

	}

	public static RequestSpecification getRequestSpecification() {
		// 基础信息
		return given().headers(Headers).cookies(Cookies).relaxedHTTPSValidation();

	}

	public static void getURL() {
		// 获取url
		RestAssured.baseURI = baseURL;

	}

	public static String getDate() {
		// date
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return date.format(new Date());

	}

	public static long getCurrentTime() {
		// 获取当前时间
		long date = System.currentTimeMillis();
		return date;
	}

	public static String responses() {
		// 返回值
		String responses = response.asString();
		return responses;

	}

	public static String getField(String path) {
		// 获取字段
		String field = response.andReturn().jsonPath().getString(path);
		return field;

	}

	public static Boolean getHas(String name) {
		// 获取字段
		boolean field = response.asString().contains(name);
		return field;

	}

	public static String statuscode() {
		// 获取状态码信息
		String statuscode = response.getStatusCode() + "";
		return statuscode;
	}

	public static String headers() {
		// headers
		Headers getHeaders = response.getHeaders();
		return getHeaders.toString();
	}

	public static String time() {
		// 时间
		long timeInSeconds = response.getTime();
		String responsetime = String.valueOf(timeInSeconds);
		return responsetime;

	}

	public static String mapToString(Map<String, Object> map) {
		// map转成string
		StringBuilder stringBuilder = new StringBuilder();
		for (String key : map.keySet()) {
			stringBuilder.append(key).append("=").append(map.get(key)).append("&");
		}
		String parameters = stringBuilder.substring(0, stringBuilder.length() - 1);
		return parameters;

	}

	public static void resultCheck(String request, String url, String checkpath, String actual) throws IOException {
		Reporter.log("——————【正常用例】——————");
		Reporter.log("【请求地址】: " + url);
		Reporter.log("【请求内容】: " + request);
		Reporter.log("【返回结果】: " + responses());
		Reporter.log("【用例结果】: resultCheck=>expected: " + getField(checkpath) + " ,actual: " + actual);
		Assert.assertEquals(getField(checkpath), actual);
	}

    public static void resultCheck(String request, String url, String check) throws IOException {
        Reporter.log("——————【正常用例】——————");
        Reporter.log("【请求地址】: " + url);
        Reporter.log("【请求内容】: " + request);
        Reporter.log("【返回结果】: " + responses());
        Reporter.log("【用例结果】: resultCheck=>expected: " + check +"是否存在");
        Assert.assertTrue((responses().contains(check)));
    }
}
