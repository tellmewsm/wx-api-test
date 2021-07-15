package com.wx.base;

import com.wx.correlation.CheckPoints;
import com.wx.correlation.ParasReplace;
import com.wx.correlation.SaveParams;
import com.wx.correlation.StringToMap;
import com.wx.entity.ResponseResult;
import com.wx.entity.TestCase;
import org.testng.Assert;
import com.alibaba.fastjson.JSON;
import com.wx.testcases.Wequest;
import com.wx.utils.WxApi;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuxi
 * @date 2020年1月3日
 */
public class TestBase {

    private static Wequest wequest = WxApi.build(Wequest.class);
    private static ResponseResult response;
    private Map<String, Object> Headers = new HashMap<>();
    private Map<String, Object> body = new HashMap<>();

    public static void main(String args[]) {
        testHttpsGet();
    }

    @Test
    public static void testHttpsGetError() {
        response = wequest.HttpsGet("https://www.baidu.com11111");
        if (!response.success) {
            System.out.println(response.message);
        }
    }

    @Test
    public static void testHttpsGet() {
        response = wequest.HttpsGet("https://api.apiopen.top/searchMusic");
        System.out.println(response.getResponseReturn());
        System.out.println(response.getHeaders());
        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        System.out.println(response.getField("message"));
    }

    @Test
    public void testPostBody() {

        body.put("testUserName", "tellme");
        response = wequest.HttpsPostBody(JSON.toJSONString(body), "https://api.apiopen.top/searchMusic");
        Assert.assertEquals(response.getField("message"), "成功!");

    }

    @Test
    public void testPostBodyHeader() {

        body.put("testUserName", "tellme");
        Headers.put("Accept", "application/json, text/plain, */*");
        response = wequest.HttpsPostBodyHeader(JSON.toJSONString(body), "https://api.apiopen.top/searchMusic", Headers);
        System.out.println(response.getResponseReturn());
        Assert.assertEquals(response.getField("code"), "200");

    }

    @Test
    public void testcheckByJsonPath() {

        body.put("testUserName", "tellme");
        response = wequest.HttpsPostBody(JSON.toJSONString(body), "https://api.apiopen.top/searchMusic");
        //通过jsonPath检查
        CheckPoints.checkByJsonPath(response.getResponseReturn(), "$.code=200;$.message=成功!");
        //关联
        SaveParams.saveMapbyJsonPath(response.getResponseReturn(), "user=$.message;password=$.code");
    }

    @Test
    public void testParasReplace() {

        body.put("testUserName", "tellme");
        response = wequest.HttpsPostBody(JSON.toJSONString(body), "https://api.apiopen.top/searchMusic");
        //通过jsonPath检查
        CheckPoints.checkByJsonPath(response.getResponseReturn(), "$.code=200;$.message=成功!");
        TestCase testCase = new TestCase();
        testCase.setUrl("https://api.apiopen.top/searchMusic");
        testCase.setParams("user=${user}&password=${password}");
        testCase.setHeader("user=${user};password=${password};mobile=xxxxxx");
        //关联
        testCase = ParasReplace.matcher(testCase, SaveParams.saveMapbyJsonPath(response.getResponseReturn(), "user=$.message;password=$.code"));
        wequest.HttpsPostParaHeader(StringToMap.covertStringToMp(testCase.getParams(), "&"), testCase.getUrl(), StringToMap.covertStringToMp(testCase.getHeader()));
        wequest.HttpsPostBodyHeader(JSON.toJSONString(StringToMap.covertStringToMp(testCase.getParams(), "&")), testCase.getUrl(), StringToMap.covertStringToMp(testCase.getHeader()));
    }

}
