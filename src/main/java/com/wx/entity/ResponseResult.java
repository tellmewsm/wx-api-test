package com.wx.entity;

import com.jayway.restassured.internal.RestAssuredResponseImpl;
import com.jayway.restassured.response.Headers;

/**
 * @ClassName Result
 * @Description 接口返回值
 * @Author wuxi
 * @Date 2021/7/15 6:13 下午
 **/
public class ResponseResult {

    public int code;

    public String message;

    public String responseBody;

    public long timestamp;

    public boolean success;

    public RestAssuredResponseImpl responseOptions;

    public ResponseResult(int code, String message, RestAssuredResponseImpl responseOptions, boolean success) {
        super();
        this.code = code;
        this.message = message;
        this.responseOptions = responseOptions;
        this.timestamp = System.currentTimeMillis();
        this.success = success;
    }

    public static ResponseResult success(RestAssuredResponseImpl responseOptions) {
        return new ResponseResult(1, "操作成功", responseOptions, true);
    }

    public static ResponseResult fail(String errorMessage) {
        return new ResponseResult(0, errorMessage, null, false);
    }

    public String getResponseReturn() {
        // 返回值
        String responses = responseOptions.asString();
        return responses;
    }

    public String getField(String path) {
        // 获取字段
        String field = responseOptions.andReturn().jsonPath().getString(path);
        return field;
    }

    public Boolean hasValue(String name) {
        // 获取字段
        boolean field = responseOptions.asString().contains(name);
        return field;
    }

    public int getStatusCode() {
        // 获取状态码信息
        int statuscode = responseOptions.getStatusCode();
        return statuscode;
    }

    public String getHeaders() {
        // headers
        Headers getHeaders = responseOptions.getHeaders();
        return getHeaders.toString();
    }

    public long getTime() {
        // 时间
        long timeInSeconds = responseOptions.getTime();
        return timeInSeconds;
    }

}

