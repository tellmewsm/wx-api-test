package com.wx.testing;

import com.alibaba.fastjson.JSON;
import com.jayway.restassured.response.Response;
import com.wx.correlation.CheckPoints;
import com.wx.correlation.ParasReplace;
import com.wx.correlation.SaveParams;
import com.wx.correlation.StringToMap;
import com.wx.entity.TestCase;
import com.wx.testcases.WxAutoTest;
import com.wx.utils.ApiUtils;
import com.wx.utils.DbUtils;
import com.wx.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

/**
 * @ClassName TestCases
 * @Description //TODO
 * @Author wuxi
 * @Date 2020-01-10 09:33
 * @Version 1.0
 **/
public class TestCases {

    private static WxAutoTest wxAutoTest = ApiUtils.create(WxAutoTest.class);
    private static Map<String, Object> saveMap = new HashMap<>();

    @Test(dataProvider = "Cases")
    public void TestCase(TestCase testCase) {

        testCase = ParasReplace.matcher(testCase, saveMap);

        if ("get".equalsIgnoreCase(testCase.getType())) {

            wxAutoTest.HttpsGet(testCase.getUrl());

        } else if ("post".equalsIgnoreCase(testCase.getType())) {

            wxAutoTest.HttpsPostParaHeader(StringToMap.covertStringToMp(testCase.getParams(), "&"), testCase.getUrl(), StringToMap.covertStringToMp(testCase.getHeader()));

        } else if ("postjson".equalsIgnoreCase(testCase.getType())) {

            wxAutoTest.HttpsPostBodyHeader(JSON.toJSONString(StringToMap.covertStringToMp(testCase.getParams(), "&")), testCase.getUrl(), StringToMap.covertStringToMp(testCase.getHeader()));
        }

        boolean check = CheckPoints.checkByJsonPath(HttpUtils.responses(), testCase.getCheck());

        if (check) {
            saveMap = SaveParams.saveMapbyJsonPath(HttpUtils.responses(), testCase.getCorrelation());
        }
        Assert.assertTrue(check);

    }

    @DataProvider(name = "Cases")
    public Iterator<Object[]> TestProvider() {

        List<Object[]> dataProvider = new ArrayList<Object[]>();

        String sql = "select * from TestCase where id = 2";

        List<TestCase> cases = DbUtils.casesAll(sql, DbUtils.getConn());

        Iterator<TestCase> list = cases.iterator();

        while (list.hasNext()) {
            dataProvider.add(new Object[]{list.next()});
        }

        return dataProvider.iterator();
    }

}
