package com.wx.base;

import com.wx.correlation.CheckPoints;
import com.wx.correlation.ParasReplace;
import com.wx.correlation.SaveParams;
import com.wx.correlation.StringToMap;
import com.wx.entity.TestCase;
import com.wx.utils.HttpUtils;
import com.wx.utils.StrSubUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @date 2020年1月3日
 */
public class TestBase {

    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    private static WxAutoTest wxAutoTest = ApiUtils.create(WxAutoTest.class);
    private static Response response;
    private Map<String, Object> Headers = new HashMap<>();
    private Map<String, Object> body = new HashMap<>();


    @Test
    public void testHttpsGet() {

        response = wxAutoTest.HttpsGet("https://www.baidu.com/");
    }

    @Test
    public void testPostBody() {

        body.put("testUserName", "tellme");
        response = wxAutoTest.HttpsPostBody(JSON.toJSONString(body), "https://api.apiopen.top/searchMusic");
        //通过json解析
        logger.info("message>>>>>>>" + HttpUtils.getField("message"));
        //通过string截取
        logger.info("message>>>>>>>" + StrSubUtil.getSubUtilSimple(HttpUtils.responses(), "message\":\"(.*?)\",\"result"));
        Assert.assertEquals(HttpUtils.getField("message"), "成功!");

    }

    @Test
    public void testPostBodyHeader() {

        body.put("testUserName", "tellme");
        Headers.put("Accept", "application/json, text/plain, */*");
        response = wxAutoTest.HttpsPostBodyHeader(JSON.toJSONString(body), "https://api.apiopen.top/searchMusic", Headers);
        Assert.assertEquals(HttpUtils.getField("code"), "200");

    }

    @Test
    public void testcheckByJsonPath() {

        body.put("testUserName", "tellme");

        response = wxAutoTest.HttpsPostBody(JSON.toJSONString(body), "https://api.apiopen.top/searchMusic");
        //通过jsonPath检查
        CheckPoints.checkByJsonPath(HttpUtils.responses(), "$.code=200;$.message=成功!");

        //关联
        SaveParams.saveMapbyJsonPath(HttpUtils.responses(), "user=$.message;password=$.code");
        logger.info(SaveParams.saveMap.toString());

    }

    @Test
    public void testParasReplace() {

        body.put("testUserName", "tellme");
        response = wxAutoTest.HttpsPostBody(JSON.toJSONString(body), "https://api.apiopen.top/searchMusic");

        //通过jsonPath检查
        CheckPoints.checkByJsonPath(HttpUtils.responses(), "$.code=200;$.message=成功!");

        TestCase testCase = new TestCase();
        testCase.setUrl("https://api.apiopen.top/searchMusic");
        testCase.setParams("user=${user}&password=${password}");
        testCase.setHeader("user=${user};password=${password};mobile=xxxxxx");

        //关联
        testCase = ParasReplace.matcher(testCase,SaveParams.saveMapbyJsonPath(HttpUtils.responses(), "user=$.message;password=$.code"));

        wxAutoTest.HttpsPostParaHeader(StringToMap.covertStringToMp(testCase.getParams(), "&"), testCase.getUrl(), StringToMap.covertStringToMp(testCase.getHeader()));

        wxAutoTest.HttpsPostBodyHeader(JSON.toJSONString(StringToMap.covertStringToMp(testCase.getParams(), "&")), testCase.getUrl(), StringToMap.covertStringToMp(testCase.getHeader()));

    }

}
