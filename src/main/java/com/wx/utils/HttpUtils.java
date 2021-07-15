package com.wx.utils;

import static com.jayway.restassured.RestAssured.given;
import static com.wx.variable.WxVariable.JsonType;
import static com.wx.variable.WxVariable.FormType;
import static com.wx.variable.WxVariable.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.jayway.restassured.internal.RestAssuredResponseImpl;
import com.wx.entity.ResponseResult;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.wx.entity.TestGlobal;

/**
 * @author wuxi
 * @date 2018年11月2日
 */
public class HttpUtils {

    private static Map<String, Object> Cookies = new HashMap<>();
    private static Map<String, Object> Headers = new HashMap<>();
    private static RequestSpecification requestSpecifcation;
    private static Response response;

    // 动态代理
    public static ResponseResult httpRequest(TestGlobal testGlobal) throws Exception {

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
        if (testGlobal.getWmethod().equals("get")) {
            try {
                response = requestSpecifcation.get(testGlobal.getUrl());
                return ResponseResult.success((RestAssuredResponseImpl) response.andReturn());
            } catch (Exception e) {
                return ResponseResult.fail(e.getMessage());
            }
        } else {
            // 判断参数请求类型
            if (testGlobal.getBody() == null) {
                try {
                    response = requestSpecifcation.contentType(FormType + ";" + Charset).parameters(testGlobal.getParams())
                            .post(testGlobal.getUrl());
                    return ResponseResult.success((RestAssuredResponseImpl) response.andReturn());
                } catch (Exception e) {
                    return ResponseResult.fail(e.getMessage());
                }
            } else {
                try {
                    response = requestSpecifcation.contentType(JsonType + ";" + Charset).body(testGlobal.getBody())
                            .post(testGlobal.getUrl());
                    return ResponseResult.success((RestAssuredResponseImpl) response.andReturn());
                } catch (Exception e) {
                    return ResponseResult.fail(e.getMessage());
                }
            }
        }
    }

    public static RequestSpecification getRequestSpecification() {
        // 基础信息
        return given().headers(Headers).cookies(Cookies).relaxedHTTPSValidation();
    }

    public static String getDate() {
        // date
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return date.format(new Date());
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
}
